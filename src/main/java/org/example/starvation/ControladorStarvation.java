package org.example.starvation;

import javax.swing.SwingUtilities;

public class ControladorStarvation {
    private SimuladorStarvation modelo;
    private VistaStarvation vista;

    public ControladorStarvation(SimuladorStarvation modelo, VistaStarvation vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Escuchadores de los botones (Eventos)
        this.vista.btnIniciar.addActionListener(e -> iniciar());
        this.vista.btnDetener.addActionListener(e -> detener());
    }

    private void iniciar() {
        // Gestión de botones y limpieza de pantalla
        vista.btnIniciar.setEnabled(false);
        vista.btnDetener.setEnabled(true);
        vista.txtCodicioso.setText("");
        vista.txtIgnorado.setText("");

        // Llamamos a tu modelo y le inyectamos la interfaz "Listener"
        modelo.iniciarInanicion((tipoHilo, mensaje) -> {

            // ¡REGLA DE ORO EN JAVA SWING!
            // Si no usas esto, el SO bloqueará la ventana.
            SwingUtilities.invokeLater(() -> {
                if (tipoHilo.equals("ALTA")) {
                    vista.txtCodicioso.append(mensaje + "\n");
                    // Auto-scroll hacia abajo
                    vista.txtCodicioso.setCaretPosition(vista.txtCodicioso.getDocument().getLength());
                } else if (tipoHilo.equals("BAJA")) {
                    vista.txtIgnorado.append(mensaje + "\n");
                    vista.txtIgnorado.setCaretPosition(vista.txtIgnorado.getDocument().getLength());
                }
            });

        });
    }

    private void detener() {
        modelo.detenerSimulacion(); // Llama al método que creaste en el modelo
        vista.btnIniciar.setEnabled(true);
        vista.btnDetener.setEnabled(false);
        vista.txtCodicioso.append("\n[!] El SO ha detenido el proceso.\n");
        vista.txtIgnorado.append("\n[!] El SO ha detenido el proceso.\n");
    }

    // Punto de arranque (Main)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimuladorStarvation modelo = new SimuladorStarvation();
            VistaStarvation vista = new VistaStarvation();

            // Entregamos el modelo y la vista al controlador
            new ControladorStarvation(modelo, vista);

            vista.setVisible(true);
        });
    }
}