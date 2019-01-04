package me.reid.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.reid.Game;
import me.reid.Log;
import me.reid.Utilities;

public class MenuScreen implements Screen, Input.TextInputListener {

    private String inputText;

    private SpriteBatch batch;
    private float x, y;
    private Texture joinTexture;

    private Game game;

    public MenuScreen(Game game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.joinTexture = new Texture("joinButton.png");
        this.x = (Gdx.graphics.getWidth() / 2) - (joinTexture.getWidth() / 2);
        this.y = (Gdx.graphics.getHeight() / 2) - (joinTexture.getHeight() / 2);
    }

    @Override
    public void show() {
        Log.i("Displaying menu screen");
    }

    @Override
    public void render(float delta) {
        update();

        batch.begin();
        batch.draw(joinTexture, x, y);
        batch.end();
    }

    public void update() {
        if (Gdx.input.justTouched() && Utilities.isInBounds(Gdx.input.getX(), Gdx.input.getY(), (int) x, (int) y, joinTexture)) {
            Gdx.input.getTextInput(this, "Please enter username", "username", "");
            //game.setScreen(new PlayScreen(game));
        }
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
        joinTexture.dispose();
    }

    @Override
    public void input(String text) {
        this.inputText = text;
    }

    @Override
    public void canceled() {

    }
}
