package com.prjhuellvotweb.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prjhuellvotweb.DAO.DAOVoto;
import com.prjhuellvotweb.Util.Conexion;
import com.prjhuellvotweb.modelo.Voto;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Estiven Mazo Moreno
 */
@WebServlet(urlPatterns = {"/Reporte"})
public class PDF extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Preguntar por la sesion del usuario admin
        HttpSession sessionOk = request.getSession(true);
        if (sessionOk.getAttribute("admin") != null) {
            //cambiar a tipo application/pdf
            response.setContentType("application/pdf;charset=UTF-8");
            //flujo de salida
            OutputStream out = response.getOutputStream();
            String texto = request.getParameter("report");
            //texto = "Reporte de los proyectos Sena CTGI (Centro tecnologico de gestion industrial) donde se dan a conocer"
             //                   + " los nombres de los proyectos y cantidad de votos obtenidos para cada proyecto.";
            try {

                Connection con = Conexion.conectar("mysql");
                DAOVoto dao = new DAOVoto();
                List<List> lista =dao.estadisticaNumeroVotos();
                Voto t = dao.contarVotos();
                int to = t.getIdUsuario();
                if (!lista.isEmpty()) {
                    try {
                        //programar pdf
                        Document documento = new Document();                        
                        //asosciar documento con la salida
                        PdfWriter.getInstance(documento, out);// salida del cocumento en pdf
                        //abrir documento
                        documento.open();
                        Paragraph par2 = new Paragraph();
                        Paragraph par3 = new Paragraph();
                        Paragraph par4 = new Paragraph();
                        //agregar una imagen logo sena al pdf
                        Image imagenes = Image.getInstance("C:\\Users\\Sena\\Downloads\\projectjsp\\projectjsp-master\\web\\Multimedia\\reportes.png");
                        //Centrar la imagen
                        imagenes.setAlignment(Element.ALIGN_CENTER);
                        //tamaño de la imagen
                        imagenes.scaleToFit(530, 520);
                        //agg imagen al documento F:\\Documentos\\yo\\huellvot 2 17-06-2016\\PrjHuellVotWeb\\web\\iCO.png
                        //documento.add(imghuellvot);
                        documento.add(imagenes);
                        //Agg salto de linea
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));

                        //fuente del pdf, tipo de fuente famimilia tamaño de letra
                        //Importar ttf que contiene el tipo de letra
                        FontFactory.register("C:\\Users\\Sena\\Downloads\\projectjsp\\projectjsp-master\\web\\fonts\\roboto\\Roboto-Bold.ttf", "Roboto");
                        //Font fondescripcion = FontFactory.getFont("Roboto");
                        Font fondescripcion = new Font(Font.getFamily("Roboto"), 16, Font.NORMAL, BaseColor.BLACK);
                        //texto de la descripcion
                        par2.add(new Phrase(texto, fondescripcion));
                        //justificar descripcion
                        par2.setAlignment(Element.ALIGN_JUSTIFIED);
                        //Agg salto de linea
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        //agregar descripcion al documento
                        documento.add(par2);//agregar todas las propiedades de la descripción
                        //crear una tabla
                        PdfPTable tabla = new PdfPTable(5);//( Numero de columnas de la tabla)
                        //columnas de la tabla, cabezera y agg un estilo
                        PdfPCell celda = new PdfPCell(new Paragraph("Número", FontFactory.getFont("Roboto", 14, Font.BOLD)));
                        PdfPCell celda1 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("Roboto", 14, Font.BOLD)));
                        celda1.setColspan(3);
                        PdfPCell celda2 = new PdfPCell(new Paragraph("Votos", FontFactory.getFont("Roboto", 14, Font.BOLD)));
                        //Color de fondo
                        celda.setBackgroundColor(new BaseColor(252, 115, 35));
                        celda1.setBackgroundColor(new BaseColor(252, 115, 35));
                        celda2.setBackgroundColor(new BaseColor(252, 115, 35));
                        //Centrar
                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celda1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        celda2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //padding
                        celda.setPadding(8.0f);
                        celda1.setPadding(8.0f);
                        celda2.setPadding(8.0f);
                        //agg columna ala tabla
                        tabla.addCell(celda);
                        tabla.addCell(celda1);
                        tabla.addCell(celda2);

                        for (int i = 0; i < lista.size(); i++) {
                            List l = lista.get(i);
                            //Convertir el entero a string
                            String num = String.valueOf(l.get(0));
                            String nom = String.valueOf(l.get(1));
                            String tot = String.valueOf(l.get(2));
                            //Agregar valores a las celdas
                            PdfPCell c = new PdfPCell(new Paragraph(num, FontFactory.getFont("Roboto", 12, Font.BOLD)));
                            PdfPCell c1 = new PdfPCell(new Paragraph(nom, FontFactory.getFont("Roboto", 12, Font.BOLD)));
                            c1.setColspan(3);
                            PdfPCell c2 = new PdfPCell(new Paragraph(tot, FontFactory.getFont("Roboto", 12, Font.BOLD)));
                            //Padding para las celdas
                            c.setPadding(4.0f);
                            c1.setPadding(4.0f);
                            c2.setPadding(4.0f);
                            //Centrar contenido de celda
                            c.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                            //mostrar los resultados de cada columna los agrega a la tabla
                            tabla.addCell(c);
                            tabla.addCell(c1);
                            tabla.addCell(c2);

                        }
                        PdfPCell c1 = new PdfPCell(new Paragraph("Total Votos: ", fondescripcion));
                        c1.setColspan(4);
                        PdfPCell c2 = new PdfPCell(new Paragraph(""+to, fondescripcion));
                        
                        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        
                        c1.setPadding(4.0f);
                        c2.setPadding(4.0f);
                        
                        tabla.addCell(c1);
                        tabla.addCell(c2);
                        
                        //Agrega la tabla a el documento
                        documento.add(tabla);
                        Font fonfecha = new Font(Font.getFamily("Roboto"), 12, Font.NORMAL, BaseColor.LIGHT_GRAY);
                                               
                        par4.add(new Phrase("Expedido por HuellVot", fonfecha));
                        DateFormat formato = DateFormat.getDateInstance(DateFormat.FULL);
                        par4.add(new Paragraph(formato.format(new Date())));

                        documento.add(par4);

//cerrar el documento
                        documento.close();
                    } catch (DocumentException | IOException e) {
                        e.getMessage();
                        System.out.println("Error al generar el reporte PDF" + e);
                    }

                }

            } finally {
                out.close();
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
