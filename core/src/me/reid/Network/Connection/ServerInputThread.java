package me.reid.Network.Connection;

import me.reid.Game;
import me.reid.Log;
import me.reid.Network.Packet.*;
import me.reid.Screens.PlayScreen;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Thread to listen to input from the server
 */
public class ServerInputThread extends Thread {

    private ConnectionHandler connectionHandler;

    public ServerInputThread(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public void run() {
        while (true) {
            System.out.println("\nWaiting to receive a packet");
            DatagramPacket packet = connectionHandler.receivePacket();
            System.out.println("Packet received");
            processPacket(packet);
        }
    }

    /**
     * Process a datagram packet received from the server
     * @param packet
     */
    public void processPacket(DatagramPacket packet) {
        String[] data = new String(packet.getData()).trim().split(":");

        InetAddress address = packet.getAddress();
        int port = packet.getPort();

        Log.i("Received message from server: " + address + " " + port);

        int packetID = Integer.parseInt(data[0]);
        Packet.PacketID id = Packet.PacketID.getPacket(packetID);
        switch(id) {
            case LOGINREQUEST:
                Log.i("Received Login Request (This means our login failed");
                loginRequest(new PacketLoginRequest(data[1]));
                break;
            case LOGINSUCCESS:
                Log.i("Received Login Success!");
                loginSuccess(new PacketLoginSuccess(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                break;
            case PLAYERLOGIN:
                Log.i("Received player login request");
                playerLogin(new PacketPlayerLogin(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
                break;
            case PLAYERMOVE:
                playerMove(new PacketPlayerMove(data[1], Float.parseFloat(data[2]), Float.parseFloat(data[3])));
        }
    }

    public void loginRequest(PacketLoginRequest packet) {
        Log.i("Failed to log in");
    }

    public void loginSuccess(PacketLoginSuccess packet) {
        Log.i("We have succesfully logged in as " + packet.getUsername());
    }

    public void playerLogin(PacketPlayerLogin packet) {
        Log.i("Recevied new player login for " + packet.getUsername());
        PlayScreen.i().getNetPlayerHandler().createNewPlayer(packet.getUsername(), packet.getX(), packet.getY());
    }

    public void playerMove(PacketPlayerMove packet) {
        Log.i("Received move packet for player " + packet.getUsername() + "  YOU ARE " + ConnectionHandler.username );

        PlayScreen.i().getNetPlayerHandler().getPlayer(packet.getUsername()).setPosition(packet.getX(), packet.getY());
    }


   /* public void loginPlayer(PacketLoginSuccess packet) {
        if(!packet.getUsername().equalsIgnoreCase(connectionHandler.username)) {
            Log.i("Spawning new player");
            Game.getInstance().getPlayerHandler().addPlayer(packet.getUsername());
        }
    }

    public void moveChange(PacketMoveChange packet) {
        System.out.println(packet.getX() + " " + packet.getY());
        Game.getInstance().getPlayerHandler().makeMove(packet);
    }*/
}
