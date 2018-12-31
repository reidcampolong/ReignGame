package me.reid.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.reid.GFX.GameObject;

public class NetPlayer implements GameObject {

    private float speed;
    private Texture texture;
    private PositionHandler mover;

    public NetPlayer(float startX, float startY) {
        speed = 5;
        texture = Player.texture;
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
