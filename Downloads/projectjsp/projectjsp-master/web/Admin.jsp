<%-- 
    Document   : Admin
    Created on : 16-jun-2016, 11:53:42
    Author     : Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>
<% HttpSession sessionOk = request.getSession();
    if (sessionOk.getAttribute("admin") == null) {
        sessionOk.invalidate();
        sessionOk = request.getSession();
        response.sendRedirect("index.jsp");
    }else{%>

<%@page import="com.prjhuellvotweb.DAO.DAOCategoria"%>
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
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
        <title>Huellvot</title>
        <!-- icono -->
        <link rel="shortcut icon" type="image/x-icon" href="Multimedia/iC0.png" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- cdn iconos y jquery -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <!--Cdn para Tabla -->

    <link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"><font></font>
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <!--Cdn para botones-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css"><font></font>
    <script type="text/javascript" src="http://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
    <script type="text/javascript" src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script type="text/javascript" src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script type="text/javascript" src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>

    <!-- Estadisticas -->
    <script type="text/javascript" src="JS-Estadisticas/Chart.bundle.min.js"></script>
    <!-- validaciones js -->
    <script src="js/Validaciones.js" type="text/javascript"></script>
    <!-- alertas swal -->
    <script src="alertas/sweetalert.min.js" type="text/javascript"></script>
    <link href="alertas/sweetalert.css" rel="stylesheet" type="text/css"/>
</head>


<body style="background-color:#eeeeee">
    <div class="container">
        <nav>
            <div class="nav-wrapper teal darken-2">
                <a class="brand-logo right logo hoverable"><img src="../Multimedia/logosena.PNG" width="55" height="55" ></a>
                <a class="brand-logo center">Huellvot</a>
                <a data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul class="hide-on-med-and-down">
                    <li><a class="tooltipped" data-tooltip="Inicio"><img src="../Multimedia/iC0.png" width="45" height="55" ></a></li>
                    <li><a class="showUser tooltipped " data-tooltip="Administrar Usuarios" id="mnU">Usuarios</a></li>
                    <li><a class="showOp tooltipped " data-tooltip="Administrar Opciones" id="mnO">Opciones</a></li>
                    <li><a class="showRep tooltipped " data-tooltip="Generar reportes" id="mnR">Reportes</a></li>
                    <li><a class="hoverable tooltipped " data-tooltip="Cerrar sesión" href="Cerrar-Sesion.jsp"><i class="material-icons center">settings_power</i></a>
                </ul>
                <ul class="side-nav" id="mobile-demo">
                    <li><a class="showUser hoverable">Usuarios</a></li>
                    <li><a class="showOp hoverable">Opciones</a></li>
                    <li><a class="showRep hoverable">Reportes</a></li>
                    <li><a class="hoverable" href="Cerrar-Sesion.jsp"><i class="material-icons left">settings_power</i></a>
                </ul>
            </div>
        </nav>

        <div class="Inicio"> 
            <div class="row">
                <div class="col s12 m12 l12">
                    <div class="card-panel hoverable center"><img src="../Multimedia/iC0.png" width="243" height="400"></div>
                </div>
            </div>
        </div>

        <div class="formUsuario" id="foruser" >
            <div class="row">
                <div class="col s12 m12 l12">
                    <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Usuarios</div></font>
                </div>
            </div>


            <div class="row">
                <div class="col m12">
                    <div class="input-field col s12 m6">
                        <input id="nomU" type="text" class="validate" name="nombre" required=""  length="70" onkeypress="sololetras()" onpaste=" return false">
                        <label id="lblnm" for="txtdesc">Nombre:</label>
                        <div id="vldrNombre" style="color:#f57c00;;"> </div>
                    </div>
                    <div class="input-field col s6 m3">
                        <input id="docu" type="text"  name="documento" required="" length="11" class="validate" onkeypress="solonum()">
                        <label id="lbldocu" for="txtdesc" >Documento:</label>
                        <div id="vldrDocumento" style="color:#f57c00;;"> </div>
                    </div>
                    <div class="input-field col s6 m3">
                        <select  id="sexo" required="" name="sexo" value="">
                            <option value="" disabled selected>Escoja el sexo</option>
                            <option value="femenino">Femenino</option> 
                            <option value="masculino">Masculino</option>  
                        </select>
                        <label id="lblsexo">Género</label>
                        <div id="vldrsexo" style="color:#f57c00;;"> </div>
                    </div>
                    <div class="input-field col s12 m12">
                        <input type="email" id="correo" class="validate" name="correo" required="" length="80" onkeyup="introinsertU(event)" placeholder="@" title="llenar este campo.">
                        <label id="lblcorreo" for="textarea1">Correo:</label>
                        <div  id="vldrCorreo" style="color:#f57c00;;"> </div>
                    </div>
                </div>

            </div>
            <div class="row ">
                <div class=" center-align">
                    <button type="button" id="InUsu" name="btnUsu" value="insertarUsu" class="waves-effect waves-light btn teal darken-2" onclick="insertarU()">Insertar<i class="material-icons right">add</i></button>
                </div>               
            </div>

            <!-- Tabla de usuarios-->
            <div class="row hoverable" >
                <div class="col s12 m12 l12" id="tabUsu" >
                </div>
            </div>
            <!-- modal para modificar datos de usuario-->
            <div class="row ">
                <div class="col m12">
                    <div id="modal1" class="modal">
                        <div class="modal-content">
                            <h4 class="center-align">MODIFICAR USUARIO</h4>
                            <div id="modalUsuario">  </div>
                            <div class="col m12 s12">
                                <div class="modal-footer">
                                    <a class=" modal-action modal-close waves-effect waves-red btn-flat hoverable " id="cancelarMU">Cancelar</a>
                                    <button type="button" id="btnModUsu" name="btnUsu" value="modificarUsu" class="waves-effect  waves-light btn teal darken-2" onclick="actualizarU()">Modificar<i class="material-icons right ">mode_edit</i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   <!-- fin del modal modificar usuario -->
            </div>
        </div>  <!-- fin del Modulo Usuario-->

        <!-- Modulo de Opcion-->
        <div class="formOpcion" id="forop">
            <div class="row">
                <div class="col s12 m12 l12">
                    <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Opción</div></font>
                </div>
            </div>
            <div class="row">
                <div class="col m12">
                    <div class="input-field col s12 m6">
                        <input  id="nomO" type="text" class="validate" name="nombrep" required="" length="120" >
                        <label  id="lblnomO" for="txtdesc">Nombre</label>
                        <div id="vldrNombreO" style="color:#f57c00;;"> </div>
                    </div>
                    <div class="input-field col s12 m6">
                        <select  id="catO" required="" name="categoriap" value="">
                            <option value="" disabled selected>Escoja la categoria</option>
                            <% DAOCategoria dao = new DAOCategoria();
                                ArrayList<Categoria> list = new ArrayList();
                                list = dao.consultarCategorias();
                                for (int i = 0; i < list.size(); i++) {
                                    Categoria c = new Categoria();
                                    c = list.get(i);
                                    int m = c.getIdCategoria();
                                    String nom = c.getNombreC();
                            %> 

                            <option value="<%=m%>"><%=nom%></option>
                            <%}%>
                        </select>
                        <label id="lblcatO">Categoria</label>
                        <div id="vldrcatO" style="color:#f57c00;;"> </div>
                    </div>

                    <div class="input-field col s12 m12">
                        <textarea id="descriO" class="materialize-textarea" name="descripcionp"required=""></textarea>
                        <label id="lbldesO" for="textarea1">Descripción</label>
                        <div id="vldrdescO" style="color:#f57c00;"> </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="center-align">
                    <button type="button" id="inOp" class="waves-effect waves-light btn teal darken-2" name="btnOp" value="InsertarOpcion" onclick="insertarO()" >Insertar<i class="material-icons right">add</i></button>
                </div>
            </div>

            <!-- Tabla opción-->
            <div class="row hoverable">
                <div class="col m12" id="tabOp">
                </div>
            </div> 
            <!-- modal para modificar datos de Opción-->
            <div class="row ">
                <div class="col m12">
                    <div id="modal2" class="modal">
                        <div class="modal-content">
                            <h4 class="center-align">MODIFICAR OPCIÓN</h4>
                            <div id="modalOpcion">  </div>
                            <div class="col m12 s12">
                                <div class="modal-footer">
                                    <a class=" modal-action modal-close waves-effect waves-red btn-flat hoverable " id="cancelarMO">Cancelar</a>
                                    <button type="button" id="btnModOpc" name="btnOpc" value="modificarOpc" class="waves-effect  waves-light btn teal darken-2" onclick="actualizarO()">Modificar<i class="material-icons right ">mode_edit</i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   <!-- fin del modal modificar Opcion -->
            </div>
        </div> <!--Fin del Modulo Opcion-->

        <!-- Modulo de Reportes-->
        <div class="formReporte">
            <div class="row">
                <div class="col s12 m12 l12">
                    <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Reportes</div></font>
                </div>
            </div>
            <div class="row">
                <div class="col s8 m4">
                    <button id="btnEstadisticas" class="waves-effect waves-light btn teal darken-2">Estadisticas de género<i class="material-icons right">add</i></button>
                </div>
                <form name="reporte" action="Reporte" target="_blank"  method="POST" >
                    <div class="col s8 m4">
                        <button type="button" class="waves-effect waves-light btn teal darken-2" id="msjReport">Reporte resultados<i class="material-icons right">add</i></button>
                    </div>
                    <!-- Modal mensaje PDF-->
                    <div class="col m12">
                        <div id="modalReport" class="modal">
                            <div class="modal-content">
                                <h4 class="center-align">REPORTE</h4>
                                <div id="modalReporte">                                 
                                    <div class="input-field col s12 m12">
                                        <textarea id="txasjReport" class="materialize-textarea" name="report" required="" id='trep'></textarea>
                                        <label id="lblreport" for="txasReport">Mensaje para el reporte</label>
                                        <div id="vldrreport" style="color:#f57c00;"> </div>
                                    </div>
                                    <div class="modal-footer">
                                        <a class=" modal-action modal-close waves-effect waves-red btn-flat hoverable" id="cancelarMO">Cancelar</a>
                                        <button type="submit"  class="waves-effect waves-light btn teal darken-2"  name="btnver" value="visualizarpdf" id='rep'>Generar reporte<i class="material-icons right">add</i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Final Modal mensaje PDF-->
                </form>                
            </div>
            <div id="canvas-container" style="width:50%;">
                <canvas id="chart" width="500" height="350"></canvas>
            </div>
        </div>
        <footer class="page-footer teal darken-2">            
            <div class="footer-copyright">
                <div class="container">
                    © 2016 Copyright Huellvot(Version 1.0)
                    <a class="right tooltipped " data-tooltip="Contacto" data-position="top" href="Contactenos.jsp" target="_blank"><img class="hoverable circle" src="Multimedia/contac.png" width="45" height="45" style="padding: 5px"></a>
                    <a class="right tooltipped " data-tooltip="Twitter" data-position="top" href="https://www.twitter.com/HuellVot" target="_blank"><img class="hoverable circle" src="Multimedia/twitterLogo.png" width="45" height="45" style="padding: 5px"></a>
                    <a class="right tooltipped " data-tooltip="Facebook" data-position="top" href="https://www.facebook.com" target="_blank"><img class="hoverable circle" src="Multimedia/facebook.png" width="45" height="45" style="padding: 5px"></a>
                </div>
            </div>
        </footer>
    </div>
</body>
<%} %>
<script type="text/javascript">
        if (history.forward(1)) {
            location.replace(location.reload());
        }
    </script> 
</html>
