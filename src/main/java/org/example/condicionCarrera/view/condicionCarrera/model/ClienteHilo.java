package org.example.condicionCarrera.view.condicionCarrera.model;
import view.VentanaView;

public class ClienteHilo implements Runnable {
    private GeneradorID generador;
    private VentanaView vista;
    private boolean sincronizado;

    public ClienteHilo(
            GeneradorID generador,
            VentanaView vista,
            boolean sincronizado) {

        this.generador = generador;
        this.vista = vista;
        this.sincronizado = sincronizado;
    }

    @Override
    public void run() {

        for(int i = 0; i < 5; i++) {

            int id;

            if(sincronizado)
                id = generador.generarIDSeguro();
            else
                id = generador.generarID();

            vista.agregarMensaje(
                    Thread.currentThread().getName()
                            + " obtuvo ID: "
                            + id);
        }
    }

}
