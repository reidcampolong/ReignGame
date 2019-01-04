package me.reid.Network.Connection;

import me.reid.Network.Packet.Packet;
import me.reid.Network.Packet.PacketLoginRequest;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionHandler {

    public static String username;

    private InetAddress serverAddress;
    private String host;
    private int port;

    private NetworkConnection connection;
    private ServerInputThread inputThread;

    public ConnectionHandler(String playerName, String host, int port) {
        username = playerName;
        this.host = host;
        this.port = port;
        try {
            this.serverAddress = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        connect();
    }

    /**
     * Initial connect sequence called upon creation
     */
    private void connect() {
        connection = new NetworkConnection(this);
        inputThread = new ServerInputThread(this);
        inputThread.start();

        // Send our connect sequence packet
        sendConnectionPacket();
    }

    public void sendPacket(Packet packet) {
        connection.send(packet);
    }

    /**
     * Send a connection packet tot he server
     */
    private void sendConnectionPacket() {
        Packet gamePacket = new PacketLoginRequest(username);
        connection.send(gamePacket);
    }

    /*public void sendMovePacket(int x, int y) {
        Packet movePacket = new PacketMoveChange(username, x, y);
        connection.send(movePacket);
    }*/

    public boolean isOpen() {
        return connection.isValid();
    }

    /**
     * Receive a Datagram Packet from the server
     * @return
     */
    public DatagramPacket receivePacket() {
        return connection.receivePacket();
    }

    public InetAddress getServerAddress() {
        return serverAddress;
    }

}
