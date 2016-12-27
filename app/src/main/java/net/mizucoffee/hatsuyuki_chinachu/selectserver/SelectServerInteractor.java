package net.mizucoffee.hatsuyuki_chinachu.selectserver;

import net.mizucoffee.hatsuyuki_chinachu.model.ServerConnection;

import java.util.ArrayList;

interface SelectServerInteractor {

    interface OnLoadFinishedListener {
        void onSuccess(ArrayList<ServerConnection> sc);
        void onNotFound();
    }

    void load(OnLoadFinishedListener listener);
    void delete(int position);
    ServerConnection edit(int position);

}
