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

/**
 *
 * @author pc
 */
public class ServletModUsu extends HttpServlet {

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
        //Preguntar por la sesion del usuario admin
//        HttpSession sessionOk = request.getSession(true);
//        if (sessionOk.getAttribute("admin") != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String id = request.getParameter("id");
                System.out.println(id);
                Usuario usuario = new Usuario();
                DAOUsuario dao = new DAOUsuario();
                if (dao.autenticarUsuario(id) != null) {
                    usuario = dao.autenticarUsuario(id);
                    out.println("<input id='id1' class='hide' value='"+usuario.getId()+"'>"
                            + "<div class=\"input-field col s12 m6\">\n"
                            + "<input id=\"nomU1\" type=\"text\" class=\"validate\" name=\"nombre\" required=\"\" value=\"" + usuario.getNombre() + "\" onkeypress=\"sololetras()\" onpaste=\" return false\">\n"
                            + "<label id=\"lblnm1\" for=\"txtdesc\"></label>\n"
                            + "<div id=\"vldrNombre1\" style=\"color:red;\"> </div>\n"
                            + "</div>\n"
                            + "<div class=\"input-field col s12 m6\">\n"
                            + "<input id=\"docu1\" type=\"text\" class=\"validate\" name=\"documento\" required=\"\" value=\"" + usuario.getNumerodocumento() + "\" maxlength=\"11\" onkeypress=\"solonum()\" >\n"
                            + "<label id=\"lbldocu1\" for=\"txtdesc\" ></label>\n"
                            + "<div id=\"vldrDocumento1\" style=\"color:red;\"> </div>\n"
                            + "</div>\n"
                            + "<div class=\"input-field col s12 m12\">\n"
                            + "<input type=\"email\" id=\"correo1\" class=\"validate\" name=\"correo\" required=\"\" onkeyup=\"intromodalU(event)\" value=\"" + usuario.getCorreo() + "\">\n"
                            + "<label id=\"lblcorreo1\" for=\"textarea1\"></label>\n"
                            + "<div  id=\"vldrCorreo1\" style=\"color:red;\"> </div>\n"
                            + "</div>");
                }

            }
//        } else {
//            response.setStatus(500);
//            out.println("La sesion termino");
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
