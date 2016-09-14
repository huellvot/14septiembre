<%-- 
    Document   : n
    Created on : 14/07/2016, 11:37:02 PM
    Author     : Familia Mazo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huellvot</title>
        <!-- icono -->
        <link rel="shortcut icon" type="image/x-icon" href="/iC0.png" />
        <link href="css/ErrorPage.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col s12 m12 l12">
                <font size="20" face="Arial" color="white"><div class="card-panel nav-wrapper teal darken-2 hoverable center-align ">
                    <a class="brand-logo right"><img src="../Multimedia/logosena.PNG" width="55" height="55" ></a>
                    HuellVot
                    <a class="brand-logo left"><img src="../Multimedia/iC0.png" width="55" height="80" ></a>
                </div>
                </font>
            </div>
            <div class="row center valign-wrapper ">
                <div class="col s12 m12 l12" style="margin-top:6%">
                    <div class="error">
                    <h4>Perdone las molestias!</h4>
                    <p>Lo sentimos, pero la pagina que estas buscando no existe o no se encuentra aquí.</p>
                    </div>
                    <a href="index.jsp" <button class="waves-effect waves-light btn teal darken-2 hoverable">Click aqui para ir al inicio.<i class="material-icons right">home</i></button></a>
                </div>
            </div>
            <footer class="page-footer teal darken-2">            
            <div class="footer-copyright">
                <div class="container">
                    © 2016 Copyright Huellvot(Version 1.0)
                    <a class="right" href="https://www.twitter.com" target="_blank"><img class="hoverable circle" src="Multimedia/twitterLogo.png" width="45" height="45" style="padding: 5px"></a>
                    <a class="right" href="https://www.facebook.com" target="_blank"><img class="hoverable circle" src="Multimedia/facebook.png" width="45" height="45" style="padding: 5px"></a>
                </div>
            </div>
        </footer>
        </div>
        <script language="Javascript">
            <!-- Begin
            function disableselect(e) {
                return false
            }
            function reEnable() {
                return true
            }
            document.onselectstart = new Function("return false")
            if (window.sidebar) {
                document.onmousedown = disableselect
                document.onclick = reEnable
            }
            // End -->
        </script>

</html>