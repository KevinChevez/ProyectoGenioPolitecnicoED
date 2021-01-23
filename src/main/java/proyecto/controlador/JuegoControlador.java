/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import static proyecto.constantes.Constantes.*;
import static proyecto.controlador.Aplicacion.arbolPreguntas;
import proyecto.modelo.Nodo;

/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class JuegoControlador implements Initializable {

    @FXML
    private Button btnInicio;
    @FXML
    private Button btnSalir;
    @FXML
    private TextField respuestaTxtF;
    @FXML
    private Label pregunta;
    @FXML
    private ImageView imagenJuego;
    @FXML
    private VBox contenedorImagen;
    @FXML
    private Label mesajeRespWrong;
    
    public static Nodo<String> nodoActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pregunta.setText(arbolPreguntas.getDataNodoActual());
        //arbolPreguntas.anchura();
    }    

    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        arbolPreguntas.reiniciarNodoActual();
        Aplicacion.setRoot(PATH_VIEW_INICIO);
    }

    @FXML
    private void salir(ActionEvent event) {        
        System.exit(0);
    }

    @FXML
    private void enterAction(ActionEvent event) throws IOException {
        String textRecolected = respuestaTxtF.getText();
        respuestaTxtF.clear();
        if(textRecolected.toLowerCase().equals("si")){
            mesajeRespWrong.setVisible(false);
            if(arbolPreguntas.isHojaNodoActual()){
                Aplicacion.setRoot(PATH_VIEW_WIN);
            }
            else{
                String newPregunta = arbolPreguntas.iterarIzquierda();
                pregunta.setText(newPregunta);
            }
        }
        else if (textRecolected.toLowerCase().equals("no")){ 
            mesajeRespWrong.setVisible(false);
            if(arbolPreguntas.isHojaNodoActual()){
                nodoActual = arbolPreguntas.getNodoActual();
                Aplicacion.setRoot(PATH_VIEW_LOSE);
            }
            else{
                String newPregunta = arbolPreguntas.iterarDerecha();
                pregunta.setText(newPregunta);
            }
        }
        else{
            mesajeRespWrong.setVisible(true);
        }
    }


    @FXML
    private void clickPane(MouseEvent event) {
        //System.out.println("Hace clikc pane");        
        //respuestaTxtF.setFocusTraversable(true);
        //respuestaTxtF.setDisable(true);
    }
    
}
