package me.reid;

import com.badlogic.gdx.graphics.Texture;

public class Utilities {

    public static boolean isInBounds(int touchX, int touchY, int spriteX, int spriteY, Texture texture) {
        return ((touchX >= spriteX && touchX <= spriteX + texture.getWidth()) &&
                (touchY >= spriteY && touchY <= spriteX + texture.getHeight()));

    }
}
