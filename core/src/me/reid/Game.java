package me.reid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import me.reid.Entities.NetPlayerHandler;
import me.reid.Entities.Player;
import me.reid.Network.Connection.ConnectionHandler;
import me.reid.World.Map;

public class Game extends ApplicationAdapter {

	private static Game instance;

	private Player player;
	private Map map;
	private GameCamera camera;

	private ConnectionHandler connectionHandler;
	private NetPlayerHandler netPlayerHandler;

	public static String username;
	
	@Override
	public void create () {
		instance = this;

		player = new Player(32, 32);
		camera = new GameCamera(this, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		map = new Map(this, "map.tmx");

		// Create a new connection handler to the server
		netPlayerHandler = new NetPlayerHandler(this);
		username = "Reid" + Math.random();
		connectionHandler = new ConnectionHandler(username, "localhost", 1069);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
	public void dispose () {
		map.dispose();
		player.dispose();
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

	public ConnectionHandler getConnectionHandler() { return connectionHandler; }

	public static Game i() {
		return instance;
	}
}
