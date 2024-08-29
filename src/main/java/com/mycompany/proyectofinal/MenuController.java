/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;
import com.mycompany.proyectofinal.clase.reportes;
import com.mycompany.proyectofinal.modelo.login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class MenuController implements Initializable {

    @FXML
    private MenuItem menuMateriales;
    @FXML
    private MenuItem menuProveedores;
    @FXML
    private MenuItem menuCliente;
    @FXML
    private MenuItem menuServicio;
    @FXML
    private MenuItem menuGastos;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
    private void servicios(ActionEvent event) {
        abrirFxml("servicios.fxml","Formulario Servicios");
    }

    @FXML
    private void cliente(ActionEvent event) {
        abrirFxml("primary.fxml","Formulario Cliente");
    }

    @FXML
    private void gastos(ActionEvent event) {
        abrirFxml("gastos.fxml","Formulario Gastos");
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
    private void reporCliente(ActionEvent event) {
        reportes r=new reportes();
              String ubicacion="/reportes/clienteF.jasper";
   String titulo="Informe de Cliente";
        r.generarReporte(ubicacion, titulo);
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void factProvee(ActionEvent event) {
        abrirFxml("facturaProve.fxml","Formulario Factura Proveedores");
    }

    @FXML
    private void factura(ActionEvent event) {
        abrirFxml("factura.fxml","Formulario Factura");
    }

}
