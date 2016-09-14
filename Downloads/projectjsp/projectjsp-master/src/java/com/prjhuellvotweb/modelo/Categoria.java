/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.modelo;

/**
 *
 * @author Juan Estiven Mazo Moreno
 */
public class Categoria {
    private int idCategoria;
    private String nombreC;

    public Categoria(String nombreC) {
        this.nombreC = nombreC;
    }

    public Categoria() {
    }
    

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }
    
    
}
