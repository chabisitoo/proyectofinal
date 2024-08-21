/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.materiales;
import com.mycompany.proyectofinal.modelo.servicios;
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
 * @author fjavi
 */
public class MaterialesController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtCant;
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
    private TableView<materiales> tablaMateriales;
    @FXML
    private TableColumn<materiales, Integer> columId;
    @FXML
    private TableColumn<materiales, String> columNom;
    @FXML
    private TableColumn<materiales, Integer> columCant;
    @FXML
    private TextField txtBuscar;
    ObservableList<materiales> registros;
    ObservableList<materiales> registrosFiltrados;
    boolean modificar = false;
    materiales m = new materiales();
    Integer i= null;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TableColumn<?, ?> columPrecio;
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
        txtNom.setDisable(false);
        txtCant.setDisable(false);
        txtPrecio.setDisable(false);

    }

    @FXML
    private void guardar(ActionEvent event) {
 
        if ("".equals(txtNom.getText())||"".equals(txtId.getText())||"".equals(txtCant.getText())) {
           
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("El sistema comunica");
            alerta.setHeaderText(null);
            alerta.setContentText("Rellene todos los campos");
            alerta.show();
        } else {
            int id = Integer.parseInt(txtId.getText());
            String nom = txtNom.getText();
            int cant = Integer.parseInt(txtCant.getText());
            int prec = Integer.parseInt(txtPrecio.getText());
            m.setId(id);
            m.setNombre(nom);
            m.setCantidad(cant);
            m.setPrecio(prec);
            if (modificar) {
                if (m.modificar()) {
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
                if (m.insertar()) {
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
        txtNom.setText("");
        txtCant.setText("");
        txtPrecio.setText("");
        txtId.setDisable(true);
        txtNom.setDisable(true);
        txtCant.setDisable(true);
        txtPrecio.setDisable(true);
        btnGuardar.setDisable(true);
        btnNuevo.setDisable(false);
        btnCancelar.setDisable(true);
        mostrarDatos();

    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar txt
        txtId.clear();
        txtNom.clear();
        txtCant.clear();
        txtPrecio.clear();

        //deshabilitartxt
        txtId.setDisable(false);
        txtNom.setDisable(false);
        txtCant.setDisable(false);

        //deshabilitar btn
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnCancelar.setDisable(true);
        //habilitar
        btnNuevo.setDisable(false);
        txtId.setDisable(true);
        txtNom.setDisable(true);
        txtCant.setDisable(true);
        txtPrecio.setDisable(true);
    }

    public void mostrarDatos() {
        registros = FXCollections.observableArrayList(m.consulta());
        columId.setCellValueFactory(new PropertyValueFactory<>("idMaterial"));
        columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        tablaMateriales.setItems(registros);
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
            m.setId(cod);
            if (m.eliminar()) {
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
        txtNom.setDisable(true);
        txtCant.setDisable(true);

    }

    @FXML
    private void modificar(ActionEvent event) {
        txtId.setDisable(true);
        txtNom.setDisable(false);
        txtCant.setDisable(false);
        txtPrecio.setDisable(false);
        btnGuardar.setDisable(false);

        btnNuevo.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        modificar = true;

    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        m = tablaMateriales.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(m.getIdMaterial()));
        txtNom.setText(m.getNombre());
        txtCant.setText(String.valueOf(m.getCantidad()));
        txtPrecio.setText(String.valueOf(m.getPrecio()));

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
            tablaMateriales.setItems(registros);
        } else {
            registrosFiltrados.clear();
            for (materiales registro : registros) {
                if (registro.getNombre().toLowerCase().contains(buscar.toLowerCase())) {
                    registrosFiltrados.add(registro);
                }//fin if
            }
            tablaMateriales.setItems(registrosFiltrados);
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaActual.close();
    }

}
