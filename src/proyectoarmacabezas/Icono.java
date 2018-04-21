
package proyectoarmacabezas;


/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class Icono {
    
    private String nombre;
    private int x;
    private int y;
    private int tamaño;
    
    public Icono(String nombre, int x, int y, int tamaño) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.tamaño = tamaño;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

}
