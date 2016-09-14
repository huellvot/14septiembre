/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.modelo;

/**
 *
 * @author Eliana Marquez Olarte
 */
public class Voto {
    private int idUsuario;
    private String idOpcion;
    
    public Voto() {
    }

    public Voto(int idUsuario, String idOpcion) {
        this.idUsuario = idUsuario;
        this.idOpcion = idOpcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

   
    
    
}
