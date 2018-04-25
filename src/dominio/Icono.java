
package dominio;


/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class Icono {
    
    private String nombre;
    private int tamaño;
    private String url;

    public Icono() {
    }
    
    public Icono(String nombre, int tamaño, String url) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.url = url;
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

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
