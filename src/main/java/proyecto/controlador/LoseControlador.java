package proyecto.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static proyecto.controlador.Aplicacion.arbolPreguntas;
//import static proyecto.controlador.JuegoControlador.nodoActual;


/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class LoseControlador  implements Initializable{

    @FXML
    private Button btnVolverAJugar;
    @FXML
    private TextField animalField;
    @FXML
    private Label preguntaText;
    @FXML
    private TextField preguntaField;
    @FXML
    private Label respuestaText;
    @FXML
    private TextField respuestaField;
    @FXML
    private Label mensajeFinalText;
    
    private String pregunta;
    private String animalNuevo;

    

    /**
     * Initializes the controller class.
     * @param url Recibe una url implícita de FXML para construir la clase.
     * @param rb Recibe un ResourseBundle de parte de FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }  

    @FXML
    private void animalAccion(ActionEvent event) {
        JuegoControlador jc= new JuegoControlador();
        animalNuevo = animalField.getText(); 
        animalNuevo = animalNuevo.substring(0,1).toUpperCase()+animalNuevo.substring(1);
        animalField.setDisable(true);
        preguntaText.setText(
                "Escribe una pregunta que me permita diferenciar entre "+
                        animalNuevo.toLowerCase()+" y "+
                        jc.getNodoActual().getData().toLowerCase());
        preguntaField.setVisible(true);
        preguntaText.setVisible(true);
    }

    @FXML
    private void preguntaAccion(ActionEvent event) {
        preguntaField.setDisable(true);
        if(!preguntaField.getText().isBlank()){
            pregunta = preguntaField.getText();
            respuestaText.setText("Para "+ animalNuevo.toLowerCase()+
                    ", la respuesta a la pregunta: \""+
                    pregunta.toLowerCase()+"\", es si o no?");
            respuestaField.setVisible(true);
            respuestaText.setVisible(true);
        }      
    }

    @FXML
    private void respuestaAccion(ActionEvent event) {
        JuegoControlador jc= new JuegoControlador();
        String resp = respuestaField.getText();
        respuestaField.setDisable(true);
        mensajeFinalText.setVisible(true);
        String respAnterior = jc.getNodoActual().getData();
        arbolPreguntas.setDataNodoActual(pregunta);
        if(resp.toLowerCase().equals("si") || resp.toLowerCase().equals("sí")){
            arbolPreguntas.setHijosNodeActual(animalNuevo, false);
            arbolPreguntas.setHijosNodeActual(respAnterior, true);
        }
        else if(resp.toLowerCase().equals("no")){
            arbolPreguntas.setHijosNodeActual(animalNuevo, true);
            arbolPreguntas.setHijosNodeActual(respAnterior, false);
        }
        btnVolverAJugar.setDisable(false);
        arbolPreguntas.guardarTxtArbolPreOrden();
    }

    @FXML
    private void switchToPlay(ActionEvent event) throws IOException {
        btnVolverAJugar.setDisable(true);
        arbolPreguntas.reiniciarNodoActual();
        Aplicacion.setRoot(Aplicacion.PATH_VIEW_JUEGO);
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    
    
}
