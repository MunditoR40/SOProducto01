package org.example.starvation;

public class SimuladorStarvation {

    // 1. EL RECURSO COMPARTIDO (El Mutex)
    // Este objeto actuará como el cerrojo que ambos hilos quieren alcanzar.
    private final Object recursoCPU = new Object();

    private boolean simulacionActiva = false;

    // 2. LA INTERFAZ DE COMUNICACIÓN
    public interface StarvationListener {
        void onMensaje(String tipoHilo, String mensaje);
    }

    // 3. LA LÓGICA DEL PROBLEMA
    public void iniciarInanicion(StarvationListener listener) {
        simulacionActiva = true;

        // --- HILO 1: EL PROCESO CODICIOSO (Alta Prioridad) ---
        Thread hiloCodicioso = new Thread(() -> {
            while (simulacionActiva) {
                // Acapara el recurso
                synchronized (recursoCPU) {
                    listener.onMensaje("ALTA", "[Prioridad 10] Hilo Codicioso procesando y usando la CPU...");

                    // Simula un trabajo pesado que toma tiempo
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }

                // Sale del recurso apenas un instante (falsa esperanza para el otro hilo)
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                }
            }
        });
        // ¡LA CLAVE DE TU MODELO!: Le exigimos al SO que le dé toda la preferencia
        hiloCodicioso.setPriority(Thread.MAX_PRIORITY);


        // --- HILO 2: EL PROCESO IGNORADO (Baja Prioridad) ---
        Thread hiloIgnorado = new Thread(() -> {
            int intentos = 0;
            while (simulacionActiva) {
                intentos++;
                listener.onMensaje("BAJA", "[Prioridad 1] Hilo Ignorado intentando entrar... (Intento " + intentos + ")");

                // Intenta entrar al recurso, pero el SO casi nunca le da el paso
                synchronized (recursoCPU) {
                    listener.onMensaje("BAJA", "⭐⭐ ¡MILAGRO! El Hilo Ignorado logró obtener la CPU ⭐⭐");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        // ¡LA CLAVE DE TU MODELO!: Le decimos al SO que este hilo no importa
        hiloIgnorado.setPriority(Thread.MIN_PRIORITY);

        // ¡Que comience la carrera injusta!
        hiloIgnorado.start();
        hiloCodicioso.start();
    }

    public void detenerSimulacion() {
        this.simulacionActiva = false;
    }
}