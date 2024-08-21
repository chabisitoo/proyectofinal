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
public class cliente extends conexion implements sentencias {

    private int ruc;
    private String nombre;
    private String direccion;
    private String telefono;

    public cliente() {
    }

    public cliente(int ruc, String nombre, String direccion, String num) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = num;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String num) {
        this.telefono = num;
    }

    
    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean insertar() {
        String sql = "insert into cliente values (?,?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.ruc);
            stm.setString(2, this.nombre);
            stm.setString(3, this.direccion);
            stm.setString(4, this.telefono);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consulta() {
        ArrayList<cliente> clientes = new ArrayList();
        String sql = " select * from cliente";
        try (
                Connection con = getCon(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                int cod = rs.getInt("ruc");
                String nom = rs.getString("nombre");
                String dir = rs.getString("direccion");
                String tele = rs.getString("telefono");
                cliente c = new cliente(cod,nom,dir,tele);
                clientes.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;

    }

    @Override
    public boolean modificar() {
        String sql = "Update cliente set nombre=?, direccion=?,telefono=?  "
                + "where ruc=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.nombre);
            stm.setString(2, this.direccion);
            stm.setString(3, this.telefono);
            stm.setInt(4,this.ruc);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean eliminar() {
        String sql = "Delete from cliente where ruc=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.ruc);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
