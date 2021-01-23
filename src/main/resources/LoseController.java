/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author KevinChevez
 */
public class LoseController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void animalAccion(ActionEvent event) {
    }

    @FXML
    private void preguntaAccion(ActionEvent event) {
    }

    @FXML
    private void respuestaAccion(ActionEvent event) {
    }
    
}
