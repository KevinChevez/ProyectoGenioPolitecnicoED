/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto.controlador;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyecto.modelo.BinaryTree;

/**
 * 
 * @author KevinChevez
 */
public class Aplicacion extends Application {
    public static final String PATH_DATOS = "src/main/resources/archivos/datos.txt";
    public static final String PATH_VIEW_INICIO ="/vista/InicioVista";
    public static final String PATH_VIEW_JUEGO ="/vista/JuegoVista";
    public static final String PATH_VIEW_WIN = "/vista/WinVista";
    public static final String PATH_VIEW_LOSE = "/vista/LoseVista";
    
    private static Scene scene;
    public static final BinaryTree<String> arbolPreguntas = crearArbolGenio(); 
    
    @Override
    public void start(Stage primaryStage){
        try{
            scene = new Scene(loadFXML("/vista/InicioVista"));
            primaryStage.setScene(scene);
            primaryStage.show();     
        }catch(IOException e){
            e.getLocalizedMessage();
//            
//            System.out.println("Hubo un problema "+e.getMessage());
//            System.exit(0);
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
        arbol.construirArbolGenio(PATH_DATOS);
        return arbol;
    }
}
