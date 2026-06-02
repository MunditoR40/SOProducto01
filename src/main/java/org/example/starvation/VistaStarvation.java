package org.example.starvation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaStarvation extends JFrame {
    public JTextField txtNombre;
    public JComboBox<Integer> cbPrioridad;
    public JButton btnAgregar;
    public JButton btnIniciar;
    public JButton btnDetener;
    public JTextArea txtConsola;
    public JTextPane paneActivos; // Nuevo componente solicitado
    public JButton btnAyuda;

    public VistaStarvation() {
        setTitle("Producto de 1° Unidad - Sistemas Operativos - Simulación de Inanición");        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- Panel Superior: Título y Entradas ---
        JPanel panelTop = new JPanel(new GridLayout(2, 1));

        JLabel lblTitulo = new JLabel("SIMULACIÓN DE INANICIÓN (STARVATION)", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 5, 0));
        panelTop.add(lblTitulo);

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelControles.add(new JLabel("Proceso:"));
        txtNombre = new JTextField(12);
        panelControles.add(txtNombre);

        panelControles.add(new JLabel("Prioridad (1-10):"));
        Integer[] prioridades = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        cbPrioridad = new JComboBox<>(prioridades);
        cbPrioridad.setSelectedIndex(4);
        panelControles.add(cbPrioridad);

        btnAgregar = new JButton("Agregar Hilo");
        btnAgregar.setBackground(Color.BLUE); // Color más oscuro
        btnAgregar.setForeground(Color.BLACK); // Letras blancas
        panelControles.add(btnAgregar);

        btnAyuda = new JButton("?"); // Botón pequeño de ayuda
        panelControles.add(btnAyuda);

        panelTop.add(panelControles);
        add(panelTop, BorderLayout.NORTH);

        // --- Panel Central: Split (Izquierda Activos / Derecha Consola) ---
        paneActivos = new JTextPane();
        paneActivos.setEditable(false);
        paneActivos.setBackground(new Color(245, 245, 245));
        paneActivos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.add(new JLabel(" Memoria: Procesos Activos", SwingConstants.CENTER), BorderLayout.NORTH);
        panelIzquierdo.add(new JScrollPane(paneActivos), BorderLayout.CENTER);

        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setBackground(Color.BLACK);
        txtConsola.setForeground(new Color(0, 255, 0));
        txtConsola.setFont(new Font("Monospaced", Font.BOLD, 14));
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(new JLabel(" Procesador: Consola de Ejecución", SwingConstants.CENTER), BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(txtConsola), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, panelDerecho);
        splitPane.setDividerLocation(250); // Ancho del panel izquierdo
        add(splitPane, BorderLayout.CENTER);

        // --- Panel Inferior: Botones y Autores ---
        JPanel panelBottom = new JPanel(new BorderLayout());

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIniciar = new JButton("Iniciar Simulación");
        btnDetener = new JButton("Detener SO");
        btnDetener.setEnabled(false);
        panelAcciones.add(btnIniciar);
        panelAcciones.add(btnDetener);

        JLabel lblAutores = new JLabel("Autores: Rojas Leon & Liñan Briones", SwingConstants.RIGHT);
        lblAutores.setFont(new Font("Arial", Font.ITALIC, 12));
        lblAutores.setBorder(new EmptyBorder(0, 0, 5, 15));

        panelBottom.add(panelAcciones, BorderLayout.CENTER);
        panelBottom.add(lblAutores, BorderLayout.SOUTH);

        add(panelBottom, BorderLayout.SOUTH);
        setLocationRelativeTo(null);


    }

    public String getNombreHilo() {
        return txtNombre.getText().trim();
    }

    public int getPrioridadSeleccionada() {
        return (Integer) cbPrioridad.getSelectedItem();
    }

    public void limpiarNombre() {
        txtNombre.setText("");
    }

    public void enfocarNombre() {
        txtNombre.requestFocus();
    }
}