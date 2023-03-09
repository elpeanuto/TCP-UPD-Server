package edu.network.lab.server.serverImpl;

import edu.network.lab.server.Server;
import edu.network.lab.server.handler.ClientHandler;
import edu.network.lab.server.handler.handlerImpl.TCPHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

public class TCPServer extends Server {

    private ServerSocket serverSocket;

    public TCPServer(int port) {
        super(port);
    }

    @Override
    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);

            while (!Thread.currentThread().isInterrupted()) {
                ClientHandler handler = new TCPHandler(serverSocket.accept());
                clientHandlerThreads.add(handler);
                new Thread(handler).start();
            }
        } catch (SocketException e) {
            System.out.println("Server stopped!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopServer() {
        try {
            for (ClientHandler clientThread : clientHandlerThreads) {
                clientThread.stopHandler();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
