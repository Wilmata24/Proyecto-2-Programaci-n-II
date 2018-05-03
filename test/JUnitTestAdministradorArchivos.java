
import archivos.AdministradorArchivos;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
    
    @Test
    public void testLeerXml() throws Exception {
        String valorPrueba = "iconos";
        ArrayList retorno = testAdministradorArchivos.leerArchivoXml(valorPrueba);
        assertEquals(valorPrueba, retorno);
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

}
