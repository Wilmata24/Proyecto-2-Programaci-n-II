/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarmacabezas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Wilmata
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
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imageTwitter = new Image("/iconos/twitter.png");
        imageViewTwitter.setImage(imageTwitter);
        Imagen imagenTwitter = new Imagen(imageTwitter, "Twitter", 0);
        
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
    }    
    
    
}
