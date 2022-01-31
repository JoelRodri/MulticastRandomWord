package com.company;

public class RandomWord {

    /* Classe que gestiona una dada de velocitat (int) aleatòria, per implementar l'exemple de Multicast:
     * ClientVelocimetre1.java
     * ClientVelocimetre2.java
     * SrvVelocitats.java
     */

    String[] array = new String[10];

    int vel,max;
    public RandomWord() {
        max = 10;
        array[0] = "Joel";
        array[1] = "Rodri";
        array[2] = "Peña";
        array[3] = "Lol";
        array[4] = "Hola";
        array[5] = "que tal";
        array[6] = "nfdekjnge";
        array[7] = "gdgebd";
        array[8] = "Si";
        array[9] = "No";

    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int agafaVelocitat() {
        //setVel((int)(Math.random()*max)+1);
        setVel((int)(Math.random()*max));
        return getVel();
    }

}