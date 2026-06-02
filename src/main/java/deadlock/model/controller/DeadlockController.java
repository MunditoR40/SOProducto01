package deadlock.model.controller;

import deadlock.model.*;
import deadlock.model.view.VentanaDeadlock;

public class DeadlockController {


    private VentanaDeadlock vista;

    public DeadlockController(
            VentanaDeadlock vista) {

        this.vista = vista;

        vista.getBtnIniciar()
                .addActionListener(e -> iniciar());
    }

    private void iniciar() {

        vista.limpiar();

        Recurso A = new Recurso("Recurso A");
        Recurso B = new Recurso("Recurso B");

        Thread proceso1 =
                new Thread(
                        new ProcesoDeadlock(
                                A,
                                B,
                                vista),
                        "Proceso-1");

        Thread proceso2 =
                new Thread(
                        new ProcesoDeadlock(
                                B,
                                A,
                                vista),
                        "Proceso-2");

        proceso1.start();
        proceso2.start();
    }
}
