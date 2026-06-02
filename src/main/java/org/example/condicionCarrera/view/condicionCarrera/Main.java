package org.example.condicionCarrera.view.condicionCarrera;

import org.example.condicionCarrera.view.condicionCarrera.view.VentanaView;

public class Main {

           public static void main(String[] args) {

            VentanaView vista =
                    new VentanaView();

            new RaceConditionController(vista);

            vista.setVisible(true);

        }
}
