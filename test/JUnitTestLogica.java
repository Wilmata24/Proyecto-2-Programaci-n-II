
import logica.Logica;
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
public class JUnitTestLogica {
    
    private static Logica testLogica;

    public JUnitTestLogica() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        testLogica = new Logica();
    }
    /**
     * Valida si la cadena que entra es númerica
     * La prueba se realiza con un valor incorrecto, espacio en blanco
     */
    @Test
    public void testIsNumericUno() {
        Boolean valorPrueba = false;
        Boolean retorno = testLogica.isNumeric(" ", " ");
        assertEquals(valorPrueba, retorno);
    }
    
    /**
     * Valida si la cadena que entra es númerica
     * La prueba se realiza con un valor incorrecto
     */
    @Test
    public void testIsNumericDos() {
        Boolean valorPrueba = false;
        Boolean retorno = testLogica.isNumeric("@", "$");
        assertEquals(valorPrueba, retorno);
    }
    
    /**
     * Valida si la cadena que entra es númerica
     * La prueba se realiza con letras
     */
    @Test
    public void testIsNumericTres() {
        Boolean valorPrueba = false;
        Boolean retorno = testLogica.isNumeric("a", "b");
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
