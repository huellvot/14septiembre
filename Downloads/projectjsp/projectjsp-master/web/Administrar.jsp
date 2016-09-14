<%-- 
    Document   : newjsp
    Created on : 13/06/2016, 09:41:57 PM
    Author     : Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>

<%@page import="java.util.List"%>
<%@page import="com.prjhuellvotweb.DAO.DAOVoto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.prjhuellvotweb.modelo.Voto"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <!-- icono -->
        <link rel="shortcut icon" type="image/x-icon" href="Multimedia/iC0.png" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            // Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/huellvot", "root", "");
            //Statement st = con.createStatement();
            //String sql = "select O.nombreO, count(*) as Total_votos from voto as v, opcion as o where o.ido=v.ido group by o.nombreo order by Total_Votos desc";
            // ResultSet rs = st.executeQuery(sql);

            try {
                DefaultCategoryDataset data = new DefaultCategoryDataset();
                DAOVoto dao = new DAOVoto();
                List<List> list = new ArrayList();
                list = dao.estadisticaNumeroVotos();
                for (int i = 0; i < list.size(); i++) {
                    List l= list.get(i);
                        String num = String.valueOf(l.get(0));
                        String nom = String.valueOf(l.get(1));
                        int tot=Integer.valueOf(l.get(2).toString());
                    //int totalVotos = v2.getIdUsuario();
                    //String nombreopcion = v2.getIdOpcion();
                    //System.out.println("nombre:" + nombreopcion + "total votos:" + totalVotos);
                    data.setValue(tot, num+". "+nom.toUpperCase()+"",num + "=" + tot);
                    //data.setValue(totalVotos, nombreopcion, nombreopcion + "= " + totalVotos);
                }

                //while (rs.next()) {
                // data.setValue(rs.getInt(2), rs.getString("nombreO"), rs.getString("nombreO") + "= " + rs.getInt(2));
                // }
                JFreeChart grafico = ChartFactory.createBarChart3D("ESTADISTICAS VOTOS PROYECTOS 2016", "Nombre Proyecto ", "Total votos por proyecto", data, PlotOrientation.VERTICAL, true, true, true);
                response.setContentType("image/JPEG");
                OutputStream salida = response.getOutputStream();
                ChartUtilities.writeChartAsJPEG(salida, grafico, 1300, 600);//ANCHO ,LARGO
                //ChartFrame F = new ChartFrame("ESTADISTICAS VOTOS PROYECTOS 2016", grafico);
                //F.setSize(1000,600);
                //F.setLocationRelativeTo(null);
                //F.setVisible(true);

            } catch (Exception e) {
                System.out.println("Error al intentar mostrar una estadistica: " + e);
                out.print(e);
            }

            // try {
            //   DefaultPieDataset data1 = new DefaultPieDataset();//crear graficos circulares
            //DefaultCategoryDataset data = new DefaultCategoryDataset();
            //  while (rs.next()) {
            //      data1.setValue(rs.getString("nombreO"), rs.getInt(2));
            // data.setValue(rs.getInt(2),rs.getString("nombreO"), rs.getString("nombreO")+"= "+rs.getInt(2));
            // }
            // JFreeChart grafico1 = ChartFactory.createPieChart("Holas", data1, true, true, true);
            // response.setContentType("image/JPEG");
            //  OutputStream salida1 = response.getOutputStream();
            //  ChartUtilities.writeChartAsJPEG(salida1, grafico1, 600, 700);
            // } catch (Exception e) {
            //     out.print(e);
            //  }
        %>
    </body>
</html>
