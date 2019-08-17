package me.reid.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.reid.Entities.Networked.NetPlayerHandler;
import me.reid.Entities.Player;
import me.reid.Game;
import me.reid.GameCamera;
import me.reid.World.Map;

public class PlayScreen implements Screen {

    private static PlayScreen instance;

    private Game game;
    private Player player;
    private Map map;
    private GameCamera camera;

    private NetPlayerHandler netPlayerHandler;

    public PlayScreen(Game game, String username, String address, int port) {
        instance = this;
        this.game = game;

        player = new Player(300, 300);
        camera = new GameCamera(this, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        map = new Map(this, "map.tmx");

        // Create a new connection handler to the server
        netPlayerHandler = new NetPlayerHandler(this);

        game.createConnectionHandler(username, address, port);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // Update Player
        player.update();

        // Update / Render Camera
        camera.update();

        // Update / Render Map
        map.render();

        // Render Player
        Batch batch = map.getRenderer().getBatch();
        batch.begin();
        player.render(batch);
        netPlayerHandler.renderAllPlayers(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        player.dispose();
        netPlayerHandler.disposeOfAll();
    }

    public Player getPlayer() {
        return player;
    }

    public GameCamera getGameCamera() {
        return camera;
    }

    public NetPlayerHandler getNetPlayerHandler() {
        return netPlayerHandler;
    }

    public static PlayScreen i() {
        return instance;
    }

}
