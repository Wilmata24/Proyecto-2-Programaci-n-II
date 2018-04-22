package logica;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Wilmer Mata 
 * @author Nicole Fonseca
 */

public class Logica {

    private GridPane gridPaneMapa;

    /*
    * Método para mostrar el mapa mediante una matriz, el tamaño es definido por el usuario con
    * los parámetros primerParametro y segundoParametro, el primer for recorre la matriz de Image
    * y el segundo for la matriz de ImageView, mientras que las variables m y n van aumentando para
    * llenar el GridPane con las imágenes
     */
    
    public GridPane mostrarMapa(int primerParametro, int segundoParametro) {

        gridPaneMapa = new GridPane();
        //Matriz de imágenes con tamaño definido por el usuario
        Image muestraMapa[][] = new Image[primerParametro][segundoParametro];
        ImageView imageViewMuestraMapa[][] = new ImageView[primerParametro][segundoParametro];

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
    
    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
    double orgTranslateY;
    
    public void moverIconos(ImageView imageView) {
        
        EventHandler<MouseEvent> imageViewOnMousePressedEventHandler
                = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                orgSceneX = event.getSceneX();
                orgSceneY = event.getSceneY();
                orgTranslateX = ((ImageView) (event.getSource())).getTranslateX();
                orgTranslateY = ((ImageView) (event.getSource())).getTranslateY();
            }
        };

        EventHandler<MouseEvent> imageViewOnMouseDraggedEventHandler
                = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double offsetX = event.getSceneX() - orgSceneX;
                double offsetY = event.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((ImageView) (event.getSource())).setTranslateX(newTranslateX);
                ((ImageView) (event.getSource())).setTranslateY(newTranslateY);
            }
        };

        imageView.setOnMousePressed(imageViewOnMousePressedEventHandler);
        imageView.setOnMouseDragged(imageViewOnMouseDraggedEventHandler);
    }
}
