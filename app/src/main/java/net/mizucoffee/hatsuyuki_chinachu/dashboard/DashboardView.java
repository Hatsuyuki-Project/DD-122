package net.mizucoffee.hatsuyuki_chinachu.dashboard;

import android.content.SharedPreferences;

/**
 * Created by mizucoffee on 12/19/16.
 */

public interface DashboardView {
    void intentSelectServer(boolean first);
    SharedPreferences getActivitySharedPreferences(String name , int mode);
    void setNavTitle(String title);
    void setNavAddress(String address);
    String getStringFromXml(int resId);
}
