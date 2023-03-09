package edu.network.lab.server.handler.handlerImpl;

import edu.network.lab.server.handler.ClientHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class TCPHandler extends ClientHandler {

    private final Socket socket;

    public TCPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            System.out.println(serverMessageToString("Client " + socket.getInetAddress() + " connected" + "\n"));

            int boolSize = in.readInt();
            boolean[] booleans = new boolean[boolSize];

            for (int i = 0; i < boolSize; i++) {
                booleans[i] = in.readBoolean();
            }

            int longSize = in.readInt();
            long[] longs = new long[longSize];

            for (int i = 0; i < longSize; i++) {
                longs[i] = in.readLong();
            }

            System.out.println(serverMessageToString("Received data:\n" + dataToString(booleans, longs)));

        } catch (SocketException e) {
            System.out.println("Connection with: " + this.socket.getInetAddress() + " abandoned!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopHandler() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}