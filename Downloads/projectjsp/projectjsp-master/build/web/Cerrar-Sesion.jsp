<%-- 
    Document   : Cerrar-Session
    Created on : 24-ago-2016, 20:45:38
    Author     : Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<% HttpSession sessionOk = request.getSession();
sessionOk.invalidate();
response.sendRedirect("index.jsp");
%>
