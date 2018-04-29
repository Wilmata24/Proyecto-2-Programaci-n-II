package proyectoarmacabezas;

import archivos.AdministradorArchivos;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lógica.Lógica;

/**
 *
 * @author Wilmer Mata
 * @author Nicole Fonseca
 */
public class FXMLDocumentController implements Initializable {
    
    Lógica logica = new Lógica();
    AdministradorArchivos administradorArchivos = new AdministradorArchivos();
    @FXML private AnchorPane anchorPaneMapa;
    @FXML private TextField textFieldLargo;
    @FXML private TextField textFieldAncho;
    @FXML private MenuItem menuItemGuardar;
    @FXML private MenuItem menuItemAbrir;
    @FXML private MenuItem menuItemExportar;
    @FXML private MenuItem menuItemSalir;
    @FXML private MenuItem menuItemManual;
    @FXML private MenuBar menuBar;
    @FXML private VBox vBoxIconos;
    @FXML private Button buttonAceptar;
    @FXML private Button buttonBorrar;
    @FXML private Label labelMensaje;
    private GridPane gridPaneMapa;
    ArrayList<Object> listaUrl = new ArrayList<>();
    ArrayList<Object> listaPosicionIcono = new ArrayList<>();

    /**
     * 
     * @param event
     * @throws Exception 
     */
    @FXML
    public void buttonAceptarAccion(ActionEvent event) throws Exception {

        try {
        if (Integer.parseInt(textFieldLargo.getText()) <= 0 || Integer.parseInt(textFieldAncho.getText()) <= 0) {
            labelMensaje.setText("Valor incorrecto");
        } else if(logica.isNumeric(textFieldAncho.getText(), textFieldLargo.getText())) {
            gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
            //Agregar mapa al AnchorPane
            anchorPaneMapa.getChildren().add(gridPaneMapa);
            buttonBorrar.setDisable(false);
            buttonAceptar.setDisable(true);
            vBoxIconos.setDisable(false);
        }
        }catch (NumberFormatException exception) {
            labelMensaje.setText("Valor incorrecto");
            
        } 
    }

    /**
     * Borra los íconos que se encuentran en el GridPane para llenar el mapa nuevamente
     * @param event
     * @throws Exception 
     */
    @FXML
    public void buttonBorrarAccion(ActionEvent event) throws Exception {
        gridPaneMapa = logica.mostrarMapa(Integer.parseInt(textFieldLargo.getText()), Integer.parseInt(textFieldAncho.getText()));
        anchorPaneMapa.getChildren().add(gridPaneMapa);
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Iconos de las opciones de la barra de menú
        menuItemGuardar.setGraphic(new ImageView(new Image("/iconos/guardar.png")));
        menuItemAbrir.setGraphic(new ImageView(new Image("/iconos/abrir.png")));
        menuItemExportar.setGraphic(new ImageView(new Image("/iconos/exportar.png")));
        menuItemSalir.setGraphic(new ImageView(new Image("/iconos/salida.png")));
        menuItemManual.setGraphic(new ImageView(new Image("/iconos/manual.png")));

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
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void guardar(ActionEvent event) {
        
        administradorArchivos.escribirArchivoJson(listaUrl, listaPosicionIcono, logica.getCantidadFilas(gridPaneMapa), logica.getCantidadColumnas(gridPaneMapa), "File");
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void abrirDocumento(ActionEvent event) {
        long filas = administradorArchivos.getFilasArchivo(administradorArchivos.leerArchivoJson("File"));
        long columnas = administradorArchivos.getColumnasArchivo(administradorArchivos.leerArchivoJson("File"));
        gridPaneMapa = logica.mostrarMapa((int) filas, (int) columnas);
        anchorPaneMapa.getChildren().add(gridPaneMapa);
        vBoxIconos.setDisable(false);
    }

    /**
     * 
     * @param event 
     */
    @FXML
    private void exportar(ActionEvent event) {
        logica.fileChooserExportar(gridPaneMapa);
    }
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
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
     * al darle click sobre estos íconos les da una acción
     * @throws Exception 
     */
    public void vBoxIconos() throws Exception {
        vBoxIconos.setDisable(true);
        AdministradorArchivos archivo = new AdministradorArchivos();

        Image imagenFacebook = new Image(archivo.leerArchivoXml().get(0).getUrl());
        ImageView imageViewFaceboook = new ImageView();
        imageViewFaceboook.setImage(imagenFacebook);
        imageViewFaceboook.setCursor(Cursor.HAND);
        
        Image imagenInstagram = new Image(archivo.leerArchivoXml().get(1).getUrl());
        ImageView imageViewInstagram = new ImageView();
        imageViewInstagram.setImage(imagenInstagram);
        imageViewInstagram.setCursor(Cursor.HAND);
        
        Image imagenSkype = new Image(archivo.leerArchivoXml().get(2).getUrl());
        ImageView imageViewSkype = new ImageView();
        imageViewSkype.setImage(imagenSkype);
        imageViewSkype.setCursor(Cursor.HAND);
        
        Image imagenSnapchat = new Image(archivo.leerArchivoXml().get(3).getUrl());
        ImageView imageViewSnapchat = new ImageView();
        imageViewSnapchat.setImage(imagenSnapchat);
        imageViewSnapchat.setCursor(Cursor.HAND);
        
        Image imagenSoundcloud = new Image(archivo.leerArchivoXml().get(4).getUrl());
        ImageView imageViewSoundcloud = new ImageView();
        imageViewSoundcloud.setImage(imagenSoundcloud);
        imageViewSoundcloud.setCursor(Cursor.HAND);
        
        Image imagenTelegram = new Image(archivo.leerArchivoXml().get(5).getUrl());
        ImageView imageViewTelegram = new ImageView();
        imageViewTelegram.setImage(imagenTelegram);
        imageViewTelegram.setCursor(Cursor.HAND);
        
        Image imagenTumblr = new Image(archivo.leerArchivoXml().get(6).getUrl());
        ImageView imageViewTumblr = new ImageView();
        imageViewTumblr.setImage(imagenTumblr);
        imageViewTumblr.setCursor(Cursor.HAND);
        
        Image imagenTwitter = new Image(archivo.leerArchivoXml().get(7).getUrl());
        ImageView imageViewTwitter = new ImageView();
        imageViewTwitter.setImage(imagenTwitter);
        imageViewTwitter.setCursor(Cursor.HAND);
        
        Image imagenWhatsapp = new Image(archivo.leerArchivoXml().get(8).getUrl());
        ImageView imageViewWhatsapp = new ImageView();
        imageViewWhatsapp.setImage(imagenWhatsapp);
        imageViewWhatsapp.setCursor(Cursor.HAND);
        
        Image imagenYoutube = new Image(archivo.leerArchivoXml().get(9).getUrl());
        ImageView imageViewYoutube = new ImageView();
        imageViewYoutube.setImage(imagenYoutube);
        imageViewYoutube.setCursor(Cursor.HAND);
      
            imageViewFaceboook.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/facebook.png", largo, ancho));
                         listaUrl.add("iconos/facebook.png");
                    }
                }             
            });
            imageViewInstagram.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        listaPosicionIcono.add(logica.detectaClickMapa("iconos/instagram.png", largo, ancho));
                        listaUrl.add("iconos/instagram.png");
                    }
                }             
            });
            
            imageViewSkype.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        listaPosicionIcono.add(logica.detectaClickMapa("iconos/skype.png", largo, ancho));
                        listaUrl.add("iconos/skype.png");
                    }
                }             
            });
            
            imageViewSnapchat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/snapchat.png", largo, ancho));
                        listaUrl.add("iconos/snapchat.png");
                    }
                }             
            });
            
            imageViewSoundcloud.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        listaPosicionIcono.add(logica.detectaClickMapa("iconos/soundcloud.png", largo, ancho));
                        listaUrl.add("iconos/soundcloud.png");
                    }
                }             
            });
            
            imageViewTelegram.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/telegram.png", largo, ancho));
                        listaUrl.add("iconos/telegram.png");
                    }
                }             
            });
            
            imageViewTumblr.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/tumblr.png", largo, ancho));
                        listaUrl.add("iconos/tumblr.png");
                    }
                }             
            });
            
            imageViewTwitter.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/twitter.png", largo, ancho));
                        listaUrl.add("iconos/twitter.png");
                    }
                }             
            });
            
            imageViewWhatsapp.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                         listaPosicionIcono.add(logica.detectaClickMapa("iconos/whatsapp.png", largo, ancho));
                        listaUrl.add("iconos/whatsapp.png");
                    }
                }             
            });
            
            imageViewYoutube.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int largo = (int) Integer.parseInt(textFieldLargo.getText());
                    int ancho = (int) Integer.parseInt(textFieldAncho.getText());
                    if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        listaPosicionIcono.add(logica.detectaClickMapa("iconos/youtube.png", largo, ancho));
                        listaUrl.add("iconos/youtube.png");
                    }
                }             
            });
            
            vBoxIconos.getChildren().addAll(imageViewFaceboook, imageViewInstagram, imageViewSkype, imageViewSnapchat, imageViewSoundcloud, 
                                            imageViewTelegram, imageViewTumblr, imageViewTwitter, imageViewWhatsapp, imageViewYoutube);  
    }
  
}
