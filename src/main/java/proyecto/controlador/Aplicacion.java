/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.controlador;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyecto.constantes.Constantes;
import proyecto.modelo.BinaryTree;

/**
 * 
 * @author KevinChevez
 */
public class Aplicacion extends Application {
    private static Scene scene;
    public static BinaryTree<String> arbolPreguntas = crearArbolGenio();
    
    @Override
    public void start(Stage primaryStage){
        try{
            scene = new Scene(loadFXML("/vista/InicioVista"));
            primaryStage.setScene(scene);
            primaryStage.show();     
        }catch(Exception e){
            System.out.println("Hubo un problema "+e.getMessage());
            System.exit(0);
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();       
        fxmlLoader.setLocation(Aplicacion.class.getResource(fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private static BinaryTree<String> crearArbolGenio(){
        BinaryTree<String> arbol = new BinaryTree<>();
        arbol.construirArbolGenio(Constantes.PATH_DATOS);
        return arbol;
    }
}
