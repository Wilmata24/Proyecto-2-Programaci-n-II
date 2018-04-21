
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
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

/**
 *@author Wilmer Mata
 * @author Nicole Fonseca
 */
public class Archivos {
    /*
    * Leer de archivo formato JSON
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
    * Escribir en archivo formato JSON
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

    /*
    * Escribir en archivo formato XML
    */
   
    public Boolean guardarIconos(final String rutaArchivo, final Icono icono) {
	try {

            Path archivo = Paths.get(rutaArchivo);
            BufferedWriter escritorXml = new BufferedWriter(Files.newBufferedWriter(archivo, StandardCharsets.UTF_8));
            
            JAXBContext jaxContext = JAXBContext.newInstance(Icono.class);
            Marshaller marshaller = jaxContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            QName etiquetaRaiz = new QName(icono.getClass().toString());
            JAXBElement<Icono> iconoXml = new JAXBElement<Icono>(etiquetaRaiz, Icono.class, icono);

            marshaller.marshal(iconoXml, escritorXml);

            marshaller.marshal(iconoXml, System.out);
            //cerrar descriptor de archivos
            escritorXml.close();

        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
	}
    
}
