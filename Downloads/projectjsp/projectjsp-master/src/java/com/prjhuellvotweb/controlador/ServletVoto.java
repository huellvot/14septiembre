/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prjhuellvotweb.controlador;

import com.prjhuellvotweb.DAO.DAOVoto;
import com.prjhuellvotweb.modelo.Voto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rocio Eliana Marquez Olarte
 */
public class ServletVoto extends HttpServlet {

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
        if (sessionOk.getAttribute("votante") != null) {
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                
                int idU = (int) sessionOk.getAttribute("votante");
                String idO = request.getParameter("Opcion");
                System.out.println("Id opcion" + idO);
                if (idO == null) {
                    response.setStatus(400);
                    out.println("ERROR, Usted no ha seleccionado ninguna opcion");
                    System.out.println("ERROR, Usted no ha seleccionado ninguna opcion");
                } else {
                    Voto v = new Voto(idU, idO);
                    DAOVoto daov = new DAOVoto();
                    if (daov.votarValid(v) == true) {
                        response.sendRedirect("index.jsp");
                    } else {
                        response.setStatus(400);
                        System.out.println("Ocurrio un error en el servidor.");
                    }
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
//DAOVoto daov = new DAOVoto();
//                ArrayList<Voto> listv = new ArrayList();
//                listv = daov.estadisticaNumeroVotos();
//                for (int i = 0; i < listv.size(); i++) {
//                    Voto v2 = new Voto();
//                    v2 = (listv.get(i));
//                    int totalVotos = v2.getIdUsuario();
//                    String nombreopcion = v2.getIdOpcion();
//                }
