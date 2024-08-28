/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.modelo;

import com.mycompany.proyectofinal.clase.conexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String sql = "insert into factura values (?,?,?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.idFactura);
            stm.setString(2, this.fecha);
            stm.setInt(3, this.total);
            stm.setInt(4, this.codCliee);
            stm.setString(5, this.tipodeven);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
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
