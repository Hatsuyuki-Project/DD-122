package net.mizucoffee.hatsuyuki_chinachu;

import android.app.Application;
import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import net.mizucoffee.hatsuyuki_chinachu.tools.DataModel;

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        DataModel.Companion.init(getSharedPreferences("HatsuyukiChinachu", Context.MODE_PRIVATE)); // ちょくちょく落ちるのクソ

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        Picasso.setSingletonInstance(built);
    }

    public static Context getContext(){
        return mContext;
    }

    public boolean useExtensionRenderers() {
        return BuildConfig.FLAVOR.equals("withExtensions");
    }

}