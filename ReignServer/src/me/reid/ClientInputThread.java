package me.reid;

import me.reid.Network.Packet.Packet;
import me.reid.Network.Packet.PacketLoginRequest;
import me.reid.Network.Packet.PacketLoginSuccess;
import me.reid.Network.Packet.PacketPlayerMove;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientInputThread extends Thread {

    private ReignServer server;
    private int MAX_PACKET_SIZE = 1024;
    private byte[] dataReceiveBuffer = new byte[MAX_PACKET_SIZE * 2];
    private DatagramSocket socket;

    public ClientInputThread(ReignServer server, DatagramSocket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("\nWaiting for a new packet");
            DatagramPacket packet = new DatagramPacket(dataReceiveBuffer, MAX_PACKET_SIZE);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            processPacket(packet);
            dataReceiveBuffer = new byte[MAX_PACKET_SIZE * 2];
        }
    }

    /**
     * Process a packet received from the client
     * @param packet
     */
    public void processPacket(DatagramPacket packet) {
        String[] data = new String(packet.getData()).trim().split(":");

        InetAddress address = packet.getAddress();
        int port = packet.getPort();

        Log.i("Received message from client: " + address + " " + port);

        int packetID = Integer.parseInt(data[0]);
        Packet.PacketID id = Packet.PacketID.getPacket(packetID);
        switch (id) {
            case LOGINREQUEST:
                Log.i("Received Login Request from client");
                loginRequest(new PacketLoginRequest(data[1]), address, port);
                break;
            case PLAYERMOVE:
                playerMove(new PacketPlayerMove(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                break;
        }
    }

    public void playerMove(PacketPlayerMove movePacket) {
        server.getNetworkServer().notifyAllOfMove(movePacket);
    }

    public void loginRequest(PacketLoginRequest requestPacket, InetAddress address, int port) {
        Log.i("Attempting to log in user " + requestPacket.getUsername());
        if (server.getNetworkServer().isValidName(requestPacket.getUsername())) {

            // Send back successful and notify all other players
            server.getNetworkServer().sendPacket(new PacketLoginSuccess(requestPacket.getUsername(), 300, 300), address, port);
            server.getNetworkServer().notifyAllToCurrentPlayer(address, port);
            server.getNetworkServer().notifyAllOfNewPlayer(requestPacket.getUsername());
            server.getNetworkServer().addConnection(requestPacket.getUsername(), address, port);

            Log.i("User has logged in!");

        } else {
            // Send back login packet (FAILED)!
            server.getNetworkServer().sendPacket(requestPacket, address, port);
            Log.i("User has failed to log in.");
        }
    }
}
