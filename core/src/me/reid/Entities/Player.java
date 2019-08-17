package me.reid.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import me.reid.GFX.GameObject;
import me.reid.GFX.TextureManager;
import me.reid.Game;
import me.reid.Network.Connection.ConnectionHandler;
import me.reid.Network.Packet.PacketPlayerMove;

public class Player implements GameObject {

    private Texture texture;
    private PositionHandler mover;
    private boolean didMove = false;

    public Player(float startX, float startY) {
        texture = TextureManager.playerTexture;
        mover = new PositionHandler(new Vector2(), 5);
        mover.setPosition(startX, startY);
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, getX(), getY(), 20, 26);
    }

    @Override
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            mover.moveUp();
            didMove = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            mover.moveDown();
            didMove = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            mover.moveLeft();
            didMove = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            mover.moveRight();
            didMove = true;
        }

        if (didMove)
            Game.i().getConnectionHandler().sendPacket(new PacketPlayerMove(ConnectionHandler.username, getX(), getY()));
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return mover.getX();
    }

    public float getY() {
        return mover.getY();
    }
}
