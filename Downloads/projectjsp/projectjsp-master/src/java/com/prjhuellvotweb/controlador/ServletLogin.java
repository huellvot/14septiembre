/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.controlador;

import com.prjhuellvotweb.DAO.DAOUsuario;
import com.prjhuellvotweb.DAO.DAOVoto;
import com.prjhuellvotweb.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SENA
 */
public class ServletLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String usuario = request.getParameter("usuario").trim();
            String documento = request.getParameter("documento").trim();
            HttpSession sessionOk = request.getSession(true);
            
            int id;
            int sesionAdmin = 0;
            //validar  que los campos del formulario login  no esten vacios
            if (usuario.isEmpty() && documento.isEmpty()) {
            }//ingreso de Admin 
            else if (usuario.equals("root") && documento.equals("qwerty")) {
                sesionAdmin = 1;
                sessionOk.setAttribute("admin", sesionAdmin);
                out.print("admin");
                //validar usuariorio y clave
            } else if (!usuario.equals(documento)) {
                response.setStatus(400);
                out.println("Usuario o clave incorrectos.");
            } else {
                Usuario u = new Usuario();
                u.setNumerodocumento(documento);
                DAOUsuario dao = new DAOUsuario();
                //validar que el usuario exista en la Base de datos
                if (dao.autenticarUsuario(documento) != null) {
                    id = dao.autenticarUsuario(documento).getId();
                    DAOVoto v = new DAOVoto();
                    //validar que el usuario no ha realizo la votacion                    
                    if (v.valid(id)) {
                        response.setStatus(400);
                        out.print("Este usuario ya genero la votacion.");
                        System.out.println("Usuario con id:" + id + " ya voto.");
                    } else {
                        //manejo de session de usuario
                        sessionOk.setMaxInactiveInterval(60);
                        sessionOk.setAttribute("votante", id);                        
                        out.print("votacion");
                        System.out.println("Usuario con id:" + id + " puede votar.");
                    }
                    
                } else {                    
                    //error si el Mysql NO esta iniciado.
                    response.setStatus(500);
                    out.println("Error en el servidor. Usuario no encontrado.");
                }
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
