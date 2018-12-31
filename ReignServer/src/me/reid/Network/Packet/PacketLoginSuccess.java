package me.reid.Network.Packet;

/**
 * Packet that is sent back to the client with their spawn x and y
 */
public class PacketLoginSuccess extends Packet {

    private String username;
    private float x, y;

    public PacketLoginSuccess(String username, float x, float y) {
        id = PacketID.LOGINSUCCESS;
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
        return (id.getId()+":"+username+":"+x+":"+y).getBytes();
    }
}
