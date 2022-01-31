package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;

public class Servidor {
    /* Servidor Multicast que ens comunica la velocitat que porta d'un objecte */

    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    RandomWord random;

    public Servidor(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        random = new RandomWord();
    }

    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;
        String word;
        while(continueRunning){

            //sendingData = ByteBuffer.allocate(4).putInt(random.agafaVelocitat()).array();

            word = random.array[random.agafaVelocitat()];
            sendingData = word.getBytes();


            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            socket.send(packet);
            //socket.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        //Canvieu la X.X per un número per formar un IP.
        //Que no sigui la mateixa que la d'un altre company
        Servidor srvVel = new Servidor(5557, "224.0.10.15");
        srvVel.runServer();
        System.out.println("Parat!");

    }

}