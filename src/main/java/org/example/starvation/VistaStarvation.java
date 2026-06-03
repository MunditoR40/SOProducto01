package org.example.starvation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaStarvation extends JFrame {

    public JTextField txtNombre;
    public JComboBox<Integer> cbPrioridad;
    public JButton btnAgregar, btnIniciar, btnDetener, btnVolver, btnAyuda;
    public JTextArea txtConsola;
    public JTextPane paneActivos;

    public VistaStarvation() {
        configurarVentana();
        cargarIcono();

        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        add(crearPanelInferior(), BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void configurarVentana() {
        setTitle("Producto de 1° Unidad - SO - Simulación de Inanición");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void cargarIcono() {
        try {
            java.net.URL urlLogo = getClass().getResource("/uns_logo.png");
            if (urlLogo != null) {
                Image imgOriginal = new ImageIcon(urlLogo).getImage();
                setIconImage(imgOriginal.getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            }
        } catch (Exception ignored) {
        }
    }

    private JPanel crearPanelSuperior() {
        JPanel panelTop = new JPanel(new GridLayout(2, 1));

        JLabel lblTitulo = new JLabel(
                "SIMULACIÓN DE INANICIÓN (STARVATION)",
                SwingConstants.CENTER
        );
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(new EmptyBorder(10, 0, 5, 0));

        panelTop.add(lblTitulo);
        panelTop.add(crearPanelControles());

        return panelTop;
    }

    private JPanel crearPanelControles() {
        JPanel panelControles = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5)
        );

        panelControles.add(new JLabel("Proceso:"));

        txtNombre = new JTextField(12);
        panelControles.add(txtNombre);

        panelControles.add(new JLabel("Prioridad (1-10):"));

        Integer[] prioridades = {1,2,3,4,5,6,7,8,9,10};
        cbPrioridad = new JComboBox<>(prioridades);
        cbPrioridad.setSelectedIndex(4);

        panelControles.add(cbPrioridad);

        btnAgregar = new JButton("Agregar Hilo");
        btnAgregar.setForeground(new Color(50, 50, 50));
        btnAgregar.setBackground(Color.WHITE);
        panelControles.add(btnAgregar);

        btnAyuda = new JButton("?");
        panelControles.add(btnAyuda);

        return panelControles;
    }

    private JSplitPane crearPanelCentral() {
        JPanel panelIzquierdo = crearPanelMemoria();
        JPanel panelDerecho = crearPanelConsola();

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                panelIzquierdo,
                panelDerecho
        );

        splitPane.setDividerLocation(260);

        return splitPane;
    }

    private JPanel crearPanelMemoria() {
        paneActivos = new JTextPane();
        paneActivos.setEditable(false);
        paneActivos.setBackground(new Color(245, 245, 245));
        paneActivos.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(
                new JLabel(
                        " Memoria: Procesos Activos",
                        SwingConstants.CENTER
                ),
                BorderLayout.NORTH
        );

        panel.add(new JScrollPane(paneActivos), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelConsola() {
        txtConsola = new JTextArea();
        txtConsola.setEditable(false);
        txtConsola.setBackground(Color.BLACK);
        txtConsola.setForeground(Color.GREEN);
        txtConsola.setFont(new Font("Monospaced", Font.BOLD, 14));

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(
                new JLabel(
                        " Procesador: Consola de Ejecución",
                        SwingConstants.CENTER
                ),
                BorderLayout.NORTH
        );

        panel.add(new JScrollPane(txtConsola), BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panelFooter = new JPanel(new BorderLayout());
        panelFooter.setBorder(new EmptyBorder(10, 15, 10, 15));

        btnVolver = new JButton("← Volver al Menú");
        panelFooter.add(btnVolver, BorderLayout.WEST);

        JPanel panelAccionesCentro = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        btnIniciar = new JButton("Iniciar Simulación");

        btnDetener = new JButton("Detener SO");
        btnDetener.setEnabled(false);

        panelAccionesCentro.add(btnIniciar);
        panelAccionesCentro.add(btnDetener);

        panelFooter.add(panelAccionesCentro, BorderLayout.CENTER);

        return panelFooter;
    }
}