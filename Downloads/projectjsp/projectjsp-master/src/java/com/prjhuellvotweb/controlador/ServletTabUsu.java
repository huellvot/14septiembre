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
import java.sql.Date;
import java.text.DateFormat;
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
public class ServletTabUsu extends HttpServlet {

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
        HttpSession sessionOk = request.getSession();
        if (sessionOk.getAttribute("admin") != null) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                DAOUsuario daoU = new DAOUsuario();
                ArrayList<Usuario> listU = new ArrayList();
                listU = daoU.consultarAllUsuarios();
                if (listU != null) {

                    out.println("<table class=\"tblUsuario\">"
                            + "<thead><tr>"
//                            + "<th data-field=\"id\">ID</th>"
                            + "<th data-field=\"nombre\">Nombre</th>"
                            + "<th data-field=\"documento\">Documento</th>"
                            + "<th data-field=\"sexo\">Sexo</th>"
                            + "<th data-field=\"fecha\">Fecha Registro</th>"
                            + "<th data-field=\"correo\">Correo</th>"
                            + "<th data-field=\"editar\">Editar</th>"
                            + "<th data-field=\"eliminar\">Eliminar</th>"
                            + "</tr></thead><tbody>");
                    for (int i = 0; i < listU.size(); i++) {
                        Usuario u = new Usuario();
                        u = (listU.get(i));
                        int id = u.getId();
                        String nom = u.getNombre();
                        String cor = u.getCorreo();
                        String sex = u.getSexo();
                        String doc = u.getNumerodocumento();
                        DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG);
                        Date fec = u.getFecha();
                        out.println("<tr>");
//                      out.println("<td>" + id + "</td>"
                        out.println("<td>" + nom + "</td>"
                                + "<td>" + doc + "</td>"
                                + "<td>" + sex + "</td>"
                                + "<td>" + formato.format(fec) + "</td>"
                                + "<td>" + cor + "</td>"
                                + "<td><button type=\"button\" id=\"btnModificarUs\" name=\"mdU\" value=\"" + doc + "\" class=\"waves-effect waves-light btn material-icons right acUsu \" onmouseover=\"abrirModalU()\" style='background-color:#fc7323;'><i class=\"material-icons center\">mode_edit</i></button>\n"
                                + "<td> <button type=\"button\" id=\"btnEliminarUsu\"  name=\"btnUsu\" value=\"" + doc + "\" class=\"waves-effect waves-light btn teal darken-2 eliminarU\"onmouseover=\"eliminarU()\"><i class=\"material-icons center\">delete</i></button></td>"
                                + "</tr>");
                    }
                    out.println("</tbody> </table>");
//              out.println("<script src=\"js/Validaciones.js\" type=\"text/javascript\"></script>\n");
                } else {
                    response.setStatus(500);
                    out.println("No se encontraron datos para mostrar.");
                }
            }
        } else {
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
