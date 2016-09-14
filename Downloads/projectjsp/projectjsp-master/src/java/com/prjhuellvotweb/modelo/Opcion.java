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
public class Opcion {

    private int idOpcion;
    private String nombreO;
    private String descripcionO;
    private int idc;

    public Opcion() {
    }

    public Opcion(int idOpcion, String nombreO, String descripcionO, int idc) {
        this.idOpcion = idOpcion;
        this.nombreO = nombreO;
        this.descripcionO = descripcionO;
        this.idc = idc;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getNombreO() {
        return nombreO;
    }

    public void setNombreO(String nombreO) {
        this.nombreO = nombreO;
    }

    public String getDescripcionO() {
        return descripcionO;
    }

    public void setDescripcionO(String descripcionO) {
        this.descripcionO = descripcionO;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    

}
