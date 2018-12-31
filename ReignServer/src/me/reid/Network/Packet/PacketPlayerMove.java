package me.reid.Network.Packet;

/**
 * Packet that is sent when a player moves
 */
public class PacketPlayerMove extends Packet {

    private String username;
    private float x, y;

    public PacketPlayerMove(String username, float x, float y) {
        id = PacketID.PLAYERMOVE;
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