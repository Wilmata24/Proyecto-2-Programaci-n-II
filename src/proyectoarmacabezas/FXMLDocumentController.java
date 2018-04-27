package proyectoarmacabezas;

import archivos.AdministradorArchivos;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import lógica.Lógica;

/**
 *
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class FXMLDocumentController implements Initializable {
    
    Lógica logica = new Lógica();
    @FXML private AnchorPane anchorPaneMapa;
    @FXML private TextField textFieldLargo;
    @FXML private TextField textFieldAncho;
    @FXML private MenuItem menuItemGuardar;
    @FXML private MenuItem menuItemAbrir;
    @FXML private MenuItem menuItemExportarJpg;
    @FXML private MenuItem menuItemExportarPng;
    @FXML private MenuBar menuBar;
    @FXML private VBox vBoxIconos;
    @FXML private Button buttonAceptar;
    @FXML private Button buttonBorrar;
    @FXML private Label labelMensaje;
    private GridPane gridPaneMapa;
    
    @FXML
    public void buttonAceptarAccion(ActionEvent event) throws Exception {
        
        try {
        if (Integer.parseInt(textFieldLargo.getText()) <= 0 || Integer.parseInt(textFieldAncho.getText()) <= 0) {
            labelMensaje.setText("Valor incorrecto");
        } else if(isNumeric(textFieldAncho.getText(), textFieldLargo.getText())) {
            gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
            //Agregar mapa al AnchorPane
            anchorPaneMapa.getChildren().add(gridPaneMapa);
            buttonBorrar.setDisable(false);
            buttonAceptar.setDisable(true);
        }
        }catch (NumberFormatException exception) {
            labelMensaje.setText("Valor incorrecto");
            
        } 
//        System.out.println(logica.getRowCount(gridPaneMapa));
//        System.out.println(logica.getColumnsCount(gridPaneMapa));

    }
    
    @FXML
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
    
    
 
    @FXML
    public void buttonBorrarAccion(ActionEvent event) throws Exception {
        gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
        anchorPaneMapa.getChildren().add(gridPaneMapa);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        buttonBorrar.setDisable(true);
        //Relleno y color de la barra de menú
        menuBar.setPadding(new Insets(10, 200, 10, 300));
        menuBar.setStyle("-fx-background-color: #CC9999");
        try {
            vBoxIconos();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
    private void exportarPng(ActionEvent event) {
    WritableImage image = gridPaneMapa.snapshot(new SnapshotParameters(), null);
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
    // TODO: handle exception here
    }
    }
//File file = new File("chart.png");
}



    private Stage dialogStage;
    public void start(Stage dialogStage) {
    this.dialogStage = dialogStage;
    }
    

    @FXML
    private void exportarJpg(ActionEvent event) {
        
        WritableImage image = gridPaneMapa.snapshot(new SnapshotParameters(), null);
        
        // TODO: probably use a file chooser here
        File file = new File("captura1.jpg");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "jpg", file);
        } catch (IOException e) {
           
        }
    }
    
    @FXML
    private void menuItemAyuda(ActionEvent event) throws IOException  {
        
        Parent parent = FXMLLoader.load(getClass().getResource("InterfazAyuda.fxml"));
        Scene scene = new Scene(parent);
        Stage window = new Stage();
        window.getIcons().add(new Image("/iconos/rompecabezas.png")); 
        window.setScene(scene);
        window.show();
        window.setTitle("Armacabezas");
    }
    
    /**
     * Recibe los íconos desde un archivo .xml y los coloca en un vBox,
     * al darle click sobre estos íconos les da una acción.
     */
    Image imagenAux = null;
    ImageView imageView = null;
    public void vBoxIconos() throws Exception {
      
        
        AdministradorArchivos archivo = new AdministradorArchivos();
        ArrayList lista = archivo.leerArchivo();
        for (int i = 0; i < lista.size(); i++) {
            Image imagen = new Image(archivo.leerArchivo().get(i).getUrl());
            imageView = new ImageView();
            imageView.setImage(imagen);
            imageView.setCursor(Cursor.HAND);
            
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(imageView.getImage().toString());
                    String urlImagen = "";
                    int largo = 0;
                    int ancho = 0;
                    for (int j = 0; j < lista.size(); j++) {
                        try {
                            urlImagen = archivo.leerArchivo().get(j).getUrl();
                            largo = (int) Integer.parseInt(textFieldLargo.getText());
                            ancho = (int) Integer.parseInt(textFieldAncho.getText());
                            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                logica.detectaClickMapa(urlImagen, largo, ancho);
                            } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && imageView.getImage().equals("iconos/instagram.png")) {
                                logica.detectaClickMapa(imageView.getImage().toString(), largo, ancho);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
//                                if (urlImagen.equals("iconos/facebook.png")) {
//                                    logica.detectaClickMapa(urlImagen, largo, ancho);
//                                } 
                       
                    }
                }             
            });
            vBoxIconos.getChildren().add(imageView);  
        }
    }
    

}
