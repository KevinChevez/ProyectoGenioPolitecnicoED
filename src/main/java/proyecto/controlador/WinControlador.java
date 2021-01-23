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
import static proyecto.constantes.Constantes.*;
import static proyecto.controlador.Aplicacion.arbolPreguntas;

/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class WinControlador implements Initializable {

    @FXML
    private Button btnVolverAJugar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToPlay(ActionEvent event) throws IOException{
        arbolPreguntas.reiniciarNodoActual();
        Aplicacion.setRoot(PATH_VIEW_JUEGO);
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
