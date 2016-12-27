package net.mizucoffee.hatsuyuki_chinachu.selectserver.addserver;

import net.mizucoffee.hatsuyuki_chinachu.model.ServerConnection;

import java.util.ArrayList;

/**
 * Created by mizucoffee on 12/20/16.
 */

public interface AddServerInteractor {
    ArrayList<ServerConnection> load();
    void save(ServerConnection sc);
    void edited(ServerConnection sc,int position);
}
