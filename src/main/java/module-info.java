module ProyectoGenioPolitecnico {    
    requires java.base;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.web;
    requires javafx.media;
    requires javafx.fxml;
    
    opens proyecto.controlador to javafx.fxml, javafx.base,javafx.controls,javafx.graphics,javafx.swing,javafx.web,javafx.media,java.base;
    exports proyecto.controlador;
    

    
    
}