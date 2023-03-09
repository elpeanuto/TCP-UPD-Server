package edu.network.lab.server.serverImpl;

import edu.network.lab.server.Server;
import edu.network.lab.server.handler.ClientHandler;
import edu.network.lab.server.handler.handlerImpl.TCPHandler;
import edu.network.lab.server.handler.handlerImpl.UDPHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Server {

    private final int PACKAGE_SIZE;
    private DatagramSocket serverSocket;

    public UDPServer(int port, int packageSize) {
        super(port);
        PACKAGE_SIZE = packageSize;
    }

    @Override
    public void startServer() {
        try {
            serverSocket = new DatagramSocket(port);

            while (!Thread.currentThread().isInterrupted()) {
                byte[] receiveBuffer = new byte[PACKAGE_SIZE];

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                byte[] data = receivePacket.getData();

                ClientHandler handler = new UDPHandler(clientAddress, data);
                clientHandlerThreads.add(handler);
                new Thread(handler).start();
            }
        } catch (SocketException e) {
            System.out.println("Server stopped!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    @Override
    public void stopServer() {
        for (ClientHandler clientThread : clientHandlerThreads) {
            clientThread.stopHandler();
        }
        serverSocket.close();
    }
}
