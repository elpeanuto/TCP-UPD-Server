package edu.network.lab.server;

import edu.network.lab.server.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public abstract class Server {
    protected final int port;
    protected final List<ClientHandler> clientHandlerThreads = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    public abstract void startServer();

    public abstract void stopServer();
}

