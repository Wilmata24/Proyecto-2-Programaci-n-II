package proyectoarmacabezas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import Archivos.AdministradorArchivosXml;
import javafx.scene.Cursor;

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
    @FXML private MenuBar menuBar;
   

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
        gridPaneMapa.setAlignment(Pos.CENTER);
        anchorPaneMapa.getChildren().add(gridPaneMapa);
    }
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Relleno y color de la barra de menú
        menuBar.setPadding(new Insets(10, 200, 10, 300));
        menuBar.setStyle("-fx-background-color: #CC9999");

        //Llamada al método para mostrar el mapa
        mostrarMapa(5, 5);
        //Iconos definidos para que el usuario llene el mapa
        Image imageTwitter = new Image("/iconos/twitter.png");
        imageViewTwitter.setImage(imageTwitter);
        imageViewTwitter.setCursor(Cursor.HAND);

        Image imageFacebook = new Image("/iconos/facebook.png");
        imageViewFacebook.setImage(imageFacebook);
        imageViewFacebook.setCursor(Cursor.HAND);

        Image imageInstagram = new Image("/iconos/instagram.png");
        imageViewInstagram.setImage(imageInstagram);
        imageViewInstagram.setCursor(Cursor.HAND);

        Image imageSkype = new Image("/iconos/skype.png");
        imageViewSkype.setImage(imageSkype);
        imageViewSkype.setCursor(Cursor.HAND);

        Image imageSnapchat = new Image("/iconos/snapchat.png");
        imageViewSnapchat.setImage(imageSnapchat);
        imageViewSnapchat.setCursor(Cursor.HAND);

        Image imageSoundcloud = new Image("/iconos/soundcloud.png");
        imageViewSoundCloud.setImage(imageSoundcloud);
        imageViewSoundCloud.setCursor(Cursor.HAND);

        Image imageTumblr = new Image("/iconos/tumblr.png");
        imageViewTumblr.setImage(imageTumblr);
        imageViewTumblr.setCursor(Cursor.HAND);

        Image imageWhatsApp = new Image("/iconos/whatsapp.png");
        imageViewWhatsApp.setImage(imageWhatsApp);
        imageViewWhatsApp.setCursor(Cursor.HAND);

        Image imageTelegram = new Image("/iconos/telegram.png");
        imageViewTelegram.setImage(imageTelegram);
        imageViewTelegram.setCursor(Cursor.HAND);

        Image imageYouTube = new Image("/iconos/youtube.png");
        imageViewYouTube.setImage(imageYouTube);
        imageViewYouTube.setCursor(Cursor.HAND);

        //Guardar descripción de los íconos en archivo Xml
        AdministradorArchivosXml archivoXml = new AdministradorArchivosXml();
        Icono iconoTwitter = new Icono("Twitter", 64);
        Icono iconoFacebook = new Icono("Facebook", 64);
        Icono iconoInstagram = new Icono("Instagram", 64);
        Icono iconoSkype = new Icono("Skype", 64);
        Icono iconoSnapchat = new Icono("Snapchat", 64);
        Icono iconoSoundCloud = new Icono("SoundCloud", 64);
        Icono iconoTumblr = new Icono("Tumblr", 64);
        Icono iconoWhatsApp = new Icono("WhatsApp", 64);
        Icono iconoTelegram = new Icono("Telegram", 64);
        Icono iconoYouTube = new Icono("YouTube", 64);

        archivoXml.guardarIconos("ListadoIconos", iconoTwitter);
        archivoXml.guardarIconos("ListadoIconos", iconoFacebook);
        archivoXml.guardarIconos("ListadoIconos", iconoInstagram);
        archivoXml.guardarIconos("ListadoIconos", iconoSkype);
        archivoXml.guardarIconos("ListadoIconos", iconoSnapchat);
        archivoXml.guardarIconos("ListadoIconos", iconoSoundCloud);
        archivoXml.guardarIconos("ListadoIconos", iconoTumblr);
        archivoXml.guardarIconos("ListadoIconos", iconoWhatsApp);
        archivoXml.guardarIconos("ListadoIconos", iconoTelegram);
        archivoXml.guardarIconos("ListadoIconos", iconoYouTube);

    }
    
    

}
