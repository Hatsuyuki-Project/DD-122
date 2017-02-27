package net.mizucoffee.hatsuyuki_chinachu.dashboard.recorded;

import net.mizucoffee.hatsuyuki_chinachu.chinachu.api.ChinachuGammaApi;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mizucoffee on 2/27/17.
 */

public class RecordedInteractorImpl implements RecordedInteractor {

    @Override
    public void getRecordedList(Callback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.50.50:10472/")
                //TODO: IPをちゃんと指定する
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChinachuGammaApi api = retrofit.create(ChinachuGammaApi.class);

        api.getRecorded().enqueue(callback);
    }
}
