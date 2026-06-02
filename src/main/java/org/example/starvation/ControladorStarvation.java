package org.example.starvation;

import javax.swing.*;
import java.util.List;

public class ControladorStarvation {
    private SimuladorStarvation modelo;
    private VistaStarvation vista;
    private JFrame menuPrincipal;

    public ControladorStarvation(SimuladorStarvation modelo, VistaStarvation vista,JFrame menuPrincipal) {
        this.modelo = modelo;
        this.vista = vista;
        this.menuPrincipal = menuPrincipal;

        // Conexión del Notificador de Consola
        this.modelo.setNotificador(mensaje -> {
            SwingUtilities.invokeLater(() -> {
                vista.txtConsola.append(mensaje + "\n");
                vista.txtConsola.setCaretPosition(vista.txtConsola.getDocument().getLength());
            });
        });

        // Eventos
        this.vista.btnAgregar.addActionListener(e -> agregarNuevoHilo());
        this.vista.btnIniciar.addActionListener(e -> iniciar());
        this.vista.btnDetener.addActionListener(e -> detener());
        this.vista.btnAyuda.addActionListener(e -> mostrarAyuda());
        this.vista.btnVolver.addActionListener(e -> volverAlMenu());

        actualizarPanelActivos(); // Mostrar panel vacío al inicio
    }

    private void volverAlMenu() {
        modelo.detenerSimulacion(); // Detiene los hilos por seguridad
        vista.dispose(); // Cierra la ventana actual de Starvation
        menuPrincipal.setVisible(true); // Vuelve a mostrar el menú principal
    }

    private void mostrarAyuda() {
        String mensaje = "La Inanición (Starvation) ocurre cuando un proceso \n" +
                "de baja prioridad es postergado indefinidamente \n" +
                "porque el planificador del SO siempre prefiere \n" +
                "ejecutar hilos de mayor prioridad.";
        JOptionPane.showMessageDialog(vista, mensaje, "Ayuda: ¿Qué es la Inanición?", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarNuevoHilo() {
        String nombre = vista.getNombreHilo();
        int prioridad = vista.getPrioridadSeleccionada();


        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista,
                    "Escriba un nombre para el hilo.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        modelo.agregarHilo(nombre, prioridad);
        actualizarPanelActivos();
        vista.limpiarNombre();
        vista.enfocarNombre();
    }

    private void iniciar() {
        vista.btnIniciar.setEnabled(false);
        vista.btnDetener.setEnabled(true);
        vista.txtConsola.setText("");
        modelo.iniciarSimulacion();
        actualizarPanelActivos();
    }

    private void detener() {
        modelo.detenerSimulacion();
        vista.btnIniciar.setEnabled(true);
        vista.btnDetener.setEnabled(false);
        actualizarPanelActivos(); // Se vaciará la lista
    }

    // Nuevo método para listar los hilos en el JTextPane
    private void actualizarPanelActivos() {
        List<Thread> hilos = modelo.getHilos();
        StringBuilder sb = new StringBuilder();

        if (hilos.isEmpty()) {
            sb.append("\n [ Memoria Vacía ]\n Ningún proceso cargado.");
        } else {
            sb.append("\n  PID\t| PRIORIDAD\n");
            sb.append("----------------------------\n");
            for (Thread t : hilos) {
                // Usamos el estado del hilo para dar un feedback más técnico
                String estado = t.isAlive() ? "(Ejecutando)" : "(Listo)";
                sb.append("  ").append(t.getName())
                        .append("\t| Nivel ").append(t.getPriority())
                        .append("\n  ").append(estado).append("\n\n");
            }
        }

        SwingUtilities.invokeLater(() -> vista.paneActivos.setText(sb.toString()));
    }


}