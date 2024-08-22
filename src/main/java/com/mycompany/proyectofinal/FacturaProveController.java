/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.modelo.detalleprove;
import com.mycompany.proyectofinal.modelo.factprove;
import com.mycompany.proyectofinal.modelo.materiales;
import com.mycompany.proyectofinal.modelo.proveedores;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import com.mycompany.proyectofinal.clase.conexion;
import java.sql.SQLException;
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

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class FacturaProveController implements Initializable {

    @FXML
    private TextField txtFact;
    private TextField txtClie;
    private Button btnBuscarCliente;
    @FXML
    private DatePicker txtFecha;
    private ComboBox<String> cmbMetodo;
    private TextField txtProd;
    private Button btnBuscarProd;
    @FXML
    private TextField txtCant;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnNuevo;
    //variables globales
    int codProvee = 0;
    int codMat = 0;
    int precio = 0;
    int total = 0;
    //objetos
    proveedores p = new proveedores();
    materiales m = new materiales();
    factprove f = new factprove();
    detalleprove dp = new detalleprove();
    //listas
    ObservableList<proveedores> registrosProveedor;
    ObservableList<materiales> registrosMateriales;
    ObservableList<detalleprove> registrosDetalle;
    ObservableList<materiales>registros;
    @FXML
    private TableView<detalleprove> tablaDetalle;
    @FXML
    private TableColumn<detalleprove, Integer> columCod;
    @FXML
    private TableColumn<detalleprove, String> columDesc;
    @FXML
    private TableColumn<detalleprove, Integer> columPrecio;
    @FXML
    private TableColumn<detalleprove, Integer> columCant;
    @FXML
    private TableColumn<detalleprove, Integer> columSubTotal;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnGrabar;
    @FXML
    private Button btnImprimir;
    @FXML
    private ComboBox<String> comboProve;
    @FXML
    private ComboBox<String> comboMaterial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registrosProveedor = FXCollections.observableArrayList(p.consulta());
        registrosMateriales = FXCollections.observableArrayList(m.consulta());
        registrosDetalle = FXCollections.observableArrayList();
        ///definicion de las columnas de la tabla
        columCod.setCellValueFactory(new PropertyValueFactory<>("codMat"));
        columDesc.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columCant.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columSubTotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tablaDetalle.setTooltip(new Tooltip("Agregar productos"));
    }

    @FXML
    private void nuevo(ActionEvent event) {
        txtFact.setDisable(false);
        txtFact.requestFocus();
        txtFecha.setDisable(false);
        btnNuevo.setDisable(true);
        comboProve.setDisable(false);
        comboMaterial.setDisable(false);
        //fecha actual
        txtFecha.setValue(LocalDate.now());
        cargarProveedor();
        cargarMateriales();
        txtCant.setDisable(false);
        btnAgregar.setDisable(false);

    }

    public void actualizarStock(int idMaterial, int cantidadSumar) {
        for (materiales mat : registros) {
            if (mat.getIdMaterial() == idMaterial) {
                int nuevoStock = mat.getCantidad() + cantidadSumar;
                mat.setCantidad(nuevoStock);

                // Actualiza el stock en la base de datos
                actualizarStockEnBaseDatos(idMaterial, nuevoStock);
                break;
            }
        }
    }

    private void actualizarStockEnBaseDatos(int idMaterial, int nuevoStock) {
        String sql = "UPDATE materiales SET cantidad = ? WHERE id_material = ?";

        try (Connection con= getCon(); PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setInt(1, nuevoStock);
            stm.setInt(2, idMaterial);

            int filasActualizadas = stm.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Stock actualizado correctamente para el material ID: " + idMaterial);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el stock en la base de datos.");
        }
    }

    public void abrirFxml(String fxml, String titulo) {
//      
//        try {
//            FXMLLoader loader=new FXMLLoader(getClass().getResource(fxml));
//            Parent root=loader.load();
//            Stage stage=new Stage();
//            stage.setScene(new Scene(root));
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.setTitle(titulo);
//            stage.setResizable(false); // Evita que la ventana sea redimensionable
//            stage.setMinWidth(300); // Establece un ancho mínimo
//            stage.setMinHeight(200); // Establece una altura mínima
//            stage.showAndWait();
//        } catch (IOException ex) {
//            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    private void buscarCliente(ActionEvent event) {
//         abrirFxml("buscarCliente.fxml","Buscar Cliente");
//         codCliente = ventaSingleton.getInstance().getCodCliente();
//        for (cliente object : registrosCliente) {
//            if(object.getId()==codCliente){
//                txtClie.setText(object.getNombre()+" "+object.getApellido());
//            }               
//        }
//        cmbMetodo.setDisable(false);
//        btnBuscarProd.setDisable(false);
//        btnBuscarCliente.setDisable(true);
    }

    private void buscarProducto() {

        for (materiales object : registrosMateriales) {
            if (object.getNombre().equals(comboMaterial.getSelectionModel().getSelectedItem())) {
                codMat = object.getIdMaterial();

                precio = object.getPrecio();
            }
        }
        txtCant.setDisable(false);
        btnAgregar.setDisable(false);
    }

    private void buscarProveedor() {
        // Asegúrate de asignar correctamente el código del proveedor seleccionado
        for (proveedores object : registrosProveedor) {
            if (object.getNombre().equals(comboProve.getSelectionModel().getSelectedItem())) {
                codProvee = object.getIdProveedor();
                break;
            }
        }
    }

    @FXML
    private void agregarFila(ActionEvent event) {
        if (!"".equals(txtFact.getText()) || comboProve.getSelectionModel().getSelectedItem() != null) {
            txtFact.setDisable(true);
            comboProve.setDisable(true);
            txtFecha.setDisable(true);
        }
        if (!txtCant.getText().isEmpty()) {
            buscarProducto();  // Obtener el material seleccionado
            buscarProveedor();
            // Validar si el material ya existe en el detalle
            boolean materialRepetido = false;
            for (detalleprove det : registrosDetalle) {
                if (det.getCodMat() == codMat) {
                    materialRepetido = true;
                    break;  // Romper el bucle si se encuentra un detalle repetido
                }
            }

            if (!materialRepetido) {
                int subtotal = precio * Integer.parseInt(txtCant.getText());
                total = total + subtotal;

                // Agregar el detalle del material
                detalleprove dtv = new detalleprove(codMat, comboMaterial.getSelectionModel().getSelectedItem(), precio, Integer.parseInt(txtCant.getText()), subtotal);
                registrosDetalle.add(dtv);
                tablaDetalle.setItems(registrosDetalle);
                comboMaterial.setValue(null);
                txtTotal.setText(String.valueOf(total));
                txtCant.clear();
                txtCant.setDisable(false);
                btnGrabar.setDisable(false);
            } else {
                // Mensaje de advertencia
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("El sistema comunica;");
                alerta.setHeaderText(null);
                alerta.setContentText("El material ya se encuentra en el pedido, ¿Desea modificar la cantidad?");
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
                            for (detalleprove det : registrosDetalle) {
                                if (det.getCodMat() == codMat) {
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
    private void grabar(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("El sistema comunica;");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea grabar el pedido?");
        Optional<ButtonType> opcion = alerta.showAndWait();
        if (opcion.get() == ButtonType.OK) {
            buscarProveedor();
            f.setCod(Integer.parseInt(txtFact.getText()));
            f.setFecha(txtFecha.getValue().toString());
            f.setTotal(total);
            f.setCodProvee(codProvee);
            if (f.insertar()) {//insertado
                for (detalleprove object : registrosDetalle) {
                    dp.setCod(Integer.parseInt(txtFact.getText()));
                    dp.setCodMat(object.getCodMat());
                    dp.setCantidad(object.getCantidad());
                    dp.insertar();
                }
                Alert alertaIn = new Alert(Alert.AlertType.INFORMATION);
                alertaIn.setTitle("El sistema comunica:");
                alertaIn.setHeaderText(null);
                alertaIn.setContentText("Insertado correctamente en la base de datos");
                alertaIn.show();
                //habilitar Imprimir
                btnImprimir.setDisable(false);
            } else {
                Alert alertaIn = new Alert(Alert.AlertType.ERROR);
                alertaIn.setTitle("El sistema comunica:");
                alertaIn.setHeaderText(null);
                alertaIn.setContentText("Error. Registro no insertado.");
                alertaIn.show();
            }
        }
    }

    @FXML
    private void imprimir(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar los campos
        TextField[] fields = {txtFact, txtCant, txtClie, txtProd, txtTotal};
        for (TextField field : fields) {
            field.clear();
            field.setDisable(true);
        }

        txtFecha.setDisable(true);
        btnGrabar.setDisable(true);
        btnNuevo.setDisable(false);
        btnAgregar.setDisable(true);
        btnImprimir.setDisable(true);

    }

    private void cargarProveedor() {
        for (proveedores object : registrosProveedor) {
            comboProve.getItems().add(object.getNombre());

        }
    }

    private void cargarMateriales() {
        for (materiales object : registrosMateriales) {
            comboMaterial.getItems().add(object.getNombre());

        }
    }
}
