package edu.network.lab.server.handler.handlerImpl;

import edu.network.lab.server.handler.ClientHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;

public class UDPHandler extends ClientHandler {
    private final InetAddress clientAddress;
    private final byte[] data;
    private DataInputStream in;

    public UDPHandler(InetAddress clientAddress, byte[] data) {
        super();
        this.clientAddress = clientAddress;
        this.data = data;

    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(new ByteArrayInputStream(data));

            System.out.println(serverMessageToString("Client " + clientAddress + " connected" + "\n"));

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopHandler() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
