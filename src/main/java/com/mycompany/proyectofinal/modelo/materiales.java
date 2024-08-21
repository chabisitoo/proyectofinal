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
public class materiales extends conexion implements sentencias {
    private int id;
    private String nombre;
    private int cantidad;
    private int precio;

    public materiales() {
    }

    public materiales(int id, String nombre, int cantidad, int precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    
    public int getIdMaterial() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    @Override
   public boolean insertar() {
       String sql = "insert into materiales values (?,?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.id);
            stm.setString(2, this.nombre);
            stm.setInt(3, this.cantidad);
            stm.setInt(4,this.precio);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(materiales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    @Override
    public ArrayList consulta() {
        ArrayList<materiales> materialess = new ArrayList();
        String sql = " select * from materiales";
        try (
                Connection con = getCon(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                int cod = rs.getInt("idMateriales");
                String nom = rs.getString("nombre");
                int cant=rs.getInt("cantidad");
                int prec=rs.getInt("precio");
                materiales m = new materiales(cod,nom,cant,prec);
                materialess.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(materiales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materialess;

    }


    @Override
    public boolean modificar() {
        String sql = "Update materiales set nombre=?, cantidad=?, precio=? where idMateriales=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.nombre);
            stm.setInt(2, this.cantidad);
            stm.setInt(3, this.precio);
            stm.setInt(4, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(materiales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
      String sql = "Delete from materiales where idMateriales=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(materiales.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
