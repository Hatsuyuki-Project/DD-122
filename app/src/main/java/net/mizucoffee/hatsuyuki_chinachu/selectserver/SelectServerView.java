package net.mizucoffee.hatsuyuki_chinachu.selectserver;

import android.content.DialogInterface;
import android.content.SharedPreferences;

import net.mizucoffee.hatsuyuki_chinachu.model.ServerConnection;

import java.util.ArrayList;

/**
 * Created by mizucoffee on 12/19/16.
 */

public interface SelectServerView {
    void intentAdd();
    void intentEdit(ServerConnection sc,int position);
    SharedPreferences getActivitySharedPreferences(String name , int mode);
    void setRecyclerView(ArrayList<ServerConnection> connections);
    void showAlertDialog(String title,String description,DialogInterface.OnClickListener onClickListener);
}
