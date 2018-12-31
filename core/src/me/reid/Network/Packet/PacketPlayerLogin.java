package me.reid.Network.Packet;

/**
 * Packet that is sent to all clients when a player spawns at x y
 */
public class PacketPlayerLogin extends Packet {

    private String username;
    private float x, y;

    public PacketPlayerLogin(String username, float x, float y) {
        id = PacketID.PLAYERLOGIN;
        this.username = username;
        this.x = x;
        this.y = y;
    }

    public String getUsername() {
        return username;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public byte[] getBytes() {
        return (id.getId() + ":" + username + ":" + x + ":" + y).getBytes();
    }

}
