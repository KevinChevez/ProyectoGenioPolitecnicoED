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
import static proyecto.controlador.Aplicacion.arbolPreguntas;

/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class WinControlador implements Initializable {


    /**
     * Initializes the controller class.
     * @param url Recibe una url implícita de FXML para construir la clase.
     * @param rb Recibe un ResourseBundle de parte de FXML.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToPlay(ActionEvent event) throws IOException{
        arbolPreguntas.reiniciarNodoActual();
        Aplicacion.setRoot(Aplicacion.PATH_VIEW_JUEGO);
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
