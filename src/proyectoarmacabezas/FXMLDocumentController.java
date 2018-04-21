
package proyectoarmacabezas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private ImageView imageViewTwitter;
    @FXML private ImageView imageViewFacebook;
    @FXML private ImageView imageViewInstagram;
    @FXML private ImageView imageViewSkype;
    @FXML private ImageView imageViewSnapchat;
    @FXML private ImageView imageViewSoundCloud;
    @FXML private ImageView imageViewTumblr;
    @FXML private ImageView imageViewWhatsApp;
    @FXML private ImageView imageViewTelegram;
    @FXML private ImageView imageViewYouTube;
    @FXML private GridPane gridPaneMapa;
    @FXML private AnchorPane anchorPaneMapa;
   
    /*
    * Método para mostrar el mapa mediante una matriz, el tamaño es definido por el usuario con
    * los parámetros primerParametro y segundoParametro, el primer for recorre la matriz de Image
    * y el segundo for la matriz de ImageView, mientras que las variables m y n van aumentando para
    * llenar el GridPane con las imágenes
    */
    
    public void mostrarMapa(int primerParametro, int segundoParametro) {
        
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
        //Agregar mapa al AnchorPane
        anchorPaneMapa.getChildren().add(gridPaneMapa);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Llamada al método para mostrar el mapa
        mostrarMapa(5, 5);
        //Iconos definidos para que el usuario llene el mapa
        Image imageTwitter = new Image("/iconos/twitter.png");
        imageViewTwitter.setImage(imageTwitter);
        
        Image imageFacebook = new Image("/iconos/facebook.png");
        imageViewFacebook.setImage(imageFacebook);
        
        Image imageInstagram = new Image("/iconos/instagram.png");
        imageViewInstagram.setImage(imageInstagram);
        
        Image imageSkype = new Image("/iconos/skype.png");
        imageViewSkype.setImage(imageSkype);
        
        Image imageSnapchat = new Image("/iconos/snapchat.png");
        imageViewSnapchat.setImage(imageSnapchat);
        
        Image imageSoundcloud = new Image("/iconos/soundcloud.png");
        imageViewSoundCloud.setImage(imageSoundcloud);
        
        Image imageTumblr = new Image("/iconos/tumblr.png");
        imageViewTumblr.setImage(imageTumblr);
        
        Image imageWhatsApp = new Image("/iconos/whatsapp.png");
        imageViewWhatsApp.setImage(imageWhatsApp);
        
        Image imageTelegram = new Image("/iconos/telegram.png");
        imageViewTelegram.setImage(imageTelegram);
        
        Image imageYouTube = new Image("/iconos/youtube.png");
        imageViewYouTube.setImage(imageYouTube);
    
        //Prueba para escribir en archivo Xml
        Archivos archivo = new Archivos();
        Icono iconoTwitter = new Icono("Twitter", 10, 10, 64);
        Icono iconoFacebook = new Icono("Facebook", 10, 10 ,64);
        Icono iconoInstagram = new Icono("Instagram", 10, 10 ,64);
        Icono iconoSkype = new Icono("Skype", 10, 10 ,64);
        Icono iconoSnapchat = new Icono("Snapchat", 10, 10 ,64);
        Icono iconoSoundCloud = new Icono("SoundCloud", 10, 10 ,64);
        Icono iconoTumblr = new Icono("Tumblr", 10, 10 ,64);
        Icono iconoWhatsApp = new Icono("WhatsApp", 10, 10 ,64);
        Icono iconoTelegram = new Icono("Telegram", 10, 10 ,64);
        Icono iconoYouTube = new Icono("YouTube", 10, 10 ,64);
        
        archivo.guardarIconos("ListadoIconos", iconoTwitter);
        archivo.guardarIconos("ListadoIconos", iconoFacebook);
        archivo.guardarIconos("ListadoIconos", iconoInstagram);
        archivo.guardarIconos("ListadoIconos", iconoSkype);
        archivo.guardarIconos("ListadoIconos", iconoSnapchat);
        archivo.guardarIconos("ListadoIconos", iconoSoundCloud);
        archivo.guardarIconos("ListadoIconos", iconoTumblr);
        archivo.guardarIconos("ListadoIconos", iconoWhatsApp);
        archivo.guardarIconos("ListadoIconos", iconoTelegram);
        archivo.guardarIconos("ListadoIconos", iconoYouTube);


    }    
    
    
}
