/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.DAO;

import com.prjhuellvotweb.Util.Conexion;
import com.prjhuellvotweb.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Familia Mazo
 */
public class DAOUsuario extends Conexion {

    Usuario usuario = new Usuario();
    PreparedStatement pst;
    ResultSet rs;
    String sql;
    ArrayList list = new ArrayList();

    public boolean registrarUsuario(Usuario usu) {
        boolean validar = false;
        Connection con = Conexion.conectar("mysql");
        try {

            sql = "INSERT INTO USUARIO(nombre,documento,correo,sexo)VALUES(?,?,?,?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, usu.getNombre());
            pst.setString(2, usu.getNumerodocumento());
            pst.setString(3, usu.getCorreo());
            pst.setString(4, usu.getSexo());

            if (pst.executeUpdate() == 1) {
                System.out.println("Usuario  registrado.");
                validar = true;
            }

        } catch (Exception e) {
            System.out.println("Error Mysql al intentar registrar un usuario: " + e);
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ef) {
                System.out.println("error en el finally usuario:" + ef);
            }
            return validar;
        }

    }

    public boolean modificarusuario(Usuario usu) {
        boolean validar = false;
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "UPDATE usuario SET nombre=?,correo=?,documento=? where idU=?";
            pst = con.prepareStatement(sql);
            pst.setString(3, usu.getNumerodocumento());
            pst.setString(1, usu.getNombre());
            pst.setString(2, usu.getCorreo());
            pst.setInt(4, usu.getId());

            if (pst.executeUpdate() == 1) {
                System.out.println("Usuario Modificado.");
                validar = true;
            }
            pst.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error Mysql al intentar modificar un usuario: " + e);

        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ef) {
                System.out.println("error en el finally usuario:" + ef);
            }
            return validar;
        }
    }

    public boolean eliminarUsuario(Usuario usu) {
        boolean validar = false;
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "DELETE FROM Usuario WHERE documento=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usu.getNumerodocumento());
            if (pst.executeUpdate() == 1) {
                System.out.println("Usuario elimando.");
                validar = true;
            }

        } catch (Exception e) {
            System.out.println("Error Mysql  al intentar eliminar usuario:" + e);
        } finally {
            try {
                pst.close();
                con.close();
            } catch (Exception ef) {
                System.out.println("error en el finally usuario:" + ef);
            } finally {
                return validar;
            }

        }
    }

    public Usuario autenticarUsuario(String numeroDocumento) {
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "SELECT * FROM usuario WHERE documento=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, numeroDocumento);
            rs = pst.executeQuery();     
            while (rs.next()) {
                llenar();
                return usuario;
            }
            pst.close();
            rs.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error Mysql al intentar autenticar un usuario: " + e.getMessage());
        }
        return null;
    }

    public void llenar() {
        try {
            usuario = new Usuario();
            usuario.setId(rs.getInt("idU"));
            usuario.setNumerodocumento(rs.getString("documento"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setSexo(rs.getString("sexo"));
            usuario.setCorreo(rs.getString("correo"));
            usuario.setFecha(rs.getDate("fechaRegistro"));

        } catch (Exception e) {
            System.out.println("Error llenar lista de usuario: " + e);
        }

    }

    public ArrayList<Usuario> consultarAllUsuarios() {
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "SELECT * FROM USUARIO";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                llenar();
                list.add(usuario);
            }
            pst.close();
            rs.close();
            con.close();
            return list;

        } catch (Exception e) {
            System.out.println("Error Mysql al intentar consultar todos los usuarios: " + e);

        }
        return null;
    }

    public ArrayList<Usuario> consultarUsuario(Usuario usu) {
        Connection con = Conexion.conectar("mysql");
        try {
            sql = "SELECT * FROM USUARIO WHERE tipodocumento LIKE %?% || numerodocumeto LIKE %?% ||"
                    + "nombre LIKE %?% || apellido LIKE %?% || genero LIKE %?% || fechanacimiento LIKE %?%";
            pst = con.prepareStatement(sql);
            pst.setString(2, usu.getNumerodocumento());
            pst.setString(3, usu.getNombre());
            pst.setString(7, usu.getNumerodocumento());
            rs = pst.executeQuery();
            while (rs.next()) {
                llenar();
            }
            pst.close();
            rs.close();
            con.close();
            return list;

        } catch (Exception e) {
            System.out.println("Error en Mysql al intentar buscar usuario: " + e);

        }
        return null;
    }

    public int validarCantidadUsuariosRegistrados() {
        Connection con = Conexion.conectar("mysql");
        int cantidad = 0;
        try {
            sql = "select count(*) from usuario";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                cantidad = Integer.parseInt(rs.getString(1));
                return cantidad;
            }
//            Usuario u = new Usuario();
//            u.setId(cantidad);
//            System.out.println("valor de la consulta" + u.getId());
        } catch (Exception e) {
            System.out.println("error validar la Cantidad de Usuario sRegistrados" + e);
        } finally {
            try {
                pst.close();
                con.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("error  en el finally validar la Cantidad de Usuario sRegistrados" + e);
            } finally {
                return cantidad;
            }
        }

    }
}
