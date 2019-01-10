package me.reid.GFX;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {

    public static Texture playerTexture;
    public static Texture joinButtonTexture;
    public static Texture customTiles;

    public TextureManager() {
        playerTexture = new Texture("player.png");
        joinButtonTexture = new Texture("joinButton.png");
        customTiles = new Texture("customTiles.png");
    }

    public void dispose() {
        playerTexture.dispose();
        joinButtonTexture.dispose();
        customTiles.dispose();
    }
}
