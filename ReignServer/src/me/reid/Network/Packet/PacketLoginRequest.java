package me.reid.Network.Packet;

/**
 * Sent from client -> server to ask for name
 * Sent from server -> client if name not available
 */
public class PacketLoginRequest extends Packet {

    private String username;

    public PacketLoginRequest(String username) {
        id = PacketID.LOGINREQUEST;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getBytes() {
        return (id.getId()+":"+username).getBytes();
    }

}
