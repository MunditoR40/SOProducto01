package org.example.starvation;

import javax.swing.*;
import java.awt.*;

public class VistaStarvation extends JFrame {
    public JTextArea txtCodicioso;
    public JTextArea txtIgnorado;
    public JButton btnIniciar;
    public JButton btnDetener;

    public VistaStarvation() {
        setTitle("Simulación de Inanición (Starvation) - SO");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Áreas de Texto ---
        txtCodicioso = new JTextArea();
        txtCodicioso.setEditable(false);
        txtCodicioso.setForeground(new Color(153, 0, 0)); // Rojo oscuro para el peligro

        txtIgnorado = new JTextArea();
        txtIgnorado.setEditable(false);
        txtIgnorado.setForeground(new Color(0, 51, 153)); // Azul para el ignorado

        // --- Paneles Divisorios ---
        JPanel panelTextos = new JPanel(new GridLayout(1, 2, 10, 0));

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(new JLabel("Hilo Codicioso (Prioridad 10)", SwingConstants.CENTER), BorderLayout.NORTH);
        panelIzquierdo.add(new JScrollPane(txtCodicioso), BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(new JLabel("Hilo Ignorado (Prioridad 1)", SwingConstants.CENTER), BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(txtIgnorado), BorderLayout.CENTER);

        panelTextos.add(panelIzquierdo);
        panelTextos.add(panelDerecho);

        // --- Botones ---
        JPanel panelBotones = new JPanel();
        btnIniciar = new JButton("Iniciar Inanición");
        btnDetener = new JButton("Detener");
        btnDetener.setEnabled(false); // Apagado al inicio

        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);

        // Ensamblar todo
        add(panelTextos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setLocationRelativeTo(null); // Centrar en la pantalla
    }
}