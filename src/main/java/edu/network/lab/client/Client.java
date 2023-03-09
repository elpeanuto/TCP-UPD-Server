package edu.network.lab.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public abstract class Client {

    protected final String HOST;
    protected final int PORT;
    protected final int NUM_OF_BOOL;
    protected final int NUM_OF_LONG;
    private final Random RANDOM = new Random();

    public Client(String host, int port, int numOfBool, int numOfLongs) {
        this.HOST = host;
        this.PORT = port;
        this.NUM_OF_BOOL = numOfBool;
        this.NUM_OF_LONG = numOfLongs;
    }

    public abstract void sendMessage();

    protected String dataToString(boolean[] booleans, long[] longs) {
        return "\tBooleans: " + Arrays.toString(booleans) + "\n\tLongs: " + Arrays.toString(longs)  + "\n";
    }

    protected String clientMessageToString(String str) {
        return this.getClass().getSimpleName() + ":\n" + str;
    }

    protected boolean[] generateBooleans(int size) {
        boolean[] booleans = new boolean[size];

        for (int i = 0; i < size; i++) {
            booleans[i] = RANDOM.nextBoolean();
        }

        return booleans;
    }

    protected long[] generateLongs(int size) {
        long[] longs = new long[size];

        for (int i = 0; i < size; i++) {
            longs[i] = RANDOM.nextLong();
        }

        return longs;
    }
}
