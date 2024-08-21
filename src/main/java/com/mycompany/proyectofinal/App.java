package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.clase.conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
//mira javi
/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        conexion conectado=new conexion();
        if(conectado.getCon()!=null){
            //mostrar menu    
            scene = new Scene(loadFXML("login"), 700, 500);
            stage.initStyle(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.setTitle("Inicio de Sesion");
            stage.show();
        }else{
            Alert alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de conexion");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, revise la conexion a la base de datos");
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}