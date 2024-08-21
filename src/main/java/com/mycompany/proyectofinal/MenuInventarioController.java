/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.clase.reportes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class MenuInventarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void abrirFxml(String fxml, String titulo){
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource(fxml));
            Parent root=loader.load();
            Stage stage=new Stage();
            stage.initStyle(StageStyle.UNDECORATED);

            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @FXML
    private void materiales(ActionEvent event) {
        abrirFxml("materiales.fxml","Formulario Materiales");
    }

    @FXML
    private void proveedores(ActionEvent event) {
        abrirFxml("proveedores.fxml","Formulario Proveedores");
    }

    @FXML
    private void cerrar(ActionEvent event) {
        abrirFxml("login.fxml","Login");
        MenuItem menuItem = (MenuItem) event.getSource();
        Stage ventanaActual = (Stage) menuItem.getParentPopup().getOwnerWindow();
        ventanaActual.close();
        
    }
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    
}

