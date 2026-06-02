package org.example.racecondition;

public class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    // METODO VULNERABLE A CONDICIÓN DE CARRERA
    // Para solucionarlo después, solo tendrás que agregar la palabra "synchronized"
    // Ejemplo: public synchronized void retirar(int monto, String nombreHilo)
    public void retirar(int monto, String nombreHilo) {
        if (saldo >= monto) {
            System.out.println(nombreHilo + " verificó el saldo. Saldo disponible: " + saldo);

            // Simulamos un pequeño retraso del Sistema Operativo (Cambio de contexto)
            // ¡Aquí es donde ocurre el desastre si no hay sincronización!
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            saldo = saldo - monto;
            System.out.println(nombreHilo + " retiró " + monto + ". Saldo restante: " + saldo);
        } else {
            System.out.println(nombreHilo + " intentó retirar, pero no hay saldo suficiente.");
        }
    }
}
