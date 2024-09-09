/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fjavi
 */
public class conexion {
    private String base;
    private String host;
    private String usuario;
    private String contra;
    Connection con;

    public Connection getCon() {
        try {
            String url="jdbc:mysql://"+host+"/"+base;
            con=DriverManager.getConnection(url, usuario,contra);
            System.out.println("Conectado");
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No conectado"+ex);
        }
        return con;
    }
    

    public conexion(String base, String host, String usuario, String contra) {
        this.base = base;
        this.host = host;
        this.usuario = usuario;
        this.contra = contra;
    }

    public conexion() {
        this.base = "proyectofinal";
        this.host = "186.17.87.89:3004";
        this.usuario = "Alumnos";
        this.contra = "Info024";
    }
    
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
    
}
