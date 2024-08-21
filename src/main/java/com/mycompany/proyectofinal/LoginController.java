/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import com.mycompany.proyectofinal.clase.conexion;
import com.mycompany.proyectofinal.modelo.login;
import com.mycompany.proyectofinal.modelo.proveedores;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author fjavi
 */
public class LoginController extends conexion implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtContra;
    ObservableList<login> registros;
    ObservableList<login> registrosFiltrados;
    @FXML
    private AnchorPane btnIniciar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtUser.clear();
        txtContra.clear();
    }

    public void abrirFxml(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void iniciar(ActionEvent event) throws SQLException {

        String nombreUsuario = txtUser.getText();
        String contrasena = txtContra.getText();
        String sql = "SELECT tipo FROM usuarios WHERE usuario = ? AND contraseña = ?";
        try (Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, nombreUsuario);
            stm.setString(2, contrasena);
            try (Connection conn = getCon(); ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    switch (tipo) {
                        case "Admin": {
                            abrirFxml("Menu.fxml", "Menu principal");
                            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            ventanaActual.close();
                            break;
                        }
                        case "venta": {
                            abrirFxml("menuVentas.fxml", "Menu ventas");
                            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            ventanaActual.close();
                            break;
                        }
                        case "inventario": {
                            abrirFxml("menuInventario.fxml", "Menu ventas");
                            Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            ventanaActual.close();
                            break;
                        }
                        default:
                            break;
                    }
                    Alert alerta1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta1.setTitle("El sistema comunica:");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("Inicio de Sesion correcto");
                    alerta1.show();

                } else {
                    Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                    alerta1.setTitle("El sistema comunica:");
                    alerta1.setHeaderText(null);
                    alerta1.setContentText("Datos Incorrectos");
                    alerta1.show();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void teclado(KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            String nombreUsuario = txtUser.getText();
            String contrasena = txtContra.getText();
            String sql = "SELECT tipo FROM usuarios WHERE usuario = ? AND contraseña = ?";
            try (Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, nombreUsuario);
                stm.setString(2, contrasena);
                try (Connection conn = getCon(); ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {

                        String tipo = rs.getString("tipo");
                        switch (tipo) {
                            case "Admin": {
                                abrirFxml("Menu.fxml", "Menu principal");
                                Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                ventanaActual.close();
                                break;
                            }
                            case "venta": {
                                abrirFxml("menuVentas.fxml", "Menu ventas");
                                Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                ventanaActual.close();
                                break;
                            }
                            case "inventario": {
                                abrirFxml("menuInventario.fxml", "Menu ventas");
                                Stage ventanaActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                ventanaActual.close();
                                break;
                            }
                            default:
                                break;
                        }
                        Alert alerta1 = new Alert(Alert.AlertType.CONFIRMATION);
                        alerta1.setTitle("El sistema comunica:");
                        alerta1.setHeaderText(null);
                        alerta1.setContentText("Inicio de Sesion correcto");
                        alerta1.show();

                    } else {
                        Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                        alerta1.setTitle("El sistema comunica:");
                        alerta1.setHeaderText(null);
                        alerta1.setContentText("Datos Incorrectos");
                        alerta1.show();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
