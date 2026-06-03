package org.example.condicionCarrera;
import javax.swing.*;
import java.util.List;

public class ControladorCondicionCarrera {
    private SimuladorCondicionCarrera modelo;
    private VistaCondicionCarrera vista;
    private JFrame menuPrincipal;

    public ControladorCondicionCarrera(
            SimuladorCondicionCarrera modelo,
            VistaCondicionCarrera vista,
            JFrame menuPrincipal) {

        this.modelo = modelo;
        this.vista = vista;
        this.menuPrincipal = menuPrincipal;

        modelo.setNotificador(mensaje -> {

            SwingUtilities.invokeLater(() -> {

                vista.txtConsola.append(mensaje + "\n");

                vista.txtConsola.setCaretPosition(
                        vista.txtConsola.getDocument().getLength()
                );

                vista.actualizarContador(
                        modelo.getContador()
                );
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

        vista.btnAyuda.addActionListener(
                e -> mostrarAyuda()
        );

        vista.btnVolver.addActionListener(
                e -> volverMenu()
        );


        vista.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {

                modelo.detenerSimulacion();

                menuPrincipal.setVisible(true);
            }
        });


        actualizarMemoria();
    }

    private void agregarProceso() {

        String nombre = vista.getNombreProceso();

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

            actualizarMemoria();

            vista.limpiarNombre();

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

        boolean seguro =
                vista.chkModoSeguro.isSelected();

        modelo.iniciarSimulacion(seguro);

        vista.btnIniciar.setEnabled(false);
        vista.btnDetener.setEnabled(true);
    }

    private void detener() {

        modelo.detenerSimulacion();

        vista.btnIniciar.setEnabled(true);
        vista.btnDetener.setEnabled(false);

        actualizarMemoria();
    }

    private void volverMenu() {

        modelo.detenerSimulacion();

        vista.dispose();

        menuPrincipal.setVisible(true);
    }

    private void mostrarAyuda() {

        String mensaje =
                "La Condición de Carrera ocurre cuando\n" +
                        "dos o más procesos acceden a un\n" +
                        "recurso compartido al mismo tiempo\n" +
                        "sin sincronización.\n\n" +
                        "Modo Inseguro:\n" +
                        "Puede producir resultados incorrectos.\n\n" +
                        "Modo Seguro:\n" +
                        "Usa synchronized para evitar el problema.";

        JOptionPane.showMessageDialog(
                vista,
                mensaje,
                "¿Qué es una Condición de Carrera?",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizarMemoria() {

        List<String> procesos =
                modelo.getProcesos();

        StringBuilder sb =
                new StringBuilder();

        sb.append("PROCESOS CARGADOS\n");
        sb.append("----------------------\n\n");

        if (procesos.isEmpty()) {

            sb.append("Memoria vacía");

        } else {

            for (String proceso : procesos) {

                sb.append("• ")
                        .append(proceso)
                        .append("\n");
            }
        }

        vista.paneMemoria.setText(
                sb.toString()
        );
    }
}
