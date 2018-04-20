
package proyectoarmacabezas;

import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 *
 * @author Nicole
 */
public class Archivos {
    
    public Imagen leerArchivo(String nombreArchivo) {
        
        try {
			BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
			JsonReader lectorJson = new JsonReader(lector);
			Imagen tmpImagen;
			Gson gson = new Gson();
			tmpImagen = gson.fromJson(lectorJson, null);
			// cerrar descriptores de archivos
			lectorJson.close();
			lector.close();
			// todo salió bien, devuelva la ciudad ya cargada
			return tmpImagen;
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
    
    public Boolean guardarImagen(String rutaArchivo, Image img) {
		try {

			BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo));
			JsonWriter escritorJson = new JsonWriter(escritor);

			//Generar estructura Json
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//guardar a archivo
			gson.toJson(img, img.getClass(), escritorJson);

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
