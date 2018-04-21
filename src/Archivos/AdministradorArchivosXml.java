
package Archivos;

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
import proyectoarmacabezas.Icono;

/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class AdministradorArchivosXml {
    
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
