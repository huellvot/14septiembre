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
 * @author pc
 */
public class ServletModiOpcion extends HttpServlet {

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
//        //Preguntar por la sesion del usuario admin
//        HttpSession sessionOk = request.getSession(true);
//        if (sessionOk.getAttribute("admin") != null) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                int id = Integer.parseInt(request.getParameter("idO"));
                String nombre = request.getParameter("nomO");
                String descripcion = request.getParameter("descriO");
                System.out.println("id" + id);
                System.out.println("nombre" + nombre);
                System.out.println("descri" + descripcion);
                if (nombre.isEmpty()) {
                }
                if (descripcion.isEmpty()) {
                }
                if (nombre.isEmpty() && descripcion.isEmpty()) {
                    response.setStatus(400);
                    out.println("por favor llenar los campos.");
                } else {
                    //
                    Opcion op = new Opcion();
                    op.setIdOpcion(id);
                    op.setNombreO(nombre);
                    op.setDescripcionO(descripcion);
                    //
                    DAOOpcion dao = new DAOOpcion();
                    if (dao.modificarOpcion(op)) {
                        out.println("Opción modificada.");
                    } else {
                        response.setStatus(500);
                        out.println(" Error al actualizar Opción.");
                    }
                }
            }

//        } else {
//            sessionOk.invalidate();
//            response.sendRedirect("index.jsp");
//        }
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
