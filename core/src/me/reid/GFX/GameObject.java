package me.reid.GFX;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public interface GameObject {

    void render(Batch batch);
    void update();
}
