<%-- 
    Document   : Inicio
    Created on : 14/06/2016, 05:33:14 PM
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
            <div class="col s12 m12 l12 ">
                <font size="20" face="Arial" color="white"><div class="card-panel nav-wrapper teal darken-2 hoverable center-align ">
                    <a class="brand-logo right hide-on-med-and-down"><img src="../Multimedia/logosena.PNG" width="55" height="55" ></a>
                    Bienvenido
                    <a class="brand-logo left"><img src="../Multimedia/iC0.png" width="55" height="80" ></a>
                </div>
                </font>
            </div>
            <div class="input-field col s4 center-align"  style="background-color:#fc7323; z-index: -1;">
                <p class="flow-text ">Recuerde! <br>Ingresar su  numero de documento como usuario y tambien como clave.</p>
            </div>
            <div class="input-field col s12 m6">
            <div class="card-panel  white hoverable">  
                <div class='row'>
                    <div class="input-field col s1 m4 l4"></div>
                    <div class="input-field col s10 m4 l4">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="usuario" type="text" name="usuario" class="validate" onpaste=" return false">
                        <label>Usuario:</label>
                        <div class="col s12 center" id="vldrusuario" style="color:#f57c00;">
                        </div>  
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s1 m4 l4"></div>
                    <div class="input-field col s10 m4 l4 center-align">
                        <i class="material-icons prefix">lock</i>
                        <input id="clave" type="password"  name="documento" class="validate" onpaste=" return false" onkeydown="enviar(event)">
                        <label class="left-align">Clave:</label>
                        <div class="col s12 center" id="vldrdocumento" style="color:#f57c00;"> </div>
                        <button class="waves-effect waves-light btn teal darken-2 "  type="button" name="ingresa" id="btnLogin" onclick="ingresar()">Ingresar <i class="material-icons right">send</i></button>
                    </div>
                </div>
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
</html>
