/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.cliente;
import com.mycompany.proyectofinal.modelo.detallefactura;
import com.mycompany.proyectofinal.modelo.detalleprove;
import com.mycompany.proyectofinal.modelo.factprove;
import com.mycompany.proyectofinal.modelo.factura;
import com.mycompany.proyectofinal.modelo.materiales;
import com.mycompany.proyectofinal.modelo.proveedores;
import com.mycompany.proyectofinal.modelo.servicios;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FacturaController implements Initializable {

    @FXML
    private DatePicker txtFecha;
    @FXML
    private TextField txtCant;
    @FXML
    private Button btnAgregar;
    @FXML
    private ComboBox<String> comboCliente;
    @FXML
    private ComboBox<String> comboServicio;
    @FXML
    private TableView<detallefactura> tablaDetalle;
    @FXML
    private TableColumn<detallefactura, Integer> columCod;
    @FXML
    private TableColumn<detallefactura, String> columDesc;
    @FXML
    private TableColumn<detallefactura, Integer> columPrecio;
    @FXML
    private TableColumn<detallefactura, Integer> columCant;
    @FXML
    private TableColumn<detallefactura, Integer> columSubTotal;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnNuevo;
    @FXML
    private Button btnGrabar;
    @FXML
    private Button btnImprimir;

    int codCliee = 0;
    int codSer = 0;
    int precio = 0;
    int total = 0;
    //objetos
    cliente c = new cliente();
    servicios s = new servicios();
    factura f = new factura();
    detallefactura df = new detallefactura();

    ObservableList<cliente> registrosCliente;
    ObservableList<servicios> registrosServicios;
    ObservableList<detallefactura> registrosDetallef;
    @FXML
    private ComboBox<String> comboTipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registrosCliente = FXCollections.observableArrayList(c.consulta());
        registrosServicios = FXCollections.observableArrayList(s.consulta());
        registrosDetallef = FXCollections.observableArrayList();
        registrosDetallef.clear();
        ///definicion de las columnas de la tabla
        columCod.setCellValueFactory(new PropertyValueFactory<>("codSer"));
        columDesc.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tablaDetalle.setTooltip(new Tooltip("Agregar productos"));
    }

    @FXML
    private void agregarFila(ActionEvent event) {
        if ( comboCliente.getSelectionModel().getSelectedItem() != null) {
            
            comboCliente.setDisable(true);
            txtFecha.setDisable(true);
        }
        if (!txtCant.getText().isEmpty() || !txtCant.getText().equals(0)) {
            buscarServicio();  // Obtener el servicio seleccionado
            buscarCliente();
            // Validar si el servicio ya existe en el detalle
            boolean servicioRepetido = false;
            for (detallefactura det : registrosDetallef) {
                if (det.getCodSer() == codSer) {
                    servicioRepetido = true;
                    break;  // Romper el bucle si se encuentra un detalle repetido
                }
            }

            if (!servicioRepetido) {
                int subtotal = precio * Integer.parseInt(txtCant.getText());
                total = total + subtotal;

                // Agregar el detalle del servicio
                detallefactura dtv = new detallefactura(codSer, comboServicio.getSelectionModel().getSelectedItem(), precio, Integer.parseInt(txtCant.getText()), subtotal);
                registrosDetallef.add(dtv);
                tablaDetalle.setItems(registrosDetallef);
                comboServicio.setValue(null);
                txtTotal.setText(String.valueOf(total));
                txtCant.clear();
                txtCant.setDisable(false);
                btnGrabar.setDisable(false);
            } else {
                // Mensaje de advertencia
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("El sistema comunica;");
                alerta.setHeaderText(null);
                alerta.setContentText("El servicio ya se encuentra en el pedido, ¿Desea modificar la cantidad?");
                Optional<ButtonType> opcion = alerta.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Modificar cantidad");
                    dialog.setHeaderText("Por favor, ingrese la nueva cantidad:");
                    dialog.setContentText("Cantidad:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        try {
                            int nuevaCantidad = Integer.parseInt(result.get());
                            for (detallefactura det : registrosDetallef) {
                                if (det.getCodSer() == codSer) {
                                    // Actualizar cantidad y subtotal
                                    total -= det.getSubtotal();  // Restar el subtotal antiguo
                                    det.setCantidad(nuevaCantidad);
                                    int nuevoSubtotal = nuevaCantidad * det.getPrecio();
                                    det.setSubtotal(nuevoSubtotal);
                                    total += nuevoSubtotal;  // Agregar el nuevo subtotal
                                    txtTotal.setText(String.valueOf(total));

                                    // Refrescar la tabla para mostrar los nuevos datos
                                    tablaDetalle.refresh();
                                    break;
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Por favor, ingrese un valor numérico válido.");
                        }
                    }
                }
            }
        }

    }

    @FXML
    private void nuevo(ActionEvent event) {
        txtFecha.setDisable(false);
        btnNuevo.setDisable(true);
        comboCliente.setDisable(false);
        comboCliente.requestFocus();
        comboServicio.setDisable(false);
        comboTipo.setDisable(false);
        comboTipo.getItems().addAll("Credito", "Contado");
        //fecha actual
        txtFecha.setValue(LocalDate.now());
        cargarCliente();
        cargarServicios();
        txtCant.setDisable(false);
        btnAgregar.setDisable(false);
    }

@FXML
private void grabar(ActionEvent event) {
    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    alerta.setTitle("El sistema comunica;");
    alerta.setHeaderText(null);
    alerta.setContentText("¿Desea grabar el pedido?");

    Optional<ButtonType> opcion = alerta.showAndWait();
    if (opcion.get() == ButtonType.OK) {
        // Verificar si se ha seleccionado un tipo en el comboBox
        if (comboTipo.getSelectionModel().getSelectedItem() == null) {
            Alert alertaa = new Alert(Alert.AlertType.WARNING);
            alertaa.setTitle("El sistema comunica");
            alertaa.setHeaderText(null);
            alertaa.setContentText("Rellene todos los campos");
            alertaa.show();  // Cambié aquí la variable alertaa
        } else if (registrosDetallef.isEmpty()) {
            // Verificar si la tabla de detalles no está vacía
            Alert alertaa = new Alert(Alert.AlertType.WARNING);
            alertaa.setTitle("El sistema comunica");
            alertaa.setHeaderText(null);
            alertaa.setContentText("Debe agregar al menos un servicio al pedido");
            alertaa.show();
        } else {
            // Si todo está completo, proceder con la inserción
            buscarServicio();
            buscarCliente();
           
            f.setFecha(txtFecha.getValue().toString());
            f.setTotal(total);
            f.setCodCliee(codCliee);
            f.setTipodeven(comboTipo.getSelectionModel().getSelectedItem());

            if (f.insertar()) { // Insertar la factura
                for (detallefactura object : registrosDetallef) {
                    df.setCod(f.getIdFactura());
                    df.setCodSer(object.getCodSer());
                    df.setCantidad(object.getCantidad());
                    df.insertar();
                }
                // Mostrar mensaje de éxito
                Alert alertaIn = new Alert(Alert.AlertType.INFORMATION);
                alertaIn.setTitle("El sistema comunica:");
                alertaIn.setHeaderText(null);
                alertaIn.setContentText("Insertado correctamente en la base de datos");
                alertaIn.show();
                
                // Habilitar el botón de Imprimir
                btnImprimir.setDisable(false);
            } else {
                // Mostrar mensaje de error si no se pudo insertar
                Alert alertaIn = new Alert(Alert.AlertType.ERROR);
                alertaIn.setTitle("El sistema comunica:");
                alertaIn.setHeaderText(null);
                alertaIn.setContentText("Error. Registro no insertado.");
                alertaIn.show();
            }
        }
    }

    // Deshabilitar campos y botones después de grabar
    btnGrabar.setDisable(true);
    comboServicio.setDisable(true);
    txtCant.setDisable(true);
    btnAgregar.setDisable(true);
}

    @FXML
    private void cancelar(ActionEvent event) {
        TextField[] fields = { txtCant, txtTotal};
        for (TextField field : fields) {
            field.clear();
            field.setDisable(false);
        }
        registrosDetallef.clear();
        tablaDetalle.refresh();
        txtFecha.setValue(null);
        txtCant.clear();
        txtCant.setDisable(true);
        
        
        comboServicio.setValue(null);
        comboServicio.getItems().clear();
        comboCliente.setValue(null);
        comboCliente.getItems().clear();
        comboCliente.setDisable(true);
        txtFecha.setDisable(true);
        
        comboServicio.setDisable(true);
        btnGrabar.setDisable(true);
        btnNuevo.setDisable(false);
        btnAgregar.setDisable(true);
        btnImprimir.setDisable(true);

    }

    @FXML
    private void imprimir(ActionEvent event) {
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaActual.close();
    }

    private void cargarCliente() {
        for (cliente object : registrosCliente) {
            comboCliente.getItems().add(object.getNombre());

        }
    }

    private void cargarServicios() {
        for (servicios object : registrosServicios) {
            comboServicio.getItems().add(object.getDescripcion());

        }
    }

    
    private void buscarCliente() {
    for (cliente object : registrosCliente) {
        if (object.getNombre().equals(comboCliente.getSelectionModel().getSelectedItem())) {
            codCliee = object.getRuc();  // Debe ser el RUC correcto del cliente
        }               
    }
}

    private void buscarServicio() {

        for (servicios object : registrosServicios) {
            if (object.getDescripcion().equals(comboServicio.getSelectionModel().getSelectedItem())) {
                codSer = object.getIdServicios();

                precio = object.getCosto();
            }
        }
        txtCant.setDisable(false);
        btnAgregar.setDisable(false);
    }
}
