
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
import java.io.File;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *@author Wilmer Mata
 * @author Nicole Fonseca
 */
public class AdministradorArchivos {
    /*
    * Leer de archivo
    */
    public Icono leerArchivoJson(String nombreArchivo) {
      
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
    public Boolean guardarEnArchivo(String rutaArchivo, String archivo) {
        try {

            BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo));
            JsonWriter escritorJson = new JsonWriter(escritor);

            //Generar estructura Json
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            //guardar a archivo
            gson.toJson(archivo, archivo.getClass(), escritorJson);

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
    
    public ArrayList<Icono> leerArchivo() throws Exception{
        File archivo = new File("iconos.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(archivo);
        document.getDocumentElement().normalize();
        
        ArrayList<Icono> lista = new ArrayList();
        NodeList nodeList = document.getElementsByTagName("icono");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Icono icono = new Icono();
                    icono.setTamaño(Integer.parseInt(element.getElementsByTagName("tamano").item(0).getTextContent().toString()));
                    icono.setNombre((element.getElementsByTagName("nombre").item(0).getTextContent()).toString());
                    icono.setUrl((element.getElementsByTagName("url").item(0).getTextContent()).toString());
                    
                    lista.add(icono);
//                    System.out.println("tamaño: " + element.getElementsByTagName("tamano").item(0).getTextContent());
//                    System.out.println("Nombre: " + element.getElementsByTagName("nombre").item(0).getTextContent());
//                    System.out.println("Direccion: " + element.getElementsByTagName("url").item(0).getTextContent()+"\n\n");
            }
        }
      return lista;
                
    }

}
