package org.example;

public class ClienteHilo implements Runnable {

    private GeneradorID generador;

    public ClienteHilo(GeneradorID generador) {
        this.generador = generador;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            int id = generador.generarID();

            System.out.println(Thread.currentThread().getName()
                    + " se asigno el ID: " + id);
        }
    }


}
