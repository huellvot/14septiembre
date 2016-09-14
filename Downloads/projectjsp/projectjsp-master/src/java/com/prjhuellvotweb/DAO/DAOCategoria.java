/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.DAO;

import com.prjhuellvotweb.Util.Conexion;
import com.prjhuellvotweb.modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juan Estiven Mazo
 */
public class DAOCategoria{

    String sql;
    
    PreparedStatement pst;
    ResultSet rs;
    Categoria cat;

    public boolean RegistarC(Categoria c) {
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "INSERT INTO categoria (idCategoria) VALUES (?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getIdCategoria());
            if (pst.executeUpdate() == 1) {
                System.out.println("Se ha registrado una categoria");

            } else {
                System.out.println("Han ocurrido errores durante el  registro");
            }
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al registrar por favor verifique, " + ex);
        }
        return false;
    }

    public ArrayList<Categoria> consultarCategorias() {
        Connection con = Conexion.conectar("mysql");
        String sql = "SELECT * FROM categoria";
        try {
            pst = con.prepareStatement(sql);

            ArrayList<Categoria> list = new ArrayList();

            ResultSet r = pst.executeQuery(sql);
            while (r.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(r.getInt("idC"));
                c.setNombreC(r.getString("nombreC").toUpperCase());
                list.add(c);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

//select c.nombrec from opcion as o,categoria as c where  c.idc=o.idc limit 1;
