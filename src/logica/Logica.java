package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Wilmer Mata 
 * @author Nicole Fonseca
 */

public class Logica {

    private GridPane gridPaneMapa;
    ImageView imageViewMuestraMapa[][];
    /**
     * Método para mostrar el mapa mediante una matriz, el tamaño es definido por el usuario con
     * los parámetros filas y columnas, el primer for recorre la matriz de Image
     * y el segundo for la matriz de ImageView, mientras que las variables columna y fila van aumentando para
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

        int columna = 0;
        int fila = 0;
        for (int i = 0; i < muestraMapa.length; i++) {
            columna = 0;
            for (int j = 0; j < muestraMapa[0].length; j++) {
                muestraMapa[i][j] = new Image("/iconos/cuadrado.jpg");
                imageViewMuestraMapa[i][j] = new ImageView();
                imageViewMuestraMapa[i][j].setImage(muestraMapa[i][j]);

                GridPane.setConstraints(imageViewMuestraMapa[i][j], columna, fila);
                columna++;
                gridPaneMapa.getChildren().add(imageViewMuestraMapa[i][j]);
            }
            fila++;
        }
        return gridPaneMapa;
    }
    
    public static ArrayList<Object> listaPosicionIcono = new ArrayList<>(); 
    /**
     * 
     * @param url recibe la dirección de la imagen seleccionada previamente
     * @param fila cantidad de filas del mapa
     * @param columna cantidad de columnas del mapa
     * @return 
     */
    
    public ArrayList detectaClickMapa(String url, int fila, int columna) {
  
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
                        String posicion = "[" + auxI + "]" + "," + "[" + auxJ + "]";
                        listaPosicionIcono.add(posicion);
                    }
                });            
            }
        }               
        return listaPosicionIcono;
    }

    /**
     * Abre un FileChooser para exportar el contenido de un GridPane a formato .png
     * @param gridPane 
     */
   public void fileChooserExportar(GridPane gridPane) {
       
        WritableImage image = gridPane.snapshot(new SnapshotParameters(), null);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        // Show save file dialog
        File file = fileChooser.showSaveDialog(this.dialogStage);
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".png")) {
                file = new File(file.getPath() + ".png");
            }
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                
            }
        }
    }
   
    private Stage dialogStage;

    public void start(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
   
    /**
     * Determina la cantidad de filas de un GridPane
     * @param gridPane, recibe el GridPane del cual se va a determinar la
     * cantidad de filas
     * @return cantidad filas
     */
    public int getCantidadFilas(GridPane gridPane) {
        
        int numFilas = gridPane.getRowConstraints().size();
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            Node node = gridPane.getChildren().get(i);
            if (node.isManaged()) {
                Integer indiceFila = GridPane.getRowIndex(node);
                if (indiceFila != null) {
                    numFilas = Math.max(numFilas, indiceFila + 1);
                }
            }
        }
        return numFilas;
    }
    
    /**
     * Determina la cantidad de columnas de un GridPane
     * @param gridPane, recibe el GridPane del cual se va a determinar la
     * cantidad de columnas
     * @return cantidad de columnas
     */
    public int getCantidadColumnas(GridPane gridPane) {
        
        int numColumnas = gridPane.getColumnConstraints().size();
        for (int i = 0; i < gridPane.getChildren().size(); i++) {
            Node node = gridPane.getChildren().get(i);
            if (node.isManaged()) {
                Integer indiceColumna = GridPane.getColumnIndex(node);
                if (indiceColumna != null) {
                    numColumnas = Math.max(numColumnas, indiceColumna + 1);
                }
            }
        }
        return numColumnas;
    }
    
    /**
     * Determina si los valores que entran como parámetro son números
     * @param cadena1
     * @param cadena2
     * @return 
     */
    public static boolean isNumeric(String cadena1, String cadena2) {

        boolean resultado;

        try {
            Integer.parseInt(cadena1);
            Integer.parseInt(cadena2);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
}
