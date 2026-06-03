package org.example.deadlock;
import java.util.*;
import java.util.function.Consumer;


public class SimuladorDeadlock {
    private final List<String> procesos = new ArrayList<>();

    // Proceso -> Recurso que posee
    private final Map<String, String> recursosAsignados = new HashMap<>();

    // Proceso -> Recurso que espera
    private final Map<String, String> recursosEsperados = new HashMap<>();

    private Consumer<String> notificador;

    private volatile boolean simulacionActiva = false;
    private volatile boolean deadlockDetectado = false;

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
            throw new IllegalArgumentException(
                    "Ingrese un nombre válido."
            );
        }

        if (procesos.contains(nombre)) {
            throw new IllegalArgumentException(
                    "Ya existe un proceso con ese nombre."
            );
        }

        procesos.add(nombre);

        imprimir(">>> Proceso agregado: " + nombre);
    }

    public synchronized List<String> getProcesos() {
        return new ArrayList<>(procesos);
    }

    public boolean isDeadlockDetectado() {
        return deadlockDetectado;
    }

    public Map<String, String> getRecursosAsignados() {
        return recursosAsignados;
    }

    public Map<String, String> getRecursosEsperados() {
        return recursosEsperados;
    }

    public synchronized void iniciarSimulacion() {

        if (simulacionActiva) {
            return;
        }

        if (procesos.size() < 2) {

            imprimir("");
            imprimir("Se necesitan mínimo 2 procesos.");
            return;
        }

        simulacionActiva = true;
        deadlockDetectado = false;

        recursosAsignados.clear();
        recursosEsperados.clear();

        imprimir("");
        imprimir("=== INICIANDO SIMULACIÓN ===");
        imprimir("");

        generarDeadlockCircular();

        detectarDeadlock();
    }

    private void generarDeadlockCircular() {

        int n = procesos.size();

        // Asignación inicial
        for (int i = 0; i < n; i++) {

            String proceso = procesos.get(i);
            String recurso = "R" + (i + 1);

            recursosAsignados.put(
                    proceso,
                    recurso
            );

            imprimir(
                    "[" + proceso + "] obtuvo " + recurso
            );
        }

        imprimir("");

        // Espera circular
        for (int i = 0; i < n; i++) {

            String proceso = procesos.get(i);

            String recursoEsperado =
                    "R" + (((i + 1) % n) + 1);

            recursosEsperados.put(
                    proceso,
                    recursoEsperado
            );

            imprimir(
                    "[" + proceso +
                            "] espera " +
                            recursoEsperado
            );
        }
    }

    private void detectarDeadlock() {

        imprimir("");
        imprimir("Analizando grafo de espera...");
        imprimir("");

        for (String proceso : procesos) {

            String recurso =
                    recursosEsperados.get(proceso);

            String propietario =
                    obtenerPropietario(recurso);

            imprimir(
                    proceso +
                            " espera recurso de " +
                            propietario
            );
        }

        imprimir("");

        deadlockDetectado = true;

        imprimir("⚠ DEADLOCK DETECTADO ⚠");
    }

    private String obtenerPropietario(String recurso) {

        for (Map.Entry<String, String> entry :
                recursosAsignados.entrySet()) {

            if (entry.getValue().equals(recurso)) {
                return entry.getKey();
            }
        }

        return "DESCONOCIDO";
    }

    public synchronized void recuperarDeadlock() {

        if (!deadlockDetectado) {

            imprimir(
                    "No existe deadlock activo."
            );

            return;
        }

        String victima = procesos.get(
                procesos.size() - 1
        );

        imprimir("");
        imprimir("=== RECUPERACIÓN ===");
        imprimir("");

        imprimir(
                "Proceso víctima seleccionado: "
                        + victima
        );

        String recursoLiberado =
                recursosAsignados.remove(victima);

        recursosEsperados.remove(victima);

        procesos.remove(victima);

        imprimir(
                "Proceso finalizado: "
                        + victima
        );

        imprimir(
                "Recurso liberado: "
                        + recursoLiberado
        );

        deadlockDetectado = false;

        imprimir("");
        imprimir(
                "✔ DEADLOCK RESUELTO"
        );
    }

    public synchronized void detenerSimulacion() {

        simulacionActiva = false;

        recursosAsignados.clear();
        recursosEsperados.clear();

        procesos.clear(); // <-- LIMPIA LOS PROCESOS

        deadlockDetectado = false;

        imprimir("");
        imprimir("--- SIMULACIÓN DETENIDA ---");
        imprimir("Memoria liberada.");
    }
}
