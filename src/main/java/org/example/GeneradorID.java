package org.example;

public class GeneradorID {

    private int ultimoID = 0;


    // synchronized  para la sincronizacion de la asignacion de id

    public synchronized  int generarID() {

        int nuevoID = ultimoID;

        System.out.println(Thread.currentThread().getName()
                + " leyó el ID: " + nuevoID);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ultimoID = nuevoID + 1;

        return ultimoID;
    }

    public int getUltimoID() {
        return ultimoID;
    }

}
