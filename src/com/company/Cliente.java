package com.company;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class Cliente {
    /* Client afegit al grup multicast SrvVelocitats.java que representa un velocímetre */

    private boolean continueRunning = true;
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    NetworkInterface netIf;
    InetSocketAddress group;


    public Cliente(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp,portValue);
    }

    public void runClient() throws IOException{
        DatagramPacket packet;

        byte [] receivedData = new byte[20];

        socket.joinGroup(group,netIf);
        System.out.printf("Connectat a %s:%d%n",group.getAddress(),group.getPort());

        while(continueRunning){
            packet = new DatagramPacket(receivedData, 20);
            socket.setSoTimeout(5000);
            try{
                socket.receive(packet);
                continueRunning = getData(packet.getData(),packet.getLength());
            }catch(SocketTimeoutException e){
                System.out.println("S'ha perdut la connexió amb el servidor.");
                continueRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        socket.leaveGroup(group,netIf);
        socket.close();
    }
    int contador = 0;
    protected  boolean getData(byte[] data, int lenght) {
        boolean ret=true;

        int v = ByteBuffer.wrap(data).getInt();
        String msg = new String(data,0,lenght);

       //String ms2 = ByteBuffer.wrap(data).toString();


        //pintem velocimetre
        //for(int i=0;i<v;i++) System.out.print("#");
        //System.out.print("\n");

        System.out.println(msg + " " + contador);
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if (v==1) ret=false;
        contador++;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente(5557, "224.0.10.15");
        cliente.runClient();
        System.out.println("Parat!");

    }

}
