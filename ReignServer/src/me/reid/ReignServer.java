package me.reid;

import me.reid.Network.NetworkServer;


public class ReignServer {

    private String host = "localhost";
    private int port;

    private NetworkServer networkServer;
    private ClientInputThread clientConnectListener;

    public ReignServer(int port) {
        this.port = port;
        Log.i("Creating server");
        networkServer = new NetworkServer(this);
        startConnectListener();
        Log.i("Ready!");
    }

    private void startConnectListener() {
        Log.i("Starting user connection listener");
        this.clientConnectListener = new ClientInputThread(this, networkServer.getSocket());
        this.clientConnectListener.start();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public NetworkServer getNetworkServer() {
        return networkServer;
    }

    public static void main(String[] args) {
        new ReignServer(1069);
    }
}
