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
import javafx.scene.control.Button;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void switchToInicio(ActionEvent event) throws IOException {
        Aplicacion.setRoot("/vista/InicioVista");
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
}
