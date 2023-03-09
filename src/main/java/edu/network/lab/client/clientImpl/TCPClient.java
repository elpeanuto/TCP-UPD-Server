package edu.network.lab.client.clientImpl;

import edu.network.lab.client.Client;
import edu.network.lab.server.handler.ClientHandler;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public final class TCPClient extends Client {

    public TCPClient(String host, int port, int numOfBool, int numOfLongs) {
        super(host, port, numOfBool, numOfLongs);
    }

    @Override
    public void sendMessage() {
        try (Socket socket = new Socket(HOST, PORT);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            boolean[] booleans = generateBooleans(NUM_OF_BOOL);
            long[] longs = generateLongs(NUM_OF_LONG);

            out.writeInt(NUM_OF_BOOL);

            for (boolean b : booleans) {
                out.writeBoolean(b);
            }

            out.writeInt(NUM_OF_LONG);

            for (long l : longs) {
                out.writeLong(l);
            }

            System.out.println(clientMessageToString("\tData to sent:\n" + dataToString(booleans, longs)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

