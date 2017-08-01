package net.mizucoffee.hatsuyuki_chinachu.tools

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject
import net.mizucoffee.hatsuyuki_chinachu.chinachu.ChinachuApi
import net.mizucoffee.hatsuyuki_chinachu.chinachu.model.program.Program
import net.mizucoffee.hatsuyuki_chinachu.model.ProgramItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object ChinachuModel {

    private val programItemsSubject = PublishSubject.create<ArrayList<ProgramItem>>()
    val programItems = programItemsSubject as Observable<ArrayList<ProgramItem>>

    private val broadcastSubject = PublishSubject.create<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>>()
    @JvmField val broadcast = broadcastSubject as Observable<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>>

    @JvmField
    var getAllPrograms: Observer<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>? = null

    fun getAllPrograms(address: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://$address/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val api = retrofit.create(ChinachuApi::class.java)

        api?.allPrograms?.subscribe(getAllPrograms)
    }
    fun getBroadcastList(address: String){
        val retrofit = Retrofit.Builder()
                .baseUrl("http://$address/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val api = retrofit.create(ChinachuApi::class.java)

        api.getBroadcasting().enqueue(object : Callback<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>> {
            override fun onResponse(call: Call<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>>, response: Response<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>>) {
                broadcastSubject.onNext(response.body())
            }

            override fun onFailure(call: Call<List<net.mizucoffee.hatsuyuki_chinachu.chinachu.model.Program>>, t: Throwable) {
                broadcastSubject.onNext(null)
            }
        })
    }

    fun getRecordedList(address: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://$address/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(ChinachuApi::class.java)

        api?.recorded?.enqueue(object : Callback<List<Program>> {
            override fun onResponse(call: Call<List<Program>>, response: Response<List<Program>>) {
                val items = ArrayList<ProgramItem>()
                for (p in response.body())
                    items.add(p.programItem)
                programItemsSubject.onNext(items)
            }

            override fun onFailure(call: Call<List<Program>>, t: Throwable) {
                Shirayuki.log("error")
                programItemsSubject.onError(t)
                //TODO: エラー処理
            }
        })
    }
    @JvmField
    val NOT_FOUND = 0
    @JvmField
    val FAILURE = 1
}
