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

    public synchronized boolean existeHilo(String nombre) {
        for (Thread t : listaHilos) {
            if (t.getName().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void agregarHilo(final String nombre, final int prioridad) {
        Thread nuevoHilo = new Thread(() -> {
            int ejecuciones = 0;
            while (simulacionActiva && !Thread.currentThread().isInterrupted()) {
                synchronized (recursoCPU) {
                    ejecuciones++;
                    String tag = (prioridad >= 9) ? "[ALTA]" : "[BAJA]";
                    imprimir(tag + " " + nombre + " (Prio: " + prioridad + ") obtuvo la CPU. Ejecución #" + ejecuciones);

                    // Bucle agresivo para evitar que el SO intercale fácilmente
                    for(int i=0; i<1000000; i++) Math.sin(i);
                }
                Thread.yield(); // Cede el paso pero pide volver de inmediato
            }
        });

        nuevoHilo.setName(nombre);
        nuevoHilo.setPriority(prioridad);
        listaHilos.add(nuevoHilo);
        imprimir(">>> Proceso cargado en memoria: " + nombre + " [Prioridad: " + prioridad + "]");
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