package me.reid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import me.reid.Network.Connection.ConnectionHandler;
import me.reid.Screens.MenuScreen;
import me.reid.Screens.PlayScreen;

public class Game extends com.badlogic.gdx.Game {

    private static Game instance;
    private ConnectionHandler connectionHandler;

    @Override
    public void create() {
        instance = this;
        setScreen(new MenuScreen(this));

    }

    public void createConnectionHandler(String username, String address, int port) {
        connectionHandler = new ConnectionHandler(username, address, port);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public ConnectionHandler getConnectionHandler() { return connectionHandler; }
    public static Game i() {
        return instance;
    }
}
