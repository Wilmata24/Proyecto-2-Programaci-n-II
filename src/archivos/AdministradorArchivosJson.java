
package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import dominio.Icono;

/**
 *@author Wilmer Mata
 * @author Nicole Fonseca
 */
public class AdministradorArchivosJson {
    /*
    * Leer de archivo
    */
    public Icono leerArchivo(String nombreArchivo) {
      
        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            JsonReader lectorJson = new JsonReader(lector);
            Icono tmpIcono;
            Gson gson = new Gson();
            tmpIcono = gson.fromJson(lectorJson, null);
            // cerrar descriptores de archivos
            lectorJson.close();
            lector.close();
            // todo salió bien, devuelva la ciudad ya cargada
            return tmpIcono;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    /*
    * Escribir en archivo
    */
    public Boolean guardarEnArchivo(String rutaArchivo, Icono icono) {
        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo));
            JsonWriter escritorJson = new JsonWriter(escritor);

            //Generar estructura Json
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //guardar a archivo
            gson.toJson(icono, icono.getClass(), escritorJson);

            // cerrar descriptores de archivo
            escritorJson.close();
            escritor.close();

            // Todo salió bien, notificar éxito
            return true;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            return false;
        }

    }

}
