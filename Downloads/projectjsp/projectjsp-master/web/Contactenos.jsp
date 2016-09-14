<%-- 
    Document   : Contactenos
    Created on : 14/09/2016, 01:39:56 PM
    Author     :Eliana Marquez,  Estiven Mazo , Sergio Buitrago
    Proyecto   : Huellvot
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% HttpSession sessionOk = request.getSession();
        sessionOk.invalidate();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HuellVot</title>
        <!-- icono -->
        <link rel="shortcut icon" type="image/x-icon" href="Multimedia/iC0.png" />
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
        <script src="js/Validaciones.js" type="text/javascript"></script>
        <!-- alertas swal -->
        <script src="alertas/sweetalert.min.js" type="text/javascript"></script>
        <link href="alertas/sweetalert.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="background-color:#eeeeee" >
        <div class="container">
            <font size="20" face="Arial" color="white"><div class="card-panel nav-wrapper teal darken-2 hoverable center-align ">
                <a class="brand-logo right hide-on-med-and-down"><img src="../Multimedia/logosena.PNG" width="55" height="55" ></a>
                Contacto
                <a class="brand-logo left"><img src="../Multimedia/iC0.png" width="55" height="80" ></a>
            </div>
            </font>
            <!-- Modulo de Contacto-->
            <div class="formContac" id="forcont" >
                <div class="input-field col s4 center-align"  style="background-color:#fc7323; z-index: -1; padding: 0.5%">
                    <p class="flow-text ">Envíanos tu mensaje.</p>
                </div>
                <div class="col s12 m12 l12" style="padding-top:5%;">
                <form action="SendMailServlet" method="POST">                    
                    <div class="col s6 m6 l6">
                        <div class="row">
                            <div class="input-field col s6 m6 l6">
                                <input type="email" id="correoCont" class="validate" name="correoCont" required="" length="80" onkeyup="introinsertU(event)" placeholder="@" title="llenar este campo.">
                                <label id="lblcorreoCont" for="textarea1">Correo:</label>
                                <div  id="vldrCorreoCont" style="color:#f57c00;;"> </div>
                            </div>
                            <div class="Inicio right col s6 m6 l6" style="padding-left:15%;"> 
                            <div class="row">
                                <div class="col 6 m6 6">
                                    <div class=""><img src="../Multimedia/iC0.png" width="243" height="400"></div>
                                </div>
                            </div>
                            </div>
                            <div class="input-field col s6 m6 l6">
                                <input id="nomCont" type="text" class="validate" name="nombreCont" required=""  length="70" onkeypress="sololetras()" onpaste=" return false">
                                <label id="lblnmCont" for="txtdesc">Nombre:</label>
                                <div id="vldrNombreCont" style="color:#f57c00;;"> </div>
                            </div>
                            <div class="input-field col s6 m6 l6">
                                <textarea id="msjCont" class="materialize-textarea" name="msjCont"required=""></textarea>
                                <label id="lblmsjCont" for="textarea1">Mensaje</label>
                                <div id="vldrmsjCont" style="color:#f57c00;"> </div>
                            </div>
                            <div class="row">
                            <div class=" center-align col s6 m6 l6" style="margin-top:0%;">
                                <button type="submit" id="InCont" name="btnCont" value="Contactanos" class="waves-effect waves-light btn teal darken-2" onclick="Contactar()">Enviar<i class="material-icons right">add</i></button>
                            </div>               
                        </div> 
                        </div>
                            
                    </div>
                </form>
                      
                </div>            
                
            
            <!-- Fin Modulo de Contacto-->


            <footer class="page-footer teal darken-2">            
                <div class="footer-copyright">
                    <div class="container">
                        © 2016 Copyright Huellvot(Version 1.0)
                        <a class="right tooltipped " data-tooltip="Twitter" data-position="top" href="https://www.twitter.com/HuellVot" target="_blank"><img class="hoverable circle" src="Multimedia/twitterLogo.png" width="45" height="45" style="padding: 5px"></a>
                        <a class="right tooltipped " data-tooltip="Facebook" data-position="top" href="https://www.facebook.com" target="_blank"><img class="hoverable circle" src="Multimedia/facebook.png" width="45" height="45" style="padding: 5px"></a>
                    </div>
                </div>
            </footer>
        </div>        
    </body>
</html>

