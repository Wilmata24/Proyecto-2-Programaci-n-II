
package proyectoarmacabezas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class ProyectoArmacabezas extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));       
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/iconos/rompecabezas.png"));
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Armacabezas");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
    }
    
}
