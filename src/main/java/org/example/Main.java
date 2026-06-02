package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== SIMULACIÓN DE CONDICIÓN DE CARRERA ===");

        GeneradorID generadorCompartido = new GeneradorID();

        Thread servidor1 = new Thread(
                new ClienteHilo(generadorCompartido),
                "Servidor-1");

        Thread servidor2 = new Thread(
                new ClienteHilo(generadorCompartido),
                "Servidor-2");

        servidor1.start();
        servidor2.start();

        try {
            servidor1.join();
            servidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------------");
        System.out.println("Último ID registrado: "
                + generadorCompartido.getUltimoID());
    }
}
