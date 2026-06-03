package org.example.starvation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimuladorStarvation {
    private final Object recursoCPU = new Object();
    private volatile boolean simulacionActiva = false;
    private final List<Thread> listaHilos = new ArrayList<>();
    private Consumer<String> notificador;
    private volatile int prioridadMaxima = 0;

    public void setNotificador(Consumer<String> notificador) {
        this.notificador = notificador;
    }

    private void imprimir(String msg) {
        if(notificador != null) notificador.accept(msg);
    }

    public synchronized boolean existeHilo(String nombre) {
        for (Thread t : listaHilos) {
            if (t.getName().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void agregarHilo(final String nombre, final int prioridad) {
        prioridadMaxima = Math.max(prioridadMaxima, prioridad);
        if (existeHilo(nombre)) {
            throw new IllegalArgumentException(
                    "Ya existe un proceso con el nombre: " + nombre
            );
        }

        prioridadMaxima = Math.max(prioridadMaxima, prioridad);

        Thread nuevoHilo = new Thread(() -> {

            int ejecuciones = 0;

            while (simulacionActiva &&
                    !Thread.currentThread().isInterrupted()) {

                // Simulación de inanición
                if (prioridad < prioridadMaxima) {

                    imprimir("[ESPERANDO] " + nombre +
                            " (Prio: " + prioridad +
                            ") no obtiene CPU porque existe un proceso con prioridad mayor.");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    continue;
                }

                synchronized (recursoCPU) {

                    ejecuciones++;

                    imprimir("[EJECUTANDO] " + nombre +
                            " (Prio: " + prioridad +
                            ") obtuvo la CPU. Ejecución #" +
                            ejecuciones);

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }

        });

        nuevoHilo.setName(nombre);
        nuevoHilo.setPriority(prioridad);

        listaHilos.add(nuevoHilo);

        imprimir(">>> Proceso cargado en memoria: "
                + nombre +
                " [Prioridad: " + prioridad + "]");

        if (simulacionActiva) {
            nuevoHilo.start();
        }
    }

    public synchronized void iniciarSimulacion() {
        if (simulacionActiva) return;
        simulacionActiva = true;
        imprimir("--- SIMULACIÓN INICIADA ---");

        for (Thread hilo : listaHilos) {
            if (hilo.getState() == Thread.State.NEW) hilo.start();
        }
    }

    public synchronized void detenerSimulacion() {
        simulacionActiva = false;
        for (Thread hilo : listaHilos) hilo.interrupt();
        listaHilos.clear();
        imprimir("--- SIMULACIÓN DETENIDA Y LIMPIADA ---");
    }

    public synchronized List<Thread> getHilos() {
        return new ArrayList<>(listaHilos);
    }
}