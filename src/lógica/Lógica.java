package lógica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import proyectoarmacabezas.FXMLDocumentController;

/**
 *
 * @author Wilmer Mata 
 * @author Nicole Fonseca
 */

public class Lógica {

    private GridPane gridPaneMapa;
    ImageView imageViewMuestraMapa[][];
    /**
     * Método para mostrar el mapa mediante una matriz, el tamaño es definido por el usuario con
     * los parámetros primerParametro y segundoParametro, el primer for recorre la matriz de Image
     * y el segundo for la matriz de ImageView, mientras que las variables m y n van aumentando para
     * llenar el GridPane con las imágenes
     * @param filas
     * @param columnas
     * @return 
     */
    public GridPane mostrarMapa(int filas, int columnas) {

        gridPaneMapa = new GridPane();
        //Matriz de imágenes con tamaño definido por el usuario
        Image muestraMapa[][] = new Image[filas][columnas];
        imageViewMuestraMapa= new ImageView[filas][columnas];

        int m = 0;
        int n = 0;
        for (int i = 0; i < muestraMapa.length; i++) {
            m = 0;
            for (int j = 0; j < muestraMapa[0].length; j++) {
                muestraMapa[i][j] = new Image("/iconos/cuadrado.jpg");
                imageViewMuestraMapa[i][j] = new ImageView();
                imageViewMuestraMapa[i][j].setImage(muestraMapa[i][j]);

                GridPane.setConstraints(imageViewMuestraMapa[i][j], m, n);
                m++;
                gridPaneMapa.getChildren().add(imageViewMuestraMapa[i][j]);
            }
            n++;
        }
        return gridPaneMapa;
    }
    
    /**
     * 
     * @param url recibe la dirección de la imagen seleccionada previamente
     * @param fila cantidad de filas del mapa
     * @param columna cantidad de columnas del mapa
     */
    public void detectaClickMapa(String url, int fila, int columna) {
        int matrizEspejo[][] = new int[fila][columna];
        for (int i = 0 ; i < imageViewMuestraMapa.length ; i++) {
            for (int j = 0 ; j < imageViewMuestraMapa[0].length ; j++) {
                int auxI = i;
                int auxJ = j;
                
                imageViewMuestraMapa[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        
                        int llenaEspejo = 1;
                        for(int filaEspejo= 0 ; filaEspejo < matrizEspejo.length ; filaEspejo++) {
                            for(int columnaEspejo = 0 ; columnaEspejo < matrizEspejo[0].length ; columnaEspejo++){
                                
                                Image imagen[][] = new Image[fila][columna];
                                ImageView imageView[][] = new ImageView[fila][columna];
                                int columnaGridPane = 0;
                                int filaGridPane = 0;
                                for (int filaImageView = 0 ; filaImageView < imageView.length ; filaImageView++) {
                                    columnaGridPane = 0;
                                    for (int columnaImageView = 0; columnaImageView < imageView[0].length; columnaImageView++) {
                                        imagen[auxI][auxJ] = new Image(url);
                                        imageView[auxI][auxJ] = new ImageView();
                                        imageView[auxI][auxJ].setImage(imagen[filaImageView][columnaImageView]);
                                        matrizEspejo[auxI][auxJ] = llenaEspejo;
                                        GridPane.setConstraints(imageView[auxI][auxJ], columnaGridPane, filaGridPane);
                                        columnaGridPane++;
                                        gridPaneMapa.getChildren().add(imageView[auxI][auxJ]);

                                    } 
                                    filaGridPane++;
                                } 

                            }
                        }
                    }
                });
            
            }
        }  
    }  
    /**
     *
     * @param menuItemGuardar
     */
    public void fileChooserGuardar(MenuItem menuItemGuardar) {
        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".json", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = chooser.showSaveDialog(menuItemGuardar.getParentPopup().getScene().getWindow());
        if (file != null) {
            guardarDocumento("", file);
        }
        
    }
    /**
     * 
     * @param contenido
     * @param archivo 
     */
    public void guardarDocumento(String contenido, File archivo) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(archivo);
            fileWriter.write(contenido);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private File archivo;
    private static boolean DEBUG = false;
    /**
     * 
     * @param menuItemAbrir 
     */
    public void fileChooserAbrirDocumento(MenuItem menuItemAbrir) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".*xml", "*.xml", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        if (archivo != null) {
            File existDirectory = archivo.getParentFile();
            chooser.setInitialDirectory(existDirectory);
        }

        File archivo = chooser.showOpenDialog(menuItemAbrir.getParentPopup().getScene().getWindow());

        String nombreArchivo = archivo.getPath();

//        if (DEBUG) {
//            princpalLogTextArea.appendText("Opening " + nombreArchivo + System.lineSeparator());
//        }
    }
   public void fileChooserExportar(MenuItem menuItemExportar, File file) {
        FileChooser chooser = new FileChooser();

        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".jpg", "*.png"),
                new FileChooser.ExtensionFilter(".png", "*.png")
        );

        file = chooser.showSaveDialog(menuItemExportar.getParentPopup().getScene().getWindow());
        if (file != null) {
            guardarDocumento("", file);
        }
    }
}
