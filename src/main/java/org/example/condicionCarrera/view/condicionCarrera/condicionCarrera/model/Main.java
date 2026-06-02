package org.example.condicionCarrera.view.condicionCarrera.condicionCarrera.model;

import org.example.condicionCarrera.view.condicionCarrera.condicionCarrera.model.view.VentanaView;

public class Main {

           public static void main(String[] args) {

            VentanaView vista =
                    new VentanaView();

            new RaceConditionController(vista);

            vista.setVisible(true);

        }
}
