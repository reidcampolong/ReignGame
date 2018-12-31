package me.reid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameCamera {

    private Game game;
    private OrthographicCamera camera;

    public GameCamera(Game game, int width, int height) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, width, height);
        this.camera.update();
    }


    public void render() {
        update();
    }

    public void update() {
        // Move camera to player's position
        camera.position.x = game.getPlayer().getX();
        camera.position.y = game.getPlayer().getY();
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
