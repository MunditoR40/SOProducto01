package org.example.deadlock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaDeadlock extends JFrame {

    public JTextField txtNombreProceso;

    public JButton btnAgregar;
    public JButton btnIniciar;
    public JButton btnDetener;
    public JButton btnRecuperar;
    public JButton btnAyuda;
    public JButton btnVolver;

    public JTextPane paneMemoria;

    public JTextArea txtConsola;

    public VistaDeadlock() {

        setTitle("Simulación de Interbloqueo (Deadlock)");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        // =========================
        // PANEL SUPERIOR
        // =========================

        JPanel panelTop = new JPanel(new GridLayout(2,1));

        JLabel lblTitulo = new JLabel(
                "SIMULACIÓN DE INTERBLOQUEO (DEADLOCK)",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        lblTitulo.setBorder(
                new EmptyBorder(
                        10,0,5,0
                )
        );

        panelTop.add(lblTitulo);

        JPanel panelControles =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        panelControles.add(
                new JLabel("Proceso:")
        );

        txtNombreProceso =
                new JTextField(12);

        panelControles.add(
                txtNombreProceso
        );

        btnAgregar =
                new JButton("Agregar Proceso");

        panelControles.add(
                btnAgregar
        );

        btnAyuda =
                new JButton("?");

        panelControles.add(
                btnAyuda
        );

        btnVolver =
                new JButton("Volver al Menú");

        panelControles.add(
                btnVolver
        );

        panelTop.add(panelControles);

        add(panelTop, BorderLayout.NORTH);

        // =========================
        // PANEL MEMORIA
        // =========================

        paneMemoria = new JTextPane();

        paneMemoria.setEditable(false);

        paneMemoria.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JPanel panelIzquierdo =
                new JPanel(
                        new BorderLayout()
                );

        panelIzquierdo.add(
                new JLabel(
                        "Procesos y Recursos",
                        SwingConstants.CENTER
                ),
                BorderLayout.NORTH
        );

        panelIzquierdo.add(
                new JScrollPane(
                        paneMemoria
                ),
                BorderLayout.CENTER
        );

        // =========================
        // PANEL CONSOLA
        // =========================

        txtConsola =
                new JTextArea();

        txtConsola.setEditable(false);

        txtConsola.setBackground(
                Color.BLACK
        );

        txtConsola.setForeground(
                new Color(0,255,0)
        );

        txtConsola.setFont(
                new Font(
                        "Monospaced",
                        Font.BOLD,
                        13
                )
        );

        JPanel panelDerecho =
                new JPanel(
                        new BorderLayout()
                );

        panelDerecho.add(
                new JLabel(
                        "Consola de Ejecución",
                        SwingConstants.CENTER
                ),
                BorderLayout.NORTH
        );

        panelDerecho.add(
                new JScrollPane(
                        txtConsola
                ),
                BorderLayout.CENTER
        );

        // =========================
        // SPLIT PANEL
        // =========================

        JSplitPane splitPane =
                new JSplitPane(
                        JSplitPane.HORIZONTAL_SPLIT,
                        panelIzquierdo,
                        panelDerecho
                );

        splitPane.setDividerLocation(300);

        add(splitPane, BorderLayout.CENTER);

        // =========================
        // PANEL INFERIOR
        // =========================

        JPanel panelBottom =
                new JPanel(
                        new BorderLayout()
                );

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout()
                );

        btnIniciar =
                new JButton(
                        "Iniciar Simulación"
                );

        btnDetener =
                new JButton(
                        "Detener"
                );

        btnRecuperar =
                new JButton(
                        "Recuperar Deadlock"
                );

        btnRecuperar.setEnabled(false);

        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnRecuperar);

        panelBottom.add(
                panelBotones,
                BorderLayout.CENTER
        );

        add(panelBottom,
                BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    public String getNombreProceso() {

        return txtNombreProceso
                .getText()
                .trim();
    }

    public void limpiarNombre() {

        txtNombreProceso.setText("");
    }
}
