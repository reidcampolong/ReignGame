package me.reid.Network;

import java.net.InetAddress;

public class ClientConnection {

    private InetAddress address;
    private int port;

    public ClientConnection(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
