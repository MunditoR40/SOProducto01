package deadlock.model;
import deadlock.model.view.VentanaDeadlock;

public class ProcesoDeadlock implements Runnable {

    private Recurso recurso1;
    private Recurso recurso2;
    private VentanaDeadlock vista;

    public ProcesoDeadlock(
            Recurso recurso1,
            Recurso recurso2,
            VentanaDeadlock vista) {

        this.recurso1 = recurso1;
        this.recurso2 = recurso2;
        this.vista = vista;
    }

    @Override
    public void run() {

        synchronized (recurso1) {

            vista.agregarMensaje(
                    Thread.currentThread().getName()
                            + " obtuvo "
                            + recurso1.getNombre());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            vista.agregarMensaje(
                    Thread.currentThread().getName()
                            + " espera "
                            + recurso2.getNombre());

            synchronized (recurso2) {

                vista.agregarMensaje(
                        Thread.currentThread().getName()
                                + " obtuvo "
                                + recurso2.getNombre());
            }
        }
    }

}
