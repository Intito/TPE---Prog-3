package clases;
public class Maquina {
    private String nombre;
    private int cantPiezas;

    public Maquina(String nombre ,int cantPiezas) {
        this.cantPiezas = cantPiezas;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    public void setCantPiezas(int cantPiezas) {
        this.cantPiezas = cantPiezas;
    }

    public String toString() {
        return nombre + "-P" + cantPiezas;
    }
}
