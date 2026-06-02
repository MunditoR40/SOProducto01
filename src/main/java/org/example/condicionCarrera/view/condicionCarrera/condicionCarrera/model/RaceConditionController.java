package org.example.condicionCarrera.view.condicionCarrera.condicionCarrera.model;
import org.example.condicionCarrera.view.condicionCarrera.condicionCarrera.model.view.VentanaView;
public class RaceConditionController {
    private VentanaView vista;

    public RaceConditionController(VentanaView vista) {

        this.vista = vista;

        vista.getBtnCarrera()
                .addActionListener(e ->
                        ejecutar(false));

        vista.getBtnSeguro()
                .addActionListener(e ->
                        ejecutar(true));
    }

    private void ejecutar(boolean sincronizado) {

        vista.limpiar();

        GeneradorID generador =
                new GeneradorID();

        Thread t1 =
                new Thread(
                        new ClienteHilo(
                                generador,
                                vista,
                                sincronizado),
                        "Servidor-1");

        Thread t2 =
                new Thread(
                        new ClienteHilo(
                                generador,
                                vista,
                                sincronizado),
                        "Servidor-2");

        t1.start();
        t2.start();
    }
}
