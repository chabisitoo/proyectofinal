/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.cliente;
import com.mycompany.proyectofinal.modelo.servicios;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class ServiciosController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCost;
    @FXML
    private TextArea txtDesc;
    @FXML
    private TableView<servicios> tablaService;
    @FXML
    private TableColumn<servicios, Integer> columId;
    @FXML
    private TableColumn<servicios, String> columCost;
    @FXML
    private TableColumn<servicios, String> columDesc;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    servicios s = new servicios();
    ObservableList<servicios> registros;
    ObservableList<servicios> registrosFiltrados;

    boolean modificar = false;//bandera para modificar
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatos();
    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        s = tablaService.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(s.getIdServicios()));
        txtCost.setText(String.valueOf(s.getCosto()));
        txtDesc.setText(s.getDescripcion());

        //habilitar boton
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        btnNuevo.setDisable(true);
    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        txtId.setDisable(false);
        txtCost.setDisable(false);
        txtDesc.setDisable(false);

    }

    public void mostrarDatos() {
        registros = FXCollections.observableArrayList(s.consulta());
        columId.setCellValueFactory(new PropertyValueFactory<>("idServicios"));
        columCost.setCellValueFactory(new PropertyValueFactory<>("costo"));
        columDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tablaService.setItems(registros);
    }

    @FXML
    private void guardar(ActionEvent event) {

        if ("".equals(txtCost.getText()) || "".equals(txtDesc.getText()) || "".equals(txtId.getText())) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Rellene todos los campos");
            alerta.show();
        } else {
            int cod = Integer.parseInt(txtId.getText());
            int cost = Integer.parseInt(txtCost.getText());
            String desc = txtDesc.getText();
            s.setIdServicios(cod);
            s.setCosto(cost);
            s.setDescripcion(desc);
            if (modificar) {
                if (s.modificar()) {
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
                if (s.insertar()) {
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

        txtId.setText("");
        txtCost.setText("");
        txtDesc.setText("");
        txtId.setDisable(true);
        txtCost.setDisable(true);
        txtDesc.setDisable(true);
        btnGuardar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
        mostrarDatos();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        txtId.clear();
        txtCost.clear();
        txtDesc.clear();
        //deshabilitartxt
        txtId.setDisable(false);
        txtCost.setDisable(false);
        txtDesc.setDisable(false);
        //deshabilitar btn
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        //habilitar
        btnNuevo.setDisable(false);
        txtId.setDisable(true);
        txtCost.setDisable(true);
        txtDesc.setDisable(true);
    }

    @FXML
    private void modificar(ActionEvent event) {
        txtId.setDisable(true);
        txtCost.setDisable(false);
        txtDesc.setDisable(false);
        btnGuardar.setDisable(false);

        btnNuevo.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        modificar = true;
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
            int cod = Integer.parseInt(txtId.getText());
            s.setIdServicios(cod);
            if (s.eliminar()) {
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
        txtId.setDisable(true);
        txtCost.setDisable(true);
        txtDesc.setDisable(true);

    }

    @FXML
    private void busqueda(KeyEvent event) {
        registrosFiltrados = FXCollections.observableArrayList();
        String buscar = txtBuscar.getText();
        if (buscar.isEmpty()) {
            tablaService.setItems(registros);
        } else {
            registrosFiltrados.clear();
            for (servicios registro : registros) {
                if (registro.getDescripcion().toLowerCase().contains(buscar.toLowerCase())) {
                    registrosFiltrados.add(registro);
                }//fin if
            }
            tablaService.setItems(registrosFiltrados);
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaActual.close();
    }

}
