package org.example.condicionCarrera.view;
import org.example.condicionCarrera.view.controller.RaceConditionController;

public class Main {
    public static void main(String[] args) {

        RaceConditionController controller =
                new RaceConditionController();

        controller.iniciarSimulacion();
    }
}
