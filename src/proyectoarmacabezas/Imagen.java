
package proyectoarmacabezas;

import javafx.scene.image.Image;

/**
 *
 * @author Nicole
 */
public class Imagen {
    
    private Image imagen;
    private String nombre;
    private int tamaño;
    
    public Imagen(Image imagen, String nombre, int tamaño) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.tamaño = tamaño;
    }

    /**
     * @return the imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
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
