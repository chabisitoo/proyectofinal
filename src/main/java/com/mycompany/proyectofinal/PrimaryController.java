/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.cliente;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
 * @author fjavi
 */
public class PrimaryController implements Initializable {

    @FXML
    private TextField txtRuc;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDirec;
    @FXML
    private TextField txtNum;
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
    private TableView<cliente> tablaCliente;
    @FXML
    private TableColumn<cliente, Integer> columRuc;
    @FXML
    private TableColumn<cliente, String> columNom;
    @FXML
    private TableColumn<cliente, String> columDirec;
    @FXML
    private TableColumn<cliente, String> columNum;
    cliente c = new cliente();
    ObservableList<cliente> registros;
    ObservableList<cliente> registrosFiltrados;
    boolean modificar = false;//bandera para modificar
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class. /** Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hola");
        System.out.println(c.consulta());
        mostrarDatos();

    }

    @FXML
    private void nuevo(ActionEvent event) {
        btnNuevo.setDisable(true);
        btnGuardar.setDisable(false);
        txtRuc.setDisable(false);
        txtNom.setDisable(false);
        txtDirec.setDisable(false);
        txtNum.setDisable(false);
    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        c = tablaCliente.getSelectionModel().getSelectedItem();
        txtRuc.setText(String.valueOf(c.getRuc()));
        txtNom.setText(c.getNombre());
        txtDirec.setText(c.getDireccion());
        txtNum.setText(c.getTelefono());
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
            tablaCliente.setItems(registros);
        } else {
            registrosFiltrados.clear();
            for (cliente registro : registros) {
                if (registro.getNombre().toLowerCase().contains(buscar.toLowerCase())) {
                    registrosFiltrados.add(registro);
                }//fin if
            }
            tablaCliente.setItems(registrosFiltrados);
        }
    }

    @FXML
    private void guardar(ActionEvent event) {

        if ("".equals(txtRuc.getText()) || "".equals(txtNom.getText()) || "".equals(txtDirec.getText()) || "".equals(txtNum.getText())) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Rellene todos los campos");
            alerta.show();
        } else {
            int cod = Integer.parseInt(txtRuc.getText());
            String nom = txtNom.getText();
            String dir = txtDirec.getText();
            String tel = txtNum.getText();
            c.setRuc(cod);
            c.setNombre(nom);
            c.setDireccion(dir);
            c.setTelefono(tel);
            if (modificar) {
                if (c.modificar()) {
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
                if (c.insertar()) {
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

        txtRuc.setText("");
        txtNom.setText("");
        txtDirec.setText("");
        txtNum.setText("");
        txtRuc.setDisable(true);
        txtNom.setDisable(true);
        txtDirec.setDisable(true);
        txtNum.setDisable(true);
        btnGuardar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
        mostrarDatos();
    }

    public void mostrarDatos() {
        registros = FXCollections.observableArrayList(c.consulta());
        columRuc.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columDirec.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columNum.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tablaCliente.setItems(registros);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar txt
        txtRuc.clear();
        txtNom.clear();
        txtDirec.clear();
        txtNum.clear();
        //deshabilitartxt
        txtRuc.setDisable(false);
        txtNom.setDisable(false);
        txtDirec.setDisable(false);
        txtNum.setDisable(false);
        //deshabilitar btn
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        //habilitar
        btnNuevo.setDisable(false);
        txtRuc.setDisable(true);
        txtNom.setDisable(true);
        txtDirec.setDisable(true);
        txtNum.setDisable(true);
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
            int cod = Integer.parseInt(txtRuc.getText());
            c.setRuc(cod);
            if (c.eliminar()) {
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
        txtRuc.setDisable(true);
        txtNom.setDisable(true);
        txtDirec.setDisable(true);
        txtNum.setDisable(true);
    }

    @FXML
    private void modificar(ActionEvent event) {
        txtRuc.setDisable(true);
        txtNom.setDisable(false);
        txtDirec.setDisable(false);
        txtNum.setDisable(false);
        btnGuardar.setDisable(false);

        btnNuevo.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        modificar = true;

    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaActual.close();
    }

}
