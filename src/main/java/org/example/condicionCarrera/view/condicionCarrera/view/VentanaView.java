package org.example.condicionCarrera.view.condicionCarrera.view;
import javax.swing.*;
import java.awt.*;

public class VentanaView extends JFrame {

    private JButton btnCarrera;
    private JButton btnSeguro;
    private JTextArea areaTexto;

    public VentanaView() {

        setTitle("Simulación Condición de Carrera");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnCarrera =
                new JButton("Ejecutar SIN sincronización");

        btnSeguro =
                new JButton("Ejecutar CON sincronización");

        areaTexto = new JTextArea();

        areaTexto.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(areaTexto);

        JPanel panelBotones = new JPanel();

        panelBotones.add(btnCarrera);
        panelBotones.add(btnSeguro);

        add(panelBotones, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    public JButton getBtnCarrera() {
        return btnCarrera;
    }

    public JButton getBtnSeguro() {
        return btnSeguro;
    }

    public void agregarMensaje(String mensaje) {

        SwingUtilities.invokeLater(() ->
                areaTexto.append(mensaje + "\n"));
    }

    public void limpiar() {
        areaTexto.setText("");
    }

}
