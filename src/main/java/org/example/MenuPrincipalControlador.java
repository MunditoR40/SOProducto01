package org.example;

import org.example.starvation.ControladorStarvation;
import org.example.starvation.SimuladorStarvation;
import org.example.starvation.VistaStarvation;
// condicion carrera

import org.example.condicionCarrera.ControladorCondicionCarrera;
import org.example.condicionCarrera.SimuladorCondicionCarrera;
import org.example.condicionCarrera.VistaCondicionCarrera;

//deadlock

import org.example.deadlock.ControladorDeadlock;
import org.example.deadlock.SimuladorDeadlock;
import org.example.deadlock.VistaDeadlock;

import javax.swing.*;

public class MenuPrincipalControlador {
    private MenuPrincipalVista vistaMenu;

    public MenuPrincipalControlador(MenuPrincipalVista vistaMenu) {
        this.vistaMenu = vistaMenu;

        // Eventos de los botones
        this.vistaMenu.btnStarvation.addActionListener(e -> abrirStarvation());

        this.vistaMenu.btnRaceCondition.addActionListener(
                (e) -> this.abrirCondicionCarrera()
        );
        this.vistaMenu.btnDeadlock.addActionListener(
                (e) -> this.abrirDeadlock()
        );
        this.vistaMenu.btnAutores.addActionListener(e -> mostrarAutores());
        this.vistaMenu.btnSalir.addActionListener(e -> System.exit(0));
    }

    private void abrirStarvation() {
        // Ocultamos el menú principal
        vistaMenu.setVisible(false);

        // Instanciamos el MVC de tu módulo
        SimuladorStarvation modelo = new SimuladorStarvation();
        VistaStarvation vista = new VistaStarvation();

        // LE PASAMOS LA VISTA DEL MENÚ AL CONTROLADOR para que sepa a dónde volver
        new ControladorStarvation(modelo, vista, vistaMenu);

        vista.setVisible(true);
    }

    private void abrirCondicionCarrera() {

        this.vistaMenu.setVisible(false);

        SimuladorCondicionCarrera modelo =
                new SimuladorCondicionCarrera();

        VistaCondicionCarrera vista =
                new VistaCondicionCarrera();

        new ControladorCondicionCarrera(
                modelo,
                vista,
                this.vistaMenu
        );

        vista.setVisible(true);
    }


    private void abrirDeadlock() {

        this.vistaMenu.setVisible(false);

        SimuladorDeadlock modelo =
                new SimuladorDeadlock();

        VistaDeadlock vista =
                new VistaDeadlock();

        new ControladorDeadlock(
                modelo,
                vista,
                this.vistaMenu
        );

        vista.setVisible(true);
    }


    private void mostrarAutores() {
        String autores = "--- EQUIPO DE DESARROLLO ---\n\n" +
                "• Alejos Ponce, Erick Segundo (0202314002)\n" +
                "• Liñan Briones, Juan Carlos (0202414022) - [Dev]\n" +
                "• Rojas León, Angel Edmundo (0202414044) - [Dev]\n" +
                "• Salinas Pinedo, Carlos Yampier (0202414047)\n\n" +
                "Universidad Nacional del Santa - 2026";
        JOptionPane.showMessageDialog(vistaMenu, autores, "Autores del Proyecto", JOptionPane.PLAIN_MESSAGE);
    }

    // EL NUEVO MAIN GENERAL DE TU APLICACIÓN
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalVista menu = new MenuPrincipalVista();
            new MenuPrincipalControlador(menu);
            menu.setVisible(true);
        });
    }
}