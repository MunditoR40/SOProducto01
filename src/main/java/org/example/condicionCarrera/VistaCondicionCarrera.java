package org.example.condicionCarrera;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VistaCondicionCarrera extends JFrame {

    public JTextField txtNombreProceso;

    public JButton btnAgregar;
    public JButton btnIniciar;
    public JButton btnDetener;
    public JButton btnAyuda;
    public JButton btnVolver;

    public JCheckBox chkModoSeguro;

    public JTextArea txtConsola;

    public JTextPane paneMemoria;

    public JLabel lblContador;

    public VistaCondicionCarrera() {

        setTitle("Simulación de Condición de Carrera");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // =====================
        // PANEL SUPERIOR
        // =====================

        JPanel panelTop = new JPanel(new GridLayout(2,1));

        JLabel lblTitulo = new JLabel(
                "SIMULACIÓN DE CONDICIÓN DE CARRERA",
                SwingConstants.CENTER
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(new EmptyBorder(10,0,5,0));

        panelTop.add(lblTitulo);

        JPanel panelControles =
                new JPanel(new FlowLayout(
                        FlowLayout.CENTER,
                        10,
                        5
                ));

        panelControles.add(new JLabel("Proceso:"));

        txtNombreProceso = new JTextField(12);

        panelControles.add(txtNombreProceso);

        btnAgregar = new JButton("Agregar Proceso");

        panelControles.add(btnAgregar);

        chkModoSeguro =
                new JCheckBox("Modo Seguro (synchronized)");

        panelControles.add(chkModoSeguro);

        btnAyuda = new JButton("?");

        panelControles.add(btnAyuda);

        btnVolver = new JButton("Volver al Menú");

        panelControles.add(btnVolver);

        panelTop.add(panelControles);

        add(panelTop, BorderLayout.NORTH);

        // =====================
        // PANEL IZQUIERDO
        // =====================

        paneMemoria = new JTextPane();

        paneMemoria.setEditable(false);

        paneMemoria.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        paneMemoria.setBackground(
                new Color(245,245,245)
        );

        lblContador =
                new JLabel(
                        "Contador Compartido: 0",
                        SwingConstants.CENTER
                );

        lblContador.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        JPanel panelIzquierdo =
                new JPanel(new BorderLayout());

        panelIzquierdo.add(
                lblContador,
                BorderLayout.NORTH
        );

        panelIzquierdo.add(
                new JScrollPane(paneMemoria),
                BorderLayout.CENTER
        );

        // =====================
        // PANEL DERECHO
        // =====================

        txtConsola = new JTextArea();

        txtConsola.setEditable(false);

        txtConsola.setBackground(Color.BLACK);

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
                new JPanel(new BorderLayout());

        panelDerecho.add(
                new JLabel(
                        "Consola de Ejecución",
                        SwingConstants.CENTER
                ),
                BorderLayout.NORTH
        );

        panelDerecho.add(
                new JScrollPane(txtConsola),
                BorderLayout.CENTER
        );

        // =====================
        // SPLIT
        // =====================

        JSplitPane splitPane =
                new JSplitPane(
                        JSplitPane.HORIZONTAL_SPLIT,
                        panelIzquierdo,
                        panelDerecho
                );

        splitPane.setDividerLocation(280);

        add(splitPane, BorderLayout.CENTER);

        // =====================
        // PANEL INFERIOR
        // =====================

        JPanel panelBottom =
                new JPanel(new BorderLayout());

        JPanel panelBotones =
                new JPanel(new FlowLayout());

        btnIniciar =
                new JButton("Iniciar Simulación");

        btnDetener =
                new JButton("Detener Simulación");

        btnDetener.setEnabled(false);

        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);

        JLabel lblAutores =
                new JLabel(
                        "Condición de Carrera",
                        SwingConstants.RIGHT
                );

        lblAutores.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        5,
                        15
                )
        );

        panelBottom.add(
                panelBotones,
                BorderLayout.CENTER
        );

        panelBottom.add(
                lblAutores,
                BorderLayout.SOUTH
        );

        add(panelBottom, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

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

    public String getNombreProceso() {
        return txtNombreProceso
                .getText()
                .trim();
    }

    public void limpiarNombre() {
        txtNombreProceso.setText("");
    }

    public void actualizarContador(int valor) {
        lblContador.setText(
                "Contador Compartido: " + valor
        );
    }
}
