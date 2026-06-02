package deadlock.model;
import deadlock.model.controller.DeadlockController;
import deadlock.model.view.VentanaDeadlock;
public class Main {

    public static void main(String[] args) {

        VentanaDeadlock vista =
                new VentanaDeadlock();

        new DeadlockController(vista);

        vista.setVisible(true);
    }
}
