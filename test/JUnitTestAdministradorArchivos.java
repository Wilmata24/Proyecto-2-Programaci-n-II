
import archivos.AdministradorArchivos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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
