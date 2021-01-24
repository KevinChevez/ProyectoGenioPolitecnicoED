/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static proyecto.controlador.Aplicacion.PATH_VIEW_INICIO;
import static proyecto.controlador.Aplicacion.arbolPreguntas;
import proyecto.modelo.Nodo;

/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class JuegoControlador implements Initializable {

    
    @FXML
    private TextField respuestaTxtF;
    @FXML
    private Label pregunta;
    @FXML
    private Label mesajeRespWrong;
    
    private  static Nodo<String> nodoActual ;

    public  Nodo< String> getNodoActual() {
        return nodoActual;
    }

    /**
     * Initializes the controller class.
     * @param url Recibe una url implícita de FXML para construir la clase.
     * @param rb Recibe un ResourseBundle de parte de FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pregunta.setText(arbolPreguntas.getDataNodoActual());
        
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
                Aplicacion.setRoot(Aplicacion.PATH_VIEW_WIN);
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
                Aplicacion.setRoot(Aplicacion.PATH_VIEW_LOSE);
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


 
    
}
