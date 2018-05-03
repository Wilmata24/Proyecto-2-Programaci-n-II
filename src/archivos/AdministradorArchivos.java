
package archivos;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import dominio.Icono;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *@author Wilmer Mata
 * @author Nicole Fonseca
 */

public class AdministradorArchivos {
   
    /**
     * 
     * @param url
     * @param posicionIcono
     * @param columnas
     * @param filas
     * @return 
     */
    
    public Boolean escribirArchivoJson(ArrayList url, ArrayList posicionIcono, int columnas, int filas) {
       
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("posicionIcono", posicionIcono);
        jsonObject.put("columnas", columnas);
        jsonObject.put("filas", filas);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar documento");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", "*.json"));
        File file = fileChooser.showSaveDialog(this.dialogStage);

        try {
            FileWriter archivo = new FileWriter(file);
            archivo.write(jsonObject.toJSONString());
            archivo.flush();
            archivo.close();
            return true;
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            return false;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }
    
    public FileChooser fileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Abrir documento");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
    fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser;
        
    } 

    private Stage dialogStage;

    public void start(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    int i = 0;
    int j = 0;

    public JSONObject leerArchivoJson(File file) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        try {
            Object object = jsonParser.parse(new FileReader(file));
            jsonObject = (JSONObject) object;
            ArrayList url = (ArrayList) jsonObject.get("url");
            ArrayList posicionIcono = (ArrayList) jsonObject.get("posicionIcono");
            long filas = (long) jsonObject.get("filas");
            long columnas = (long) jsonObject.get("columnas");
            
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException | ParseException exception) {;
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return jsonObject;
    }
    
    public void muestraGridPane (GridPane gridPaneMapa, File file ){
        
        long filas = (long) leerArchivoJson(file).get("filas");
        long columnas = (long) leerArchivoJson(file).get("columnas");

        JSONArray url = (JSONArray) leerArchivoJson(file).get("url");
        JSONArray posicionIcono = (JSONArray) leerArchivoJson(file).get("posicionIcono");

        ImageView imageView[][] = new ImageView[(int) filas][(int) columnas];
        Image image[][] = new Image[(int) filas][(int) columnas];

        do {
            String iconosArchivo = url.get(i).toString();
            String fila = posicionIcono.get(j).toString().substring(1, 2);
            String columna = posicionIcono.get(j).toString().substring(5, 6);
            image[Integer.parseInt(fila)][Integer.parseInt(columna)] = new Image(iconosArchivo);
            imageView[Integer.parseInt(fila)][Integer.parseInt(columna)] = new ImageView();
            imageView[Integer.parseInt(fila)][Integer.parseInt(columna)].setImage(image[Integer.parseInt(fila)][Integer.parseInt(columna)]);

            GridPane.setConstraints(imageView[Integer.parseInt(fila)][Integer.parseInt(columna)], Integer.parseInt(columna), Integer.parseInt(fila));
            gridPaneMapa.getChildren().add(imageView[Integer.parseInt(fila)][Integer.parseInt(columna)]);
            i++;
            j++;

        } while (i < posicionIcono.size());
    }
    
    /**
     * Lee archivo formato xml
     * @param nombreArchivo
     * @return
     * @throws Exception 
     */
    public ArrayList<Icono> leerArchivoXml(String nombreArchivo) throws Exception {
        
        ArrayList<Icono> lista = null;
        try {
            File archivo = new File(nombreArchivo);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            lista = new ArrayList();
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
                }
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lista;
    }

}
