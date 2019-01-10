package me.reid.Entities.Networked;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.reid.Entities.Player;
import me.reid.Entities.PositionHandler;
import me.reid.GFX.GameObject;
import me.reid.GFX.TextureManager;

public class NetPlayer implements GameObject {

    private Texture texture;
    private PositionHandler mover;

    public NetPlayer(float startX, float startY) {
        texture = TextureManager.playerTexture;
        mover = new PositionHandler(new Vector2(), 5);
        mover.setPosition(startX, startY);
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, getX(), getY(), 20, 26);
    }

    @Override
    public void update() {}

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return mover.getX();
    }

    public float getY() {
        return mover.getY();
    }

    public void setPosition(float x, float y) {
        mover.setPosition(x, y);
    }
}
