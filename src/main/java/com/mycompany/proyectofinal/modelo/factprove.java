/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.modelo;

import com.mycompany.proyectofinal.clase.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fjavi
 */
public class factprove extends conexion implements sentencias {

    private int cod;
    private String fecha;
    private int total;
    private int codProvee;

    public factprove() {
    }

    public factprove(int cod, String fecha, int total, int codProvee) {
        this.cod = cod;
        this.fecha = fecha;
        this.total = total;
        this.codProvee = codProvee;
    }

    public int getCodProvee() {
        return codProvee;
    }

    public void setCodProvee(int codProvee) {
        this.codProvee = codProvee;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
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

    @Override
    public boolean insertar() {
        String sql = "INSERT INTO factura_proveedor (fecha, total, codProvee) VALUES (?, ?, ?)";
        try (PreparedStatement stm = getCon().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // Asignar los valores a los parámetros de la consulta
            stm.setString(1, this.fecha);
            stm.setInt(2, this.total);
            stm.setInt(3, this.codProvee);

            // Ejecutar la actualización
            int filasInsertadas = stm.executeUpdate();
            if (filasInsertadas > 0) {
                // Obtener la clave generada automáticamente (cod autoincrementado)
                try (var generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.cod = generatedKeys.getInt(1);  // Asignar el cod generado
                       
                    }
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(factprove.class.getName()).log(Level.SEVERE, null, ex);
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
