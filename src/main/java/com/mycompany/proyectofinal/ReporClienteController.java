/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.clase.conexion;
import com.mycompany.proyectofinal.clase.reportes;
import com.mycompany.proyectofinal.modelo.cliente;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class ReporClienteController extends conexion implements Initializable {
    cliente c=new cliente();
    @FXML
    private ComboBox<String> comboCliente;
    @FXML
    private Button btnImprimir;
    ObservableList<cliente> registrosCliente;
    int codCliee = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registrosCliente = FXCollections.observableArrayList(c.consulta());
        cargarCliente();
    }

    private void cargarCliente() {
        for (cliente object : registrosCliente) {
            comboCliente.getItems().add(object.getNombre());

        }
    }

    private void buscarCliente() {
        for (cliente object : registrosCliente) {
            if (object.getNombre().equals(comboCliente.getSelectionModel().getSelectedItem())) {
                codCliee = object.getRuc(); // Asignar a codCliee
            }
        }
    }

    @FXML
    private void imprimir(ActionEvent event) {
        if (comboCliente.getSelectionModel().getSelectedItem() == null) {     
       
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setTitle("Selección requerida");
         alert.setHeaderText(null);
         alert.setContentText("Por favor, seleccione un cliente.");
         alert.showAndWait();
        return; // Terminar el método si no hay selección
    }

        buscarCliente();
        String ubicacion = "/reportes/ServiciosCliente.jasper";
        String titulo = "Imprimir reporte por cliente";
        generarReporteParametro(ubicacion, titulo,codCliee);
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }

    public void generarReporteParametro(String ubicacion, String titulo, int parametro) {

        try {
            // Ruta al archivo .jasper
            String reportPath = getClass().getResource(ubicacion).getPath();

            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            // Agrega parámetros según sea necesario
            parameters.put("ruc", parametro);

            // Llenar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, getCon());

            // Mostrar el informe en una nueva ventana
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(titulo);
            viewer.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
