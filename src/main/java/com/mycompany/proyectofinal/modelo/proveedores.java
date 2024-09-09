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
public class proveedores extends conexion implements sentencias {
    private int idProveedor;
    private String nombre;
    private String contacto;
    private String direccion;

    public proveedores() {
    }

    public proveedores(int idProveedor, String Nombre, String Contacto, String Direccion) {
        this.idProveedor = idProveedor;
        this.nombre = Nombre;
        this.contacto = Contacto;
        this.direccion = Direccion;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String Contacto) {
        this.contacto = Contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    @Override
    public boolean insertar() {
       String sql = "insert into proveedor values (?,?,?,?)";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.idProveedor);
            stm.setString(2, this.nombre);
            stm.setString(3, this.contacto);
            stm.setString(4, this.direccion);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ArrayList consulta() {
         ArrayList<proveedores> proveedores = new ArrayList();
        String sql = " select * from proveedor";
        try (
                Connection con = getCon(); Statement stm = con.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("idProveedor");
                String nom = rs.getString("nombre");
                String cont = rs.getString("contacto");
                String dir = rs.getString("direccion");
                proveedores p = new proveedores(id,nom,cont,dir);
                proveedores.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedores;

    }
    
    @Override
    public boolean modificar() {
      String sql = "Update proveedor set Nombre=?, Contacto=?,Direccion=?  "
                + "where idProveedor=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, this.nombre);
            stm.setString(2, this.contacto);
            stm.setString(3, this.direccion);
            stm.setInt(4,this.idProveedor);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    @Override
    public boolean eliminar() {
         String sql = "Delete from proveedor where idProveedor=?";
        try (
                Connection con = getCon(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, this.idProveedor);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(proveedores.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    
}
