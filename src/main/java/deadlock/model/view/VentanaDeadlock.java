package deadlock.model.view;


import javax.swing.*;
import java.awt.*;

public class VentanaDeadlock extends JFrame {

    private JButton btnIniciar;
    private JTextArea area;

    public VentanaDeadlock() {

        setTitle("Simulación Deadlock");

        setSize(600,400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        btnIniciar =
                new JButton("Iniciar Deadlock");

        area = new JTextArea();

        area.setEditable(false);

        add(btnIniciar, BorderLayout.NORTH);

        add(new JScrollPane(area),
                BorderLayout.CENTER);
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public void agregarMensaje(String mensaje) {

        SwingUtilities.invokeLater(() ->
                area.append(mensaje + "\n"));
    }

    public void limpiar() {
        area.setText("");
    }
}
