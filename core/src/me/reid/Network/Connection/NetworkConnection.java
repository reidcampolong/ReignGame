package me.reid.Network.Connection;

import me.reid.Network.Packet.Packet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * A middleman to handle input/output between client and server
 *
 * @author Reid
 */
public class NetworkConnection {

    private ConnectionHandler handler;

    private DatagramSocket socket;
    private int MAX_PACKET_SIZE = 1024;
    private byte[] dataReceiveBuffer = new byte[MAX_PACKET_SIZE * 2]; // TODO these numbers can be tweaked

    public NetworkConnection(ConnectionHandler handler) {
        this.handler = handler;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a game packet to the server
     * @param gamePacket
     */
    public void send(Packet gamePacket) {
        byte[] data = gamePacket.getBytes();
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, handler.getServerAddress(), 1069);
            socket.send(packet);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receive a datagram packet from the server
     * @return
     */
    public DatagramPacket receivePacket() {
        dataReceiveBuffer = new byte[MAX_PACKET_SIZE * 2];
        DatagramPacket packet = new DatagramPacket(dataReceiveBuffer, MAX_PACKET_SIZE);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packet;
    }

    public boolean isValid() {
        return socket.isConnected();
    }
}