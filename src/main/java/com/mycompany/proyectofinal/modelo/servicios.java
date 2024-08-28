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
 * @author fjavi
 */
public class servicios extends conexion implements sentencias {
    private int id;
    private int costo;
    private String descripcion;

    public servicios(int idServicios, int costo, String descripcion) {
        this.id = idServicios;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public servicios() {
    }

    public int getIdServicios() {
        return id;
    }

    public void setIdServicios(int idServicios) {
        this.id = idServicios;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean insertar() {
        String sql = "insert into servicios values (?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.id);
            stm.setString(2, this.descripcion);
            stm.setInt(3, this.costo);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(servicios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consulta() {
        ArrayList<servicios> servicioss = new ArrayList();
        String sql = " select * from servicios";
        try (
                Connection con = getCon(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                int cod = rs.getInt("idServicios");
                String desc = rs.getString("descripcion");
                int cost = rs.getInt("costo");
                servicios s = new servicios(cod,cost,desc);
                servicioss.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servicioss;
    }

    @Override
    public boolean modificar() {
        String sql = "Update servicios set descripcion=?, costo=?"
                + "where idServicios=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.descripcion);
            stm.setInt(2, this.costo);
            stm.setInt(3, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(servicios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar() {
        String sql = "Delete from servicios where idServicios=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(servicios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
