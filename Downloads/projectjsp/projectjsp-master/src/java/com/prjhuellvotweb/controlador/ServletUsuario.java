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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Estiven Mazo
 */
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Preguntar por la sesion del usuario admin
        HttpSession sessionOk = request.getSession(true);
        if (sessionOk.getAttribute("admin") != null) {
            //mostrar fecha
            System.out.println(new Date());
            DateFormat formato = DateFormat.getDateInstance(DateFormat.LONG);
            System.out.println(formato.format(new Date()));
            
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String documento = request.getParameter("documento");
                String nombre = request.getParameter("nombre");
                String correo = request.getParameter("correo");
                String sexo = request.getParameter("sexo");

                if (documento.isEmpty()) {
                    out.println("El número de documento es requerido.");
                } else if (nombre.isEmpty()) {
                    out.println("Ingrese nombre.");
                } else if (correo.isEmpty()) {
                    out.println("Ingrese correo.");
                } else if (sexo.isEmpty()) {
                    out.println("Elija sexo.");
                } else {
                    Usuario usu = new Usuario();
                    usu.setNumerodocumento(documento);
                    usu.setNombre(nombre);
                    usu.setCorreo(correo);
                    usu.setSexo(sexo);
                    DAOUsuario dao = new DAOUsuario();
                    //validar cuantos usuarios estan registrados en la bd
//                    if (dao.validarCantidadUsuariosRegistrados() > 10) {
//                        response.setStatus(400);
//                        out.println("¡Disculpa! "
//                                + "Pero has excedido el límite de registro de usuarios,"
//                                + "para seguir utilizando nuestros servicios contáctanos"
//                                + " en Medellin oficina HuellVot© tel:222-2222.");
//                        out.println("Desarrolladores: Eliana Marquez,Estiven Mazo,Sergio Buitrago.");
//                        out.println(formato.format(new Date()));
//                        //validar que el usuario no exista
//                    } else 
                        if (dao.autenticarUsuario(documento) != null) {
                        String valordocu = dao.autenticarUsuario(documento).getNumerodocumento();
                        response.setStatus(400);
                        out.print("Usuario ya existe.");
                        System.out.println("Usuario con numero de documento: " + valordocu + " ya existe");
                    } else if (dao.registrarUsuario(usu) == true) {
                        out.print("Usuario registrado correctamente.");
                        System.out.println("Usuario registrado correctamente.");
                    } else {
                        response.setStatus(500);
                        out.print("¡Disculpa! ha ocurrido un error en el servidor.");
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
