package me.reid.Entities;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.reid.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles connected players and packets
 */
public class NetPlayerHandler {

    private Game game;

    // Username with NetPlayer
    private HashMap<String, NetPlayer> netPlayers;

    public NetPlayerHandler(Game game) {
        this.game = game;
        this.netPlayers = new HashMap();
    }

    public void createNewPlayer(String username, float x, float y) {
        netPlayers.put(username, new NetPlayer(x, y));
    }

    public void renderAllPlayers(Batch batch) {
        for (Map.Entry<String, NetPlayer> entry : netPlayers.entrySet())
            entry.getValue().render(batch);
    }

    public NetPlayer getPlayer(String username) {
        return netPlayers.get(username);
    }


}
