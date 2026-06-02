package org.example.racecondition;

public class ClienteHilo implements Runnable {
    private CuentaBancaria cuenta;
    private String nombre;

    public ClienteHilo(CuentaBancaria cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Cada hilo intentará hacer 3 retiros de 10 dólares
        for (int i = 0; i < 3; i++) {
            cuenta.retirar(10, nombre);
            try {
                Thread.sleep(50); // Pausa entre retiros
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
