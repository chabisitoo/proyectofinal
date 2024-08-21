/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.gastos;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class GastosController implements Initializable {

    private TextField txtMes;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextField txtDesc;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private TableView<gastos> tablaGasto;
    gastos g = new gastos();
    ObservableList<gastos> registros;
    ObservableList<gastos> registrosFiltrados;
    boolean modificar = false;

    @FXML
    private TableColumn<gastos, String> columMes;
    @FXML
    private TableColumn<gastos, Integer> columMonto;
    @FXML
    private TableColumn<gastos, String> columDesc;
    @FXML
    private ComboBox<String> comboMes;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatos();
        comboMes.getItems().addAll(
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
);
    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        comboMes.setDisable(false);
        txtMonto.setDisable(false);
        txtDesc.setDisable(false);
        comboMes.setPromptText("Seleccione el mes");

    }

    @FXML
    private void guardar(ActionEvent event) {

        if ("".equals(txtMonto.getText()) || "".equals(txtDesc.getText())) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Rellene todos los campos");
            alerta.show();
        } else {
            int monto = Integer.parseInt(txtMonto.getText());
            String mes = txtMes.getText();
            String descripcion = txtDesc.getText();
            g.setMes(comboMes.getSelectionModel().getSelectedItem());
            g.setMonto(monto);
            g.setDescripcion(descripcion);
            if (modificar) {
                if (g.modificar()) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("El sistema comunica");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Modificado correctamente");
                    alerta.show();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("El sistema comunica");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Error al modificar");
                    alerta.show();
                }
                modificar = false;
            } else {
                if (g.insertar()) {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("El sistema comunica");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Insertado correctamente");
                    alerta.show();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("El sistema comunica");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Error al insertar");
                    alerta.show();
                }
            }//fin else modificar

        }

        comboMes.setValue(null);
        txtMonto.setText("");
        txtDesc.setText("");
        comboMes.setDisable(true);
        txtMonto.setDisable(true);
        txtDesc.setDisable(true);
        btnGuardar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
        mostrarDatos();

    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar txt
        comboMes.setValue(null);
        txtMonto.clear();
        txtDesc.clear();

        //deshabilitartxt
        comboMes.setDisable(false);
        txtMonto.setDisable(false);
        txtDesc.setDisable(false);

        //deshabilitar btn
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        //habilitar
        btnNuevo.setDisable(false);
        comboMes.setDisable(true);
        txtMonto.setDisable(true);
        txtDesc.setDisable(true);
    }

    public void mostrarDatos() {
        registros = FXCollections.observableArrayList(g.consulta());
        columMes.setCellValueFactory(new PropertyValueFactory<>("mes"));
        columMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        columDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaGasto.setItems(registros);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Aviso de Borrado");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea eliminar el registro seleccionado?");
        Optional<ButtonType> opcion;
        opcion = alerta.showAndWait();
        if (opcion.get() == ButtonType.OK) {
            String descripcion = (txtDesc.getText());
            g.setDescripcion(descripcion);
            if (g.eliminar()) {
                Alert alerta1 = new Alert(Alert.AlertType.INFORMATION);
                alerta1.setTitle("El sistema comunica:");
                alerta1.setHeaderText(null);
                alerta1.setContentText("Datos eliminados correctamente");
                alerta1.show();
            } else {
                Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                alerta1.setTitle("El sistema comunica:");
                alerta1.setHeaderText(null);
                alerta1.setContentText("Datos no eliminados");
                alerta1.show();
            }//fin else eliminar
        }//fin ok
        mostrarDatos();//actualiza la tabla
        cancelar(event);//limpia el formulario
        comboMes.setDisable(true);
        txtMonto.setDisable(true);
        txtDesc.setDisable(true);

    }

    @FXML
    private void modificar(ActionEvent event) {
        comboMes.setDisable(false);
        txtMonto.setDisable(false);
        txtDesc.setDisable(false);
        btnGuardar.setDisable(false);

        btnNuevo.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        modificar = true;

    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        g = tablaGasto.getSelectionModel().getSelectedItem();
        comboMes.setValue(g.getMes());
        txtMonto.setText(String.valueOf(g.getMonto()));
        txtDesc.setText(g.getDescripcion());

        //habilitar boton
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        btnNuevo.setDisable(true);

    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaActual.close();
    }

}
