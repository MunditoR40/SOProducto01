package org.example.condicionCarrera.view.controller;
import org.example.condicionCarrera.view.model.ClienteHilo;
import org.example.condicionCarrera.view.model.GeneradorID;
import org.example.condicionCarrera.view.ConsolaView;

public class RaceConditionController {

    private GeneradorID modelo;
    private ConsolaView vista;

    public RaceConditionController() {

        modelo = new GeneradorID();
        vista = new ConsolaView();
    }

    public void iniciarSimulacion() {

        vista.mostrar(
                "=== SIMULACIÓN DE CONDICIÓN DE CARRERA ===");

        Thread hilo1 =
                new Thread(
                        new ClienteHilo(modelo, vista),
                        "Servidor-1");

        Thread hilo2 =
                new Thread(
                        new ClienteHilo(modelo, vista),
                        "Servidor-2");

        hilo1.start();
        hilo2.start();

        try {

            hilo1.join();
            hilo2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        vista.mostrar("--------------------------------");
        vista.mostrar(
                "Último ID registrado: "
                        + modelo.getUltimoID());
    }
}
