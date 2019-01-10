package me.reid.Entities;

import com.badlogic.gdx.math.Vector2;

public class PositionHandler {

    private Vector2 position;
    private float speed;

    public PositionHandler(Vector2 position, float speed) {
        this.position = position;
        this.speed = speed;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void moveRight() {
        position.x += speed;
    }

    public void moveLeft() {
        position.x -= speed;
    }

    public void moveUp() {
        position.y += speed;
    }

    public void moveDown() {
        position.y -= speed;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
