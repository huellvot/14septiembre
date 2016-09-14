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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pc
 */
public class ServletTabOpcion extends HttpServlet {

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

                DAOOpcion daoO = new DAOOpcion();
                ArrayList<Opcion> listO = new ArrayList();
                listO = daoO.consultarAllOpcion();
                if (listO != null) {
                    out.println("<table class=\"tblOpcion\">"
                            + "<thead><tr>"
                            + "<th data-field=\"id\">Codigo</th>"
                            + "<th data-field=\"op\">Opción</th>"
                            + "<th data-field=\"des\">Descripción</th>"
                            + "<th data-field=\"editar\">Editar</th>"
                            + "<th data-field=\"eliminar\">Eliminar</th>"
                            + "</tr></thead><tbody>");

                    for (int i = 0; i < listO.size(); i++) {
                        Opcion o = new Opcion();
                        o = (listO.get(i));
                        int id = o.getIdOpcion();
                        String Op = o.getNombreO();
                        String des = o.getDescripcionO();
                        out.print("<tr>"
                                + "<td>" + id + "</td>"
                                + "<td>" + Op + "</td>"
                                + "<td>" + des + "</td>"
                                + "<td><button type=\"button\" id=\"btnModificarOp\" name=\"mdO\" value=\"" + id + "\" class=\"waves-effect waves-light btn material-icons right acOpc \" onmouseover=\"abrirModalO()\" style='background-color:#fc7323;'><i class=\"material-icons center\">mode_edit</i></button>\n"
                                + "<td> <button type=\"button\" id=\"btnEliminarOp\"  name=\"btnO\" value=\"" + id + "\" class=\"waves-effect waves-light btn teal darken-2 eliminarO\"onmouseover=\"eliminarO()\"><i class=\"material-icons center\">delete</i></button></td>"
                                + "</tr>");
                    }
                    out.println("</tbody> </table>");
                } else {
                    response.setStatus(500);
                    out.println("No se encontraron datos para mostrar.");
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
