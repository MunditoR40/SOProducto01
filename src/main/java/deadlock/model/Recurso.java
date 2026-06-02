package deadlock.model;

public class Recurso {
    private String nombre;

    public Recurso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
