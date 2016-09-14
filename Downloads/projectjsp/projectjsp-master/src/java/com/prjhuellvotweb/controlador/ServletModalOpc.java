/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.controlador;

import com.prjhuellvotweb.DAO.DAOCategoria;
import com.prjhuellvotweb.DAO.DAOOpcion;
import com.prjhuellvotweb.modelo.Categoria;
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
public class ServletModalOpc extends HttpServlet {

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
                int id = Integer.parseInt(request.getParameter("id"));
                Opcion opcion = new Opcion();
                Opcion opcion2 = new Opcion();
                DAOOpcion dao = new DAOOpcion();
                if (dao.consultarOpcionxId(id) != null) {
                    opcion = dao.consultarOpcionxId(id);
                    if (dao.mostrarNomCat(dao.consultarOpcionxId(id).getIdc()) != null) {
                        opcion2 = dao.mostrarNomCat(dao.consultarOpcionxId(id).getIdc());
                      out.println("<div class=\"input-field col s12 m6\">\n"
                                + "<input  id=\"idO1\" type=\"text\" class=\"validate hide\" name=\"nombrep\" required=\"\" value=\"" + opcion.getIdOpcion() + "\">\n"
                                + "<input  id=\"nomO1\" type=\"text\" class=\"validate\" name=\"nombrep\" required=\"\" value=\"" + opcion.getNombreO() + "\" length=\"120\">\n"
                                + "<div id=\"vldrNombreO1\" style=\"color:#f57c00;;\"></div></div>\n"
                                + "<div class=\"input-field col s12 m6\">\n"
                                + "<input  id=\"catO1\" type=\"text\" class=\"validate\" name=\"nombrep\" required=\"\" value=\"" + opcion2.getNombreO() + "\" length=\"120\">\n"
                                + "<div id=\"vldrNombreO1\" style=\"color:#f57c00;;\"> </div>\n"
                                + "</div>"
                                + "<div class=\"input-field col s12 m12\">\n"
                                + "<textarea id=\"descriO1\" class=\"materialize-textarea\" name=\"descripcionp\"required=\"\"\">" + opcion.getDescripcionO() + "</textarea>\n"
                                + "<div id=\"vldrdescO1\" style=\"color:#f57c00;\"> </div>\n"
                                + "</div>");
                    }
                } else {
                    response.setStatus(500);
                    out.println("Ocurrio un error  en el servidor.");
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
//            DAOCategoria daoc = new DAOCategoria();
//            ArrayList<Categoria> listc = new ArrayList();
//            listc = daoc.consultarCategorias();
//            for (int i = 0; i < listc.size(); i++) {
//                Categoria c = new Categoria();
//                c = listc.get(i);
//                int m = c.getIdCategoria();
//                String nom = c.getNombreC();
//                out.println("<option value=\"" + m + "\">" + nom + "</option>");
//            }
//            out.println(" </select>\n"
//                    + "<label id=\"lblcatO\">Categoria</label>\n"
//                    + "<div id=\"vldrcatO\" style=\"color:#f57c00;;\"> </div>\n"
//                    + "</div>\n"
//                    + "<div class=\"input-field col s12 m12\">\n"
//                    + "<textarea id=\"descriO\" class=\"materialize-textarea\" name=\"descripcionp\"required=\"\"></textarea>\n"
//                    + "<label id=\"lbldesO\" for=\"textarea1\">Descripción Proyecto</label>\n"
//                    + "<div id=\"vldrdescO\" style=\"color:#f57c00;\"> </div>\n"
//                    + "</div>");
