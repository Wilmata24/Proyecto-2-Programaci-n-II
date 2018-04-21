
package proyectoarmacabezas;


/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class Icono {
    
    private String nombre;
    private int tamaño;
    
    public Icono(String nombre, int tamaño) {
        this.nombre = nombre;
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
