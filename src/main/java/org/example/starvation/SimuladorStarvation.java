package org.example.starvation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimuladorStarvation {
    private final Object recursoCPU = new Object();
    private volatile boolean simulacionActiva = false;
    private final List<Thread> listaHilos = new ArrayList<>();

    private Consumer<String> notificador;

    public void setNotificador(Consumer<String> notificador) {
        this.notificador = notificador;
    }

    private void imprimir(String msg) {
        if(notificador != null) notificador.accept(msg);
    }

    public synchronized void agregarHilo(String nombre, int prioridad) {
        Thread nuevoHilo = new Thread(() -> {
            int ejecuciones = 0;
            while (simulacionActiva && !Thread.currentThread().isInterrupted()) {
                synchronized (recursoCPU) {
                    ejecuciones++;
                    imprimir("[" + nombre + " - Prio: " + prioridad + "] obtuvo la CPU. Ejecución #" + ejecuciones);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        nuevoHilo.setName(nombre);
        nuevoHilo.setPriority(prioridad);
        listaHilos.add(nuevoHilo);
        imprimir(">>> Hilo Creado: " + nombre + " | Prioridad: " + prioridad);

        if (simulacionActiva) nuevoHilo.start();
    }

    public synchronized void iniciarSimulacion() {
        if (simulacionActiva) return;
        simulacionActiva = true;
        imprimir("--- SIMULACIÓN INICIADA ---");

        for (Thread hilo : listaHilos) {
            if (hilo.getState() == Thread.State.NEW) {
                hilo.start();
            }
        }
    }

    public synchronized void detenerSimulacion() {
        simulacionActiva = false;
        for (Thread hilo : listaHilos) {
            hilo.interrupt();
        }
        listaHilos.clear();
        imprimir("--- SIMULACIÓN DETENIDA Y MEMORIA LIMPIADA ---");
    }

    // Nuevo método para que la vista lea los procesos en memoria
    public synchronized List<Thread> getHilos() {
        return new ArrayList<>(listaHilos);
    }
}