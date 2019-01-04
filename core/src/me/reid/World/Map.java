package me.reid.World;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.reid.Game;
import me.reid.Screens.PlayScreen;

public class Map {

    private PlayScreen game;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public Map(PlayScreen game, String path) {
        this.game = game;
        this.map = new TmxMapLoader().load(path);
        this.renderer = new OrthogonalTiledMapRenderer(map);

    }

    public void render() {
        update();
        renderer.render();
    }

    public void update() {
        // Render the map in the camera's view
        renderer.setView(game.getGameCamera().getCamera());
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
}
