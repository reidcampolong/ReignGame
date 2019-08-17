package me.reid.Network;

import me.reid.Log;
import me.reid.Network.Packet.Packet;
import me.reid.Network.Packet.PacketLoginRequest;
import me.reid.Network.Packet.PacketPlayerLogin;
import me.reid.Network.Packet.PacketPlayerMove;
import me.reid.ReignServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Sends out to clients as well as manages connections
 *
 * @author Reid
 */
public class NetworkServer {

    private DatagramSocket socket;
    private ReignServer server;

    /**
     * instanceof this class to remove a connection on close
     **/
    private static NetworkServer instance;

    /**
     * Map of usernames and their connection
     **/
    private HashMap<String, ClientConnection> userConnections;

    /**
     * Creates NetworkServer
     *
     * @param server
     */
    public NetworkServer(ReignServer server) {
        instance = this;
        this.server = server;
        this.userConnections = new HashMap<>();
        try {
            socket = new DatagramSocket(server.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a packet to a client
     *
     * @param packet
     * @param address
     * @param port
     */
    public void sendPacket(Packet packet, InetAddress address, int port) {
        send(packet.getBytes(), address, port);
    }

    private void send(byte[] data, InetAddress address, int port) {
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidName(String username) {
        return !userConnections.containsKey(username);
    }

    /**
     * Add a connection to our map
     *
     * @param username
     * @param address
     * @param port
     * @pre check isValidName
     */
    public void addConnection(String username, InetAddress address, int port) {
        userConnections.put(username, new ClientConnection(address, port));
    }

    public void notifyAllOfNewPlayer(String username) {
        for (Map.Entry<String, ClientConnection> entry : userConnections.entrySet())
            sendPacket(new PacketPlayerLogin(username, 300, 300), entry.getValue().getAddress(), entry.getValue().getPort());
    }

    public void notifyAllToCurrentPlayer(InetAddress address, int port) {
        for (Map.Entry<String, ClientConnection> entry : userConnections.entrySet())
            sendPacket(new PacketPlayerLogin(entry.getKey(), 32, 32), address, port);
    }

    public void notifyAllOfMove(PacketPlayerMove movePacket) {
        for (Map.Entry<String, ClientConnection> entry : userConnections.entrySet()) {
            if (entry.getKey().equals(movePacket.getUsername())) continue;
            sendPacket(movePacket, entry.getValue().getAddress(), entry.getValue().getPort());
        }
    }


    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * Returns an instance of this class, primarily used to disconnect a client
     *
     * @return this
     */
    public static NetworkServer getInstance() {
        return instance;
    }
}
