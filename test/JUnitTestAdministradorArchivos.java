
import archivos.AdministradorArchivos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import static org.junit.Assert.assertEquals;
/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class JUnitTestAdministradorArchivos {
    
    private static AdministradorArchivos testAdministradorArchivos;
    
    public JUnitTestAdministradorArchivos() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testAdministradorArchivos = new AdministradorArchivos();
    }
    /**
     * Prueba con un archivo que no existe
     * @throws IOException
     * @throws ParseException 
     */
    @Test
    public void testLeerArchivoJson() throws IOException, ParseException  {
        try {
            File file = new File("prueba");
            JSONParser jsonParser = new JSONParser();
            Object object = jsonParser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) object;
            JSONObject retorno = testAdministradorArchivos.leerArchivoJson(file);
            assertEquals(jsonObject, retorno);
        } catch (FileNotFoundException exception) {
            System.out.println("No se encontró el archivo");
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
