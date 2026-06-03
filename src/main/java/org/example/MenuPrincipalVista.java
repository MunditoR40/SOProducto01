package org.example; // O el paquete donde decidas ponerlo

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuPrincipalVista extends JFrame {
    public JButton btnStarvation;
    public JButton btnRaceCondition;
    public JButton btnDeadlock;
    public JButton btnAutores;
    public JButton btnSalir;

    public MenuPrincipalVista() {
        setTitle("Producto 1° Unidad - Sistemas Operativos");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Panel de Título ---
        JLabel lblTitulo = new JLabel("SIMULADOR DE SISTEMAS OPERATIVOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(new EmptyBorder(20, 10, 20, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel de Botones ---
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 10, 15));
        panelBotones.setBorder(new EmptyBorder(10, 50, 20, 50));

        btnStarvation = new JButton("1. Simulación de Inanición (Starvation)");
        btnRaceCondition = new JButton("2. Simulación de Condición de Carrera");
        btnDeadlock = new JButton("3. Simulación de Interbloqueo (Deadlock)");
        btnAutores = new JButton("Créditos / Autores");
        btnSalir = new JButton("Salir del Sistema");

        // Colores para destacar
        btnStarvation.setBackground(new Color(0, 102, 204));
        btnStarvation.setForeground(Color.BLUE);
        btnRaceCondition.setBackground(new Color(0, 102, 204));
        btnRaceCondition.setForeground(Color.BLUE);
        btnDeadlock.setBackground(new Color(0, 102, 204));
        btnDeadlock.setForeground(Color.BLUE);
        btnAutores.setBackground(Color.GREEN);
        btnAutores.setForeground(new Color(6, 218, 6));
        btnSalir.setBackground(new Color(153, 0, 0));
        btnSalir.setForeground(Color.RED);

        panelBotones.add(btnStarvation);
        panelBotones.add(btnRaceCondition);
        panelBotones.add(btnDeadlock);
        panelBotones.add(btnAutores);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
        setLocationRelativeTo(null); // Centrar ventana

        try {
            // Cargamos la imagen desde la carpeta de recursos de forma segura para el JAR
            java.net.URL urlLogo = getClass().getResource("/uns_logo.png");
            if (urlLogo != null) {
                ImageIcon icon = new ImageIcon(urlLogo);
                // Colocamos el icono en la ventana
                this.setIconImage(icon.getImage());
            } else {
                System.out.println("No se pudo encontrar el archivo uns_logo.png en resources");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}