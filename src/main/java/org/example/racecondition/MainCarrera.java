package org.example.racecondition;

public class MainCarrera {
    public static void main(String[] args) {
        System.out.println("--- SIMULACIÓN DE CONDICIÓN DE CARRERA ---");

        // Creamos una cuenta con 50 dólares
        CuentaBancaria cuentaCompartida = new CuentaBancaria(50);

        // Creamos dos hilos (Esposo y Esposa) que comparten LA MISMA cuenta
        Thread esposo = new Thread(new ClienteHilo(cuentaCompartida, "Hilo-Esposo"));
        Thread esposa = new Thread(new ClienteHilo(cuentaCompartida, "Hilo-Esposa"));

        // Iniciamos los hilos concurrentemente
        esposo.start();
        esposa.start();

        // Esperamos a que ambos terminen
        try {
            esposo.join();
            esposa.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------------");
        System.out.println("SALDO FINAL REAL: " + cuentaCompartida.getSaldo());
        System.out.println("Debería ser matemático... pero ¿lo es?");
    }
}