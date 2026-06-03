package org.example.condicionCarrera;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimuladorCondicionCarrera {

    private final List<String> procesos = new ArrayList<>();

    private final List<Thread> hilosActivos = new ArrayList<>();

    private final Object lock = new Object();

    private volatile boolean simulacionActiva = false;

    private boolean modoSeguro = false;

    private int contador = 0;
    private int incrementosEsperados = 0;

    private Consumer<String> notificador;

    public void setNotificador(Consumer<String> notificador) {
        this.notificador = notificador;
    }

    private void imprimir(String mensaje) {
        if (notificador != null) {
            notificador.accept(mensaje);
        }
    }

    public synchronized void agregarProceso(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido.");
        }

        if (procesos.contains(nombre)) {
            throw new IllegalArgumentException(
                    "Ya existe un proceso con ese nombre."
            );
        }

        procesos.add(nombre);

        imprimir(">>> Proceso agregado: " + nombre);
    }

    public synchronized void iniciarSimulacion(boolean seguro) {

        if (simulacionActiva) {
            return;
        }

        modoSeguro = seguro;
        simulacionActiva = true;

        contador = 0;
        incrementosEsperados = 0;

        hilosActivos.clear();

        imprimir("-----------------------------------");
        imprimir("SIMULACIÓN INICIADA");

        if (modoSeguro) {
            imprimir("MODO SEGURO (synchronized)");
        } else {
            imprimir("MODO INSEGURO (Race Condition)");
        }

        imprimir("-----------------------------------");

        for (String nombre : procesos) {

            Thread hilo = new Thread(() -> ejecutarProceso(nombre));

            hilo.setName(nombre);

            hilosActivos.add(hilo);

            hilo.start();
        }
    }

    private void ejecutarProceso(String nombre) {

        while (simulacionActiva &&
                !Thread.currentThread().isInterrupted()) {

            try {

                if (modoSeguro) {

                    synchronized (lock) {

                        int valorLeido = contador;

                        imprimir("[" + nombre + "] leyó: "
                                + valorLeido);

                        Thread.sleep(200);

                        contador = valorLeido + 1;

                        incrementosEsperados++;

                        imprimir("[" + nombre + "] escribió: "
                                + contador);
                    }

                } else {

                    int valorLeido = contador;

                    imprimir("[" + nombre + "] leyó: "
                            + valorLeido);

                    Thread.sleep(200);

                    contador = valorLeido + 1;

                    incrementosEsperados++;

                    imprimir("[" + nombre + "] escribió: "
                            + contador);
                }

                Thread.sleep(300);

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public synchronized void detenerSimulacion() {

        simulacionActiva = false;

        for (Thread hilo : hilosActivos) {
            hilo.interrupt();
        }

        imprimir("-----------------------------------");
        imprimir("SIMULACIÓN DETENIDA");
        imprimir("Incrementos esperados: "
                + incrementosEsperados);
        imprimir("Valor final contador: "
                + contador);

        if (contador < incrementosEsperados) {

            imprimir("");
            imprimir("⚠ CONDICIÓN DE CARRERA DETECTADA ⚠");

            imprimir(
                    "Incrementos perdidos: "
                            + (incrementosEsperados - contador)
            );
        }

        imprimir("-----------------------------------");

        hilosActivos.clear();
    }

    public synchronized List<String> getProcesos() {
        return new ArrayList<>(procesos);
    }

    public int getContador() {
        return contador;
    }

    public int getIncrementosEsperados() {
        return incrementosEsperados;
    }

    public boolean isModoSeguro() {
        return modoSeguro;
    }
}
