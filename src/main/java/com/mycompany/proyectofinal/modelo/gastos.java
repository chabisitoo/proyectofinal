/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.modelo;

import com.mycompany.proyectofinal.clase.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Lucas
 */
public class gastos extends conexion  implements sentencias{
    private int id;
    private String mes;
    private int monto;
    private String descripcion;

    public gastos() {
    }

    public gastos(String mes, int monto, String descripcion,int id) {
        this.id = id;
        this.mes = mes;
        this.monto = monto;
        this.descripcion = descripcion;
    }
    
    
    
    public int getCod() {
        return id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean insertar() {
       String sql = "insert into gastos (mes,descripcion,monto) values (?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.mes);
            stm.setString(2, this.descripcion);
            stm.setInt(3, this.monto);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(gastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    @Override
    public ArrayList consulta() {
        ArrayList<gastos> gastos = new ArrayList();
        String sql = " select * from gastos";
        try (
                Connection con = getCon(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                String month = rs.getString("mes");
                int mon = rs.getInt("monto");
                String desc = rs.getString("descripcion");
                int cod=rs.getInt("idGasto");
                gastos g = new gastos(month,mon,desc,cod);
                gastos.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(gastos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gastos;

    }


    @Override
    public boolean modificar() {
        String sql = "Update gastos set mes=?, monto=?, descripcion=?  "
                + "where idGasto=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.mes);
            stm.setInt(2, this.monto);
            stm.setString(3, this.descripcion);
            stm.setInt(4, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(gastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
      String sql = "Delete from gastos where idGasto=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(gastos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
}
