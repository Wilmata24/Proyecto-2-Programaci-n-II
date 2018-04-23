package proyectoarmacabezas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import logica.Logica;

/**
 *
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class FXMLDocumentController implements Initializable {
    
    Logica logica = new Logica();
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
    @FXML private AnchorPane anchorPaneMapa;
    @FXML private TextField textFieldLargo;
    @FXML private TextField textFieldAncho;
    @FXML private MenuItem menuItemGuardar;
    @FXML private MenuItem menuItemAbrir;
    @FXML private MenuItem menuItemExportar;
    @FXML private MenuBar menuBar;
    private GridPane gridPaneMapa;
   
    @FXML
    public void buttonAceptarAccion(ActionEvent event) {
        
        if (Integer.parseInt(textFieldLargo.getText()) <= 0 || Integer.parseInt(textFieldAncho.getText()) <= 0) {
            System.out.println("Valor incorrecto");
        } else if (textFieldLargo.getText().equals(null) || textFieldAncho.getText().equals(null)) {
            System.out.println("Ingrese los dos valores.");
        } else {
            gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
            //Agregar mapa al AnchorPane
            anchorPaneMapa.getChildren().add(gridPaneMapa);
            
        }
    }
    
    private Double lastX = null;
    private Double lastY = null;

    public void arrastrar()
    {
        if (this.imageViewFacebook != null)
        {
            this.imageViewFacebook.setOnDragOver(new EventHandler<DragEvent>()
            {
                @Override
                public void handle(DragEvent dragEvent)
                {
                    HandleMouseMovement(dragEvent.getSceneX(), dragEvent.getSceneY());
                }
            });

            this.imageViewFacebook.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Dragboard db = imageViewFacebook.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString("Does not matter");
                    db.setContent(content);
                    event.consume();

                    lastX = event.getSceneX();
                    lastY = event.getSceneY();
                }
            });
        }
    }

    private synchronized void HandleMouseMovement(double sceneX, double sceneY)
    {
        double deltaX = sceneX - lastX;
        double deltaY = sceneY - lastY;

        lastX = sceneX;
        lastY = sceneY;

        double currentXAnchor =AnchorPane.getLeftAnchor(this.imageViewFacebook);
        double currentYAnchor =AnchorPane.getTopAnchor(this.imageViewFacebook);

        AnchorPane.setLeftAnchor( this.imageViewFacebook,  currentXAnchor + deltaX*1.5);
        AnchorPane.setTopAnchor(this.imageViewFacebook, currentYAnchor + deltaY*1.5);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Mover íconos
        arrastrar();
        //logica.moverIconos(imageViewSkype);
        
        //Guardar descripción de los íconos en archivo Xml
        logica.guardarInformacionIconos();
        
        //Relleno y color de la barra de menú
        menuBar.setPadding(new Insets(10, 200, 10, 300));
        menuBar.setStyle("-fx-background-color: #CC9999");

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

    }
    
    @FXML
    private void guardar(ActionEvent event) {
        logica.fileChooserGuardar(menuItemGuardar);
    }
    
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void abrirDocumento(ActionEvent event) {
        logica.fileChooserAbrirDocumento(menuItemAbrir);
    }

    @FXML
    private void exportar(ActionEvent event) {
        logica.fileChooserExportar(menuItemExportar);
    }
    
    

}
