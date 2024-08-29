/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.modelo;

import com.mycompany.proyectofinal.clase.conexion;
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
import javafx.fxml.Initializable;

/**
 *
 * @author Lucas
 */
public  class factura extends conexion implements sentencias{
    private int idFactura;
    private String fecha;
    private int total;
    private int codCliee;
    private String tipodeven;

    public factura() {
    }

    public factura(int idFactura, String fecha, int total, int codCliee, String tipodeven) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.total = total;
        this.codCliee = codCliee;
        this.tipodeven = tipodeven;
    }
   

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCodCliee() {
        return codCliee;
    }

    public void setCodCliee(int codCliee) {
        this.codCliee = codCliee;
    }

    public String getTipodeven() {
        return tipodeven;
    }

    public void setTipodeven(String tipodeven) {
        this.tipodeven = tipodeven;
    }

    @Override
   public boolean insertar() {
    String sql = "INSERT INTO factura (fecha,cliente_RUC,Total,tipo_venta) VALUES (?, ?, ?,?)";
    try (PreparedStatement ps = getCon().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, fecha);   // Establecer fecha
        ps.setInt(2, codCliee);   
        ps.setInt(3, total); 
        ps.setString(4,tipodeven);

        int filasInsertadas = ps.executeUpdate();
        if (filasInsertadas > 0) {
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    this.idFactura = generatedKeys.getInt(1);  // Obtener el ID autogenerado
                    
                }
            }
        }
        return true;
    } catch (SQLException e) {
        return false;
    }
}


    @Override
    public ArrayList consulta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
