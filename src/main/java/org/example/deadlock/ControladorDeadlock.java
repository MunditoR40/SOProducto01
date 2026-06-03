package org.example.deadlock;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class ControladorDeadlock {

    private SimuladorDeadlock modelo;
    private VistaDeadlock vista;
    private JFrame menuPrincipal;

    public ControladorDeadlock(
            SimuladorDeadlock modelo,
            VistaDeadlock vista,
            JFrame menuPrincipal) {

        this.modelo = modelo;
        this.vista = vista;
        this.menuPrincipal = menuPrincipal;

        modelo.setNotificador(mensaje -> {

            SwingUtilities.invokeLater(() -> {

                vista.txtConsola.append(
                        mensaje + "\n"
                );

                vista.txtConsola.setCaretPosition(
                        vista.txtConsola
                                .getDocument()
                                .getLength()
                );

                actualizarMemoria();

                if (modelo.isDeadlockDetectado()) {
                    vista.btnRecuperar.setEnabled(true);
                }
            });
        });

        vista.btnAgregar.addActionListener(
                e -> agregarProceso()
        );

        vista.btnIniciar.addActionListener(
                e -> iniciar()
        );

        vista.btnDetener.addActionListener(
                e -> detener()
        );

        vista.btnRecuperar.addActionListener(
                e -> recuperar()
        );

        vista.btnAyuda.addActionListener(
                e -> mostrarAyuda()
        );

        vista.btnVolver.addActionListener(
                e -> volverMenu()
        );

        actualizarMemoria();
    }

    private void agregarProceso() {

        String nombre =
                vista.getNombreProceso();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ingrese un nombre para el proceso.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            modelo.agregarProceso(nombre);

            vista.limpiarNombre();

            actualizarMemoria();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void iniciar() {

        vista.txtConsola.setText("");

        modelo.iniciarSimulacion();

        vista.btnIniciar.setEnabled(false);
    }

    private void detener() {

        modelo.detenerSimulacion();

        vista.btnIniciar.setEnabled(true);
        vista.btnRecuperar.setEnabled(false);

        actualizarMemoria();
    }

    private void recuperar() {

        modelo.recuperarDeadlock();

        vista.btnRecuperar.setEnabled(false);

        actualizarMemoria();
    }

    private void volverMenu() {

        modelo.detenerSimulacion();

        vista.dispose();

        menuPrincipal.setVisible(true);
    }

    private void mostrarAyuda() {

        String mensaje =
                "Un Deadlock ocurre cuando\n" +
                        "dos o más procesos esperan\n" +
                        "recursos que están ocupados\n" +
                        "por otros procesos.\n\n" +
                        "Ninguno puede continuar.\n\n" +
                        "Esta simulación muestra:\n" +
                        "• Asignación de recursos\n" +
                        "• Espera circular\n" +
                        "• Detección\n" +
                        "• Recuperación";

        JOptionPane.showMessageDialog(
                vista,
                mensaje,
                "Ayuda - Deadlock",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizarMemoria() {

        StringBuilder sb =
                new StringBuilder();

        List<String> procesos =
                modelo.getProcesos();

        sb.append("PROCESOS\n");
        sb.append("------------------\n");

        if (procesos.isEmpty()) {

            sb.append("Sin procesos\n");

        } else {

            for (String p : procesos) {

                sb.append("• ")
                        .append(p)
                        .append("\n");
            }
        }

        sb.append("\n");

        sb.append("RECURSOS ASIGNADOS\n");
        sb.append("------------------\n");

        Map<String,String> recursos =
                modelo.getRecursosAsignados();

        if (recursos.isEmpty()) {

            sb.append("Sin recursos\n");

        } else {

            for (Map.Entry<String,String> entry :
                    recursos.entrySet()) {

                sb.append(entry.getKey())
                        .append(" → ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }

        vista.paneMemoria.setText(
                sb.toString()
        );
    }
}
