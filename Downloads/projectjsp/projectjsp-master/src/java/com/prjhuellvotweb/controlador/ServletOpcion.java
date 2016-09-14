/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.controlador;

import com.prjhuellvotweb.DAO.DAOOpcion;
import com.prjhuellvotweb.modelo.Opcion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Estiven Mazo Moreno
 */
public class ServletOpcion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        //Preguntar por la sesion del usuario admin
        HttpSession sessionOk = request.getSession(true);
        if (sessionOk.getAttribute("admin") != null) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String nomO = request.getParameter("nomO");
                String cateO = request.getParameter("cateO");
                String descriO = request.getParameter("descriO");
                //validar campos de Opción
                System.out.println("entro");
                if (nomO.isEmpty()) {
                    out.println("debe ingresar nombre.");
                }
                if (cateO.isEmpty()) {
                    out.println("debe seleccionar categoria.");
                }
                if (descriO.isEmpty()) {
                    out.println("debe ingresar una descripción.");
                }
                if (nomO.isEmpty() && cateO.isEmpty() && descriO.isEmpty()) {
                    response.setStatus(400);
                    out.println("por favor llenar  todos los campos.");
                } else {
                    //instanciar modelo Opción
                    Opcion op = new Opcion();
                    //catutar datos de la vista
                    op.setNombreO(nomO);
                    op.setDescripcionO(descriO);
                    op.setIdc(Integer.parseInt(cateO));
                    //instanciar DAO de Opcion
                    DAOOpcion dao = new DAOOpcion();

                    //error al intentar registrar Opcion.
                    if (dao.RegistarO(op) == false) {
                        response.setStatus(500);
                        out.println("No se puede Registrar la opción.");
                    }//registrar Opción
                    else {
                        out.println("Opción regitrada.");
                    }
                }
            }
        }else{
            sessionOk.invalidate();
            response.sendRedirect("index.jsp");
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
