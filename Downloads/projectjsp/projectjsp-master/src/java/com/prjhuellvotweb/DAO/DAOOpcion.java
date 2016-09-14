/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.DAO;

import com.prjhuellvotweb.Util.Conexion;
import com.prjhuellvotweb.modelo.Categoria;
import com.prjhuellvotweb.modelo.Opcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin_Sena
 */
public class DAOOpcion {

    String sql;
    Connection con = Conexion.conectar("mysql");
    PreparedStatement pst;
    ResultSet rs;
    Opcion o;
    ArrayList list = new ArrayList();

    public boolean RegistarO(Opcion o) {
        try {
            sql = "INSERT INTO opcion (nombreO,descripcion,idC) VALUES (?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, o.getNombreO());
            pst.setString(2, o.getDescripcionO());
            pst.setInt(3, o.getIdc());
            if (pst.executeUpdate() == 1) {
                System.out.println("Se ha registrado una opcion.");

            } else {
                System.out.println("Han ocurrido errores durante el  registro de una opcion.");
            }
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al registrar  la opcion por favor verifique, " + ex);
        }
        return false;
    }

    public ArrayList<Opcion> consultarOpcionXCategoria(int cat) {
        try {
            sql = "SELECT * FROM opcion WHERE idC="+cat;
            pst = con.prepareStatement(sql);
          //  pst.setInt(1, cat);
            ArrayList list = new ArrayList();

            ResultSet rt = pst.executeQuery(sql);
            while (rt.next()) {
                Opcion o = new Opcion();
                o.setIdOpcion(rt.getInt("idO"));
                o.setNombreO(rt.getString("nombreO").toUpperCase());
                list.add(o);
               // list.add(rt.getString("descripcion").toUpperCase());
                
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error al consultar opcion x categoria:"+e);
        }
        return null;
    }

    public ArrayList<Opcion> consultarAllOpcion() {
        try {
            sql = "SELECT * FROM opcion";
            pst = con.prepareStatement(sql);
            ArrayList list = new ArrayList();
            ResultSet r = pst.executeQuery(sql);
            while (r.next()) {
                o = new Opcion();
                o.setIdOpcion(r.getInt("idO"));
                o.setNombreO(r.getString("nombreO"));
                o.setDescripcionO(r.getString("descripcion"));
                list.add(o);
            }
            return list;
        } catch (Exception e) {
            System.out.println("Error al consultar todas las opciones:"+e);
        }
        return null;
    }

    public boolean modificarOpcion(Opcion o) {
        boolean validar = false;
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "update opcion set nombreo=?, descripcion=? where idO=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, o.getNombreO());
            pst.setString(2, o.getDescripcionO());
            pst.setInt(3, o.getIdOpcion());
            if (pst.executeUpdate() == 1) {
                validar = true;
                System.out.println("Opcion Modificada.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar modificar una opción:" + e);
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ex) {
                System.out.println("Error en el finally opción:" + ex);
            } finally {
                return validar;
            }
        }
    }

    public Opcion consultarOpcionxId(int idOpcion) {
        // Connection con = Conexion.conectar("mysql");
        try {
            sql = "SELECT * FROM opcion WHERE idO=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idOpcion);
            rs = pst.executeQuery();
            while (rs.next()) {
                Opcion o = new Opcion();
                o.setIdOpcion(rs.getInt("idO"));
                o.setNombreO(rs.getString("nombreO"));
                o.setDescripcionO(rs.getString("descripcion"));
                o.setIdc(rs.getInt("IdC"));
                return o;
            }
            pst.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error al consultar una opcion por le Id de opción " + e);
        }
        return null;
    }

    public Opcion mostrarNomCat(int idcat) {
        try {
            sql = "select c.nombrec  from opcion as o,categoria as c where o.idc=? and c.idc=o.idc";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idcat);
            rs = pst.executeQuery();
            while (rs.next()) {
                Opcion o = new Opcion();
                o.setNombreO(rs.getString("nombreC"));
                return o;
            }
            pst.close();
            con.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Erro al intentar consultar nombre de categoria:" + e);
        }
        return null;
    }

    public boolean eliminarOpcion(Opcion o) {
        boolean validar = false;
        try {
            sql = "DELETE FROM opcion where idO=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, o.getIdOpcion());
            if (pst.executeUpdate() == 1) {
                validar = true;
                System.out.println("Opción Eliminada.");
                return validar;
            } else {
                System.out.println(" no se pudo eliminar la Opción.");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar eliminar Opción" + e);
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception e) {
                System.out.println("Error en el Finally eliminar Opción" + e);
            } finally {
                return false;
            }
        }

    }
}
