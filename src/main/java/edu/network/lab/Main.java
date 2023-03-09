package edu.network.lab;


import edu.network.lab.client.Client;
import edu.network.lab.client.clientImpl.UDPClient;
import edu.network.lab.server.Server;
import edu.network.lab.server.serverImpl.TCPServer;
import edu.network.lab.client.clientImpl.TCPClient;

import edu.network.lab.server.serverImpl.UDPServer;

public class Main {

    private static final int PORT = 1234;
    private static final String HOST = "localhost";
    private static final int NUM_BOOL = 1;
    private static final int NUM_LONG = 6;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        try {
            Server TCPServer = new TCPServer(PORT);
            Client TCPClient = new TCPClient(HOST, PORT, NUM_BOOL, NUM_LONG);

            Server UDPServer = new UDPServer(PORT, PACKET_SIZE);
            Client UDPClient = new UDPClient(HOST, PORT, NUM_BOOL, NUM_LONG);

            System.out.println("TCP: ");
            serverTest(TCPServer, TCPClient);

            System.out.println("UDP: ");
            serverTest(UDPServer, UDPClient);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serverTest(Server server, Client client) throws InterruptedException {
        Thread serverThread = new Thread(server::startServer);
        serverThread.start();

        Thread.sleep(1000);

        client.sendMessage();
        client.sendMessage();
        client.sendMessage();

        Thread.sleep(20_000);

        server.stopServer();
        serverThread.join();
    }
}