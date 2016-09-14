/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.modelo;

import java.sql.Date;

/**
 *
 * @author Juan Estiven Mazo
 */
public class Usuario {
    private int id;
    private String numerodocumento;
    private String nombre;
    private String correo;
    private Date fecha;
    private String sexo;

    public Usuario() {
    }

    public Usuario(String tipodocumento, String numerodocumento, String nombre, String apellido, String genero, String fechanacimiento, String ficha, String jornada, String correo) {

        this.numerodocumento = numerodocumento;
        this.nombre = nombre;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    

}