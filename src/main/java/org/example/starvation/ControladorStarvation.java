package org.example.starvation;

import javax.swing.*;
import java.util.List;

public class ControladorStarvation {
    private final SimuladorStarvation modelo;
    private final VistaStarvation vista;
    private final JFrame menuPrincipal;

    public ControladorStarvation(SimuladorStarvation modelo, VistaStarvation vista, JFrame menuPrincipal) {
        this.modelo = modelo;
        this.vista = vista;
        this.menuPrincipal = menuPrincipal;

        this.modelo.setNotificador(mensaje -> {
            SwingUtilities.invokeLater(() -> {
                vista.txtConsola.append(mensaje + "\n");
                vista.txtConsola.setCaretPosition(vista.txtConsola.getDocument().getLength());
            });
        });

        this.vista.btnAgregar.addActionListener(e -> agregarNuevoHilo());
        this.vista.btnIniciar.addActionListener(e -> iniciar());
        this.vista.btnDetener.addActionListener(e -> detener());
        this.vista.btnAyuda.addActionListener(e -> mostrarAyuda());

        this.vista.btnVolver.addActionListener(e -> {
            modelo.detenerSimulacion();
            vista.dispose();
            menuPrincipal.setVisible(true);
        });

        actualizarPanelActivos();
    }

    private void agregarNuevoHilo() {
        String nombre = vista.txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Escriba un nombre para el hilo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (modelo.existeHilo(nombre)) {
            JOptionPane.showMessageDialog(vista, "Error: El proceso '" + nombre + "' ya se encuentra cargado.\nAsigne un identificador único.", "Error de Nombre", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int prioridad = (Integer) vista.cbPrioridad.getSelectedItem();
        modelo.agregarHilo(nombre, prioridad);

        actualizarPanelActivos();
        vista.txtNombre.setText("");
        vista.txtNombre.requestFocus();
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
        actualizarPanelActivos();
    }

    private void mostrarAyuda() {
        String mensaje = "La Inanición (Starvation) ocurre cuando un proceso de baja prioridad \n" +
                "es postergado indefinidamente en la cola de Listos, porque el planificador \n" +
                "siempre le otorga el uso de la CPU a hilos de mayor jerarquía.\n\n" +
                "En esta simulación, observarás cómo los procesos de Prioridad 1 jamás\n" +
                "logran imprimir en consola si hay procesos de Prioridad 10 acaparando el recurso.";
        JOptionPane.showMessageDialog(vista, mensaje, "Marco Teórico: Starvation", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarPanelActivos() {
        List<Thread> hilos = modelo.getHilos();
        StringBuilder sb = new StringBuilder();
        if (hilos.isEmpty()) {
            sb.append("\n [ Memoria Vacía ]\n Ningún proceso cargado.");
        } else {
            sb.append("\n  PROCESO\t| PRIORIDAD\n");
            sb.append("----------------------------\n");
            for (Thread t : hilos) {
                String estado = t.isAlive() ? "(Ejecutando)" : "(Listo)";
                sb.append("  ").append(t.getName())
                        .append("\t| Nivel ").append(t.getPriority())
                        .append("\n  ").append(estado).append("\n\n");
            }
        }
        SwingUtilities.invokeLater(() -> vista.paneActivos.setText(sb.toString()));
    }
}