
package archivos;

import dominio.Icono;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class AdministradorArchivosXml {
    
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
