package org.example;

public class GeneradorID {

    private int ultimoID = 0;

    public int generarID() {

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
