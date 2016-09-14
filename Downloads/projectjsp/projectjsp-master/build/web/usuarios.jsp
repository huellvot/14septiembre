<%-- 
    Document   : Admin
    Created on : 16-jun-2016, 11:53:42
    Author     : Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>

<%@page import="com.prjhuellvotweb.DAO.DAOUsuario"%>
<%@page import="com.prjhuellvotweb.modelo.Voto"%>
<%@page import="com.prjhuellvotweb.modelo.Categoria"%>
<%@page import="java.sql.Date"%>
<%@page import="com.prjhuellvotweb.modelo.Usuario"%>
<%@page import="com.prjhuellvotweb.modelo.Opcion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Huellvot</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <!--Cdn para Tabla -->
        <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
        <!-- Estadisticas -->
        <script type="text/javascript" src="JS-Estadisticas/Chart.bundle.min.js"></script>
        <script src="js/Validaciones.js" type="text/javascript"></script>
        <!-- alertas swal -->
        <script src="alertas/sweetalert.min.js" type="text/javascript"></script>
        <link href="alertas/sweetalert.css" rel="stylesheet" type="text/css"/>
        <title>Administrador</title>
    </head>
    <body style="background-color:#eeeeee">
        <div class="container">
            <div class="formUsuario" id="foruser" >
                <div class="row">
                    <div class="col s12 m12 l12">
                        <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Usuarios</div></font>
                    </div>
                </div>

                <form>
                    <div class="row">
                        <div class="col m12">
                            <div class="input-field col s12 m6">
                                <input id="nomU" type="text" class="validate" name="nombre" required="" onkeypress="sololetras()" onpaste=" return false">
                                <label id="lblnm" for="txtdesc">Nombre:</label>
                                <div id="vldrNombre" style="color:red;"> </div>
                            </div>
                            <div class="input-field col s12 m6">
                                <input id="docu" type="number" class="validate" name="documento" required="" maxlength="15">
                                <label id="lbldocu" for="txtdesc" >Documento:</label>
                                <div id="vldrDocumento" style="color:red;"> </div>
                            </div>
                            <div class="input-field col s12 m12">
                                <input type="email" id="correo" class="validate" name="correo" required="">
                                <label id="lblcorreo" for="textarea1">Correo:</label>
                                <div  id="vldrCorreo" style="color:red;"> </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col m3 s12">
                            <button type="button" id="InUsu" name="btnUsu" value="insertarUsu" class="waves-effect waves-light btn teal darken-2">Insertar<i class="material-icons right">add</i></button>
                        </div>                   
                    </div>
                </form>

                <div class="row " id="tabUsu">
                    <div class="col m12">
                        <form>
                            <table class="striped tusu" id="tblUsuario">
                                <thead>
                                    <tr>
                                        <th data-field="id">ID</th>
                                        <th data-field="desc">Nombre</th>
                                        <th data-field="precio">Documento</th>
                                        <th data-field="precio">Fecha Registro</th>
                                        <th data-field="precio">Correo</th>
                                        <th data-field="precio">Editar</th>
                                        <th data-field="precio">Eliminar</th>

                                    </tr>
                                </thead>

                                <tbody >
                                    <%
                                        
                                        DAOUsuario daoU = new DAOUsuario();
                                        ArrayList<Usuario> listU = new ArrayList();
                                        listU = daoU.consultarAllUsuarios();
                                        for (int i = 0; i < listU.size(); i++) {
                                    %> 
                                    <tr>
                                        <% Usuario u = new Usuario();
                                            u = (listU.get(i));
                                            int id = u.getId();
                                            String nom = u.getNombre();
                                            String cor = u.getCorreo();
                                            String doc = u.getNumerodocumento();
                                            Date fec = u.getFecha();
                                        %>
                                        <td><%=id%></td>
                                        <td><%=nom%></td>
                                        <td><%=doc%></td>
                                        <td><%=fec%></td>
                                        <td><%=cor%></td>
                                        <td><button type="button" id="btnModificarUs" name="mdU" value="<%=doc%>" class="waves-effect waves-light btn orange darken-2 material-icons right acUsu"><i class="material-icons center">mode_edit</i></button>
                                        </td>
                                        <td> <button type="button" id="btnEliminarUsu"  name="btnUsu" value="<%=doc%>" class="waves-effect waves-light btn teal darken-2 eliminarU"><i class="material-icons center">delete</i></button></td>

                                    </tr><%}%>
                                </tbody>
                            </table>
                        </form>

                        <!-- modal para modificar datos de usuario-->
                        <div class="row">
                            <div id="modal1" class="modal">
                                <div class="modal-content">
                                    <h4>MODIFICAR USUARIO</h4>
                                    <div id="modalUsuario"></div>
                                    <div class="col m12 s12">
                                        <div class="modal-footer">
                                            <a class=" modal-action modal-close waves-effect waves-red btn-flat " id="cancelarMU">Cancelar</a>
                                            <button type="button" id="btnModUsu" name="btnUsu" value="modificarUsu" class="waves-effect modal-close waves-light btn teal darken-2 ">Modificar<i class="material-icons right ">mode_edit</i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>   <!-- fin del modal modificar usuario -->
                        </div>
                    </div>
                </div> 
            </div>  <!-- fin del div contenedor usuario-->


        </div>  
    </body>
</html>

