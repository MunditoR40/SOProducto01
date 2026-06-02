package org.example.condicionCarrera.view.model;
import org.example.condicionCarrera.view.ConsolaView;

public class ClienteHilo implements Runnable {

    private GeneradorID generador;
    private ConsolaView vista;

    public ClienteHilo(GeneradorID generador,
                       ConsolaView vista) {

        this.generador = generador;
        this.vista = vista;
    }

    @Override
    public void run() {

        for(int i = 0; i < 5; i++) {

            int id = generador.generarID();

            vista.mostrar(
                    Thread.currentThread().getName()
                            + " obtuvo ID: "
                            + id
            );
        }
    }
}
