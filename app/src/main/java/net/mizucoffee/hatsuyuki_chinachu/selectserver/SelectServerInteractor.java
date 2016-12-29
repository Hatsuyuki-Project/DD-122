package net.mizucoffee.hatsuyuki_chinachu.selectserver;

import net.mizucoffee.hatsuyuki_chinachu.model.ServerConnection;

import java.util.ArrayList;

interface SelectServerInteractor {

    interface OnLoadFinishedListener {
        void onSuccess(ArrayList<ServerConnection> sc);
        void onNotFound();
    }

    void loadServerConnections(OnLoadFinishedListener listener);
    void deleteServerConnection(int position);
    ServerConnection editServerConnection(int position);

}
