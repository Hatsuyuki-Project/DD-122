package net.mizucoffee.hatsuyuki_chinachu.dashboard.recorded;

import android.app.Activity;
import android.content.Context;

import net.mizucoffee.hatsuyuki_chinachu.chinachu.api.model.gamma.Recorded;
import net.mizucoffee.hatsuyuki_chinachu.dashboard.DashboardInteractor;
import net.mizucoffee.hatsuyuki_chinachu.model.ServerConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mizucoffee on 2/27/17.
 */

public class RecordedPresenterImpl implements RecordedPresenter {

    private RecordedView mRecordedView;
    private RecordedInteractor mRecordedInteractor;

    RecordedPresenterImpl(RecordedView recordedView){
        this.mRecordedView = recordedView;
        mRecordedInteractor = new RecordedInteractorImpl(mRecordedView.getActivitySharedPreferences("HatsuyukiChinachu", Context.MODE_PRIVATE));
    }

    @Override
    public void onDestroy() {
        mRecordedView = null;
    }

    @Override
    public void getRecorded(Activity a){
        mRecordedInteractor.refreshServerConnection();
        mRecordedInteractor.getServerConnection(new DashboardInteractor.OnLoadFinishedListener() {
            @Override
            public void onSuccess(ServerConnection sc) {
                mRecordedInteractor.getRecordedList(new Callback<List<Recorded>>() {
                    @Override
                    public void onResponse(Call<List<Recorded>> call, Response<List<Recorded>> response) {
                        mRecordedView.setRecyclerView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Recorded>> call, Throwable t) {
                        mRecordedView.removeRecyclerView();
                        mRecordedView.showSnackbar("サーバーへの接続に失敗しました");
                    }
                });
            }

            @Override
            public void onNotFound() {
                mRecordedView.removeRecyclerView();
                mRecordedView.showSnackbar("サーバーを登録しましょう");//おいおい変更。カードにする。
            }
        });
    }
}
