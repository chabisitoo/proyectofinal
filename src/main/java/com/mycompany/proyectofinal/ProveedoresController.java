/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.cliente;
import com.mycompany.proyectofinal.modelo.materiales;
import com.mycompany.proyectofinal.modelo.proveedores;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class ProveedoresController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtDirec;
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
    @FXML
    private TableColumn<proveedores, Integer> columId;
    @FXML
    private TableColumn<proveedores, String> columNom;
    @FXML
    private TableColumn<proveedores, String> columCont;
    @FXML
    private TableColumn<proveedores, String> columDirec;
    @FXML
    private TextField txtBuscar;
    ObservableList<proveedores> registros;
    ObservableList<proveedores> registrosFiltrados;
    boolean modificar = false;
    proveedores p = new proveedores();
    @FXML
    private TableView<proveedores> tablaProveedores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDatos();
    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        txtId.setDisable(false);
        txtNombre.setDisable(false);
        txtContacto.setDisable(false);
        txtDirec.setDisable(false);
    }

    @FXML
    private void guardar(ActionEvent event) {

        if ("".equals(txtNombre.getText()) || "".equals(txtContacto.getText()) || "".equals(txtDirec.getText()) || "".equals(txtId.getText())) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Rellene todos los campos");
            alerta.show();
        } else {
            int idProveedor = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String contacto = txtContacto.getText();
            String direccion = txtDirec.getText();
            p.setIdProveedor(idProveedor);
            p.setNombre(nombre);
            p.setContacto(contacto);
            p.setDireccion(direccion);
            if (modificar) {
                if (p.modificar()) {
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
                if (p.insertar()) {
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
        txtNombre.setText("");
        txtContacto.setText("");
        txtDirec.setText("");
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtDirec.setDisable(true);
        txtContacto.setDisable(true);
        btnGuardar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
        mostrarDatos();
    }

    public void mostrarDatos() {
        registros = FXCollections.observableArrayList(p.consulta());
        columId.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columCont.setCellValueFactory(new PropertyValueFactory<>("contacto"));
        columDirec.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tablaProveedores.setItems(registros);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar txt
        txtId.clear();
        txtNombre.clear();
        txtContacto.clear();
        txtDirec.clear();
        //deshabilitartxt
        txtId.setDisable(false);
        txtNombre.setDisable(false);
        txtContacto.setDisable(false);
        txtDirec.setDisable(false);
        //deshabilitar btn
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        //habilitar
        btnNuevo.setDisable(false);
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtContacto.setDisable(true);
        txtDirec.setDisable(true);
    }

    @FXML
    private void modificar(ActionEvent event) {
        txtId.setDisable(true);
        txtNombre.setDisable(false);
        txtContacto.setDisable(false);
        txtDirec.setDisable(false);
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
            p.setIdProveedor(cod);
            if (p.eliminar()) {
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
        txtNombre.setDisable(true);
        txtContacto.setDisable(true);
        txtDirec.setDisable(true);

    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        p = tablaProveedores.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(p.getIdProveedor()));
        txtNombre.setText(p.getNombre());
        txtContacto.setText(p.getContacto());
        txtDirec.setText(p.getDireccion());
        //habilitar boton
        btnModificar.setDisable(false);
        btnEliminar.setDisable(false);
        btnCancelar.setDisable(false);
        btnNuevo.setDisable(true);
    }

    @FXML
    private void busqueda(KeyEvent event) {
        registrosFiltrados = FXCollections.observableArrayList();
        String buscar = txtBuscar.getText();
        if (buscar.isEmpty()) {
            tablaProveedores.setItems(registros);
        } else {
            registrosFiltrados.clear();
            for (proveedores registro : registros) {
                if (registro.getNombre().toLowerCase().contains(buscar.toLowerCase())) {
                    registrosFiltrados.add(registro);
                }//fin if
            }
            tablaProveedores.setItems(registrosFiltrados);
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaActual.close();
    }
}
