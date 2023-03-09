package edu.network.lab.client.clientImpl;

import edu.network.lab.client.Client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class UDPClient extends Client {

    public UDPClient(String host, int port, int numOfBool, int numOfLongs) {
        super(host, port, numOfBool, numOfLongs);
    }

    @Override
    public void sendMessage() {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteOutputStream);

            boolean[] booleans = generateBooleans(NUM_OF_BOOL);
            long[] longs = generateLongs(NUM_OF_LONG);

            out.writeInt(booleans.length);
            for (boolean b : booleans) {
                out.writeBoolean(b);
            }

            out.writeInt(longs.length);
            for (long l : longs) {
                out.writeLong(l);
            }

            System.out.println(clientMessageToString("\tData to sent:\n" + dataToString(booleans, longs)));

            byte[] sendData = byteOutputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), PORT);
            clientSocket.send(sendPacket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
