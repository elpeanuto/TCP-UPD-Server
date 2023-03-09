package edu.network.lab.server.handler;

import java.net.Socket;
import java.util.Arrays;

public abstract class ClientHandler implements Runnable{

    public abstract void stopHandler();

    protected String dataToString(boolean[] booleans, long[] longs) {
        return "\tBooleans: " + Arrays.toString(booleans) + "\n\tLongs: " + Arrays.toString(longs) + "\n";
    }

    protected String serverMessageToString(String str) {
        return this.getClass().getSimpleName() + ":\n\t" + str;
    }

}
