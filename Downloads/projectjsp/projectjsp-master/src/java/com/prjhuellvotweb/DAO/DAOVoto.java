/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.DAO;

import com.prjhuellvotweb.Util.Conexion;
import com.prjhuellvotweb.modelo.Usuario;
import com.prjhuellvotweb.modelo.Voto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eliana Marquez Olarte
 */
public class DAOVoto {

    String sql;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList list = new ArrayList();

    public boolean votarValid(Voto v) {
        try {
            Conectar();
            sql = "CALL INSERTVOTO (?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, v.getIdUsuario());
            pst.setString(2, v.getIdOpcion());
            if (pst.executeUpdate() == 1) {
                System.out.println("Usted ha votado con exito !");

            } else {
                System.out.println("Han ocurrido errores durante el voto");
            }
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al votar por favor verifique, " + ex);
        }
        return false;
    }
    
    //Votar sin procedimiento que valida registro en la base de datos
    public boolean votar(Voto v) {
        try {
            Conectar();
            sql = "INSERT INTO voto (idU, idO) VALUES (?,?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, v.getIdUsuario());
            pst.setString(2, v.getIdOpcion());
            if (pst.executeUpdate() == 1) {
                System.out.println("Usted ha votado con exito !");

            } else {
                System.out.println("Han ocurrido errores durante el voto");
            }
            pst.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al votar por favor verifique, " + ex);
        }
        return false;
    }

    public int contar(int op) {
        Conectar();
        sql = "SELECT COUNT (*) FROM votos WHERE idOpcion = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, op);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error contando los votos, " + ex);
        }

        return 0;
    }

    public boolean valid(int u) {
        Conectar();
        sql = "SELECT * FROM voto WHERE idU = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, u);
            rs = pst.executeQuery();
            while (rs.next()) {
                return true;
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Este usuario ya voto, " + ex);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public ArrayList<List> estadisticaNumeroVotos() {
        Conectar();
        try {
            sql = "select O.idO, O.nombreO, count(*) as Total_votos from voto as v, opcion as o where o.ido=v.ido group by o.nombreo order by Total_Votos desc";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                List l = new ArrayList();
                l.add(rs.getInt(1));
                l.add(rs.getString(2));
                l.add(rs.getInt(3));
                list.add(l);
            }
            pst.close();
            rs.close();
            con.close();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al intentar hacer una consulta" + e);
        }
        return null;
    }
    
    //Contar votos
    
    public Voto contarVotos() {
        Conectar();
        try {
            Voto v = new Voto();
            sql = "select count(*)from voto";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {                
                v.setIdUsuario(rs.getInt(1));                
            }
            pst.close();
            rs.close();
            con.close();
            return v;
        } catch (SQLException e) {
            System.out.println("Error al intentar hacer una consulta" + e);
        }
        return null;
    }
    
    //Consultar estadisticas de genero
    
    public List<Integer> EstadisticasGenero() {
        Connection conG = Conexion.conectar("mysql");
        List<Integer> listG = new ArrayList();        
        try {
            sql = "select count(*) as votos from usuario as u, voto as v where u.idU= v.idU group by sexo";
            pst = conG.prepareStatement(sql);
            rs = pst.executeQuery();            
            while (rs.next()) {                
                int v=rs.getInt("votos");
                listG.add(v); 
                System.out.println(v);
            }
            return listG;
        }catch(SQLException sq){
            System.out.println("Error estadisticas genero SQL" + sq);
        } catch (Exception e) {
            System.out.println("Error estadisticas genero" + e);
        }
        finally {
            try {
                pst.close();
                conG.close();
                rs.close();
            } catch (Exception e) {
                System.out.println("Error Finally estadisticas genero" + e);
            } finally {
               return listG;
            }
        }

    }

    private void Conectar() {
        con = Conexion.conectar("mysql");
    }

}
