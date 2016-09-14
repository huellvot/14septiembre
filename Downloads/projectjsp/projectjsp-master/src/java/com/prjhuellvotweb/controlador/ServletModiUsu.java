/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.controlador;

import com.prjhuellvotweb.DAO.DAOUsuario;
import com.prjhuellvotweb.modelo.Usuario;
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
public class ServletModiUsu extends HttpServlet {

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
//        HttpSession sessionOk = request.getSession(true);
//        if (sessionOk.getAttribute("admin") != null) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String documento = request.getParameter("documento");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                int id = Integer.parseInt(request.getParameter("id"));
                if (documento.isEmpty()) {
                    out.println("numero de documento es requerido.");
                } else if (nombre.isEmpty()) {
                    out.println("ingrese nombre.");
                } else if (correo.isEmpty()) {
                    out.println("ingrese correo.");
                } else {
                    Usuario usu = new Usuario();
                    usu.setNumerodocumento(documento);
                    usu.setNombre(nombre);
                    usu.setCorreo(correo);
                    usu.setId(id);
                    DAOUsuario dao = new DAOUsuario();
                    //validar que el usuario no exista
                    if (dao.modificarusuario(usu)) {
                        out.print("Usuario actualizado correctamente.");
                    } else {
                        response.setStatus(500);
                        out.print("Error al actualizar Usuario.");
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
