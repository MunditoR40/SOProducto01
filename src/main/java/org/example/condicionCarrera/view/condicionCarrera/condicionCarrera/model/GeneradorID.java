package org.example.condicionCarrera.view.condicionCarrera.condicionCarrera.model;

public class GeneradorID {

    private int ultimoID = 0;

    public int generarID() {

        int temp = ultimoID;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ultimoID = temp + 1;

        return ultimoID;
    }

    public synchronized int generarIDSeguro() {

        int temp = ultimoID;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ultimoID = temp + 1;

        return ultimoID;
    }
}
