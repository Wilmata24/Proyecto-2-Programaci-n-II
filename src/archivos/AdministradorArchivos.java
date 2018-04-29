
package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import dominio.Icono;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
     * @param rutaArchivo 
     */
    public void escribirArchivoJson(ArrayList url, ArrayList posicionIcono, int columnas, int filas, String rutaArchivo) {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("posicionIcono", posicionIcono);
        jsonObject.put("columnas", columnas);
        jsonObject.put("filas", filas);

        try {
            FileWriter archivo = new FileWriter(rutaArchivo);
            archivo.write(jsonObject.toJSONString());
            archivo.flush();
            archivo.close();

        } catch (IOException exception) {
            System.out.println("IOException");
        }
    }
    
    /**
     *  
     * @param rutaArchivo
     * @return
     */
    public JSONObject leerArchivoJson(String rutaArchivo) {
        
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            Object object = jsonParser.parse(new FileReader(rutaArchivo));

            jsonObject = (JSONObject) object;

            ArrayList url = (ArrayList) jsonObject.get("url");
            ArrayList posicionIcono = (ArrayList) jsonObject.get("posicionIcono");
            long filas = (long) jsonObject.get("filas");
            long columnas = (long) jsonObject.get("columnas");

        } catch (Exception ex) {
            System.err.println("Error: " + ex.toString());
        } 
        return jsonObject;
    }
    
    public ArrayList getIconosArchivo(JSONObject jsonObject) {

        JSONArray url = (JSONArray) jsonObject.get("url");
        Iterator<String> iterator = url.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return url;
    }
    
    public long getFilasArchivo(JSONObject jsonObject) {
        long filas = (long) jsonObject.get("filas");
        return filas;
    }
    
    public long getColumnasArchivo(JSONObject jsonObject) {
        long columnas = (long) jsonObject.get("columnas");
        return columnas;
    }
    
    public String getPosicionIconosArchivo(JSONObject jsonObject) {
        String posicionIconos = (String) jsonObject.get("posicionIconos");
        return posicionIconos;
    }
    
    /**
     * Lee archivo formato xml
     * @return
     * @throws Exception 
     */
    public ArrayList<Icono> leerArchivoXml() throws Exception {

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
            }
        }
        return lista;

    }

}
