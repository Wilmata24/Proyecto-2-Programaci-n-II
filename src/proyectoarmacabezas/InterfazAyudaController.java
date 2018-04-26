/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoarmacabezas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class InterfazAyudaController implements Initializable {

    @FXML
    private ImageView imageViewSigno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imagenSigno = new Image("/iconos/signo.png");
        imageViewSigno.setImage(imagenSigno);
    }    
    
}
