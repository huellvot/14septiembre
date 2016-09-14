<%-- 
    Document   : SuperAdmin
    Created on : 07-sep-2016, 13:04:47
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HuellVot</title>
        <!-- icono -->
        <link rel="shortcut icon" type="image/x-icon" href="Multimedia/iC0.png" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!-- cdn iconos y jquery -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
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
                        <li><a class="showUser tooltipped " data-tooltip="Administrar Usuarios">Usuarios</a></li>
                        <li><a class="showOp tooltipped " data-tooltip="Administrar Opciones">Opciones</a></li>
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
        
           <div class="adminUsario" id="adminUsario" >
                <div class="row">
                    <div class="col s12 m12 l12">
                        <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Usuarios</div></font>
                    </div>
                </div>
                <form action="" method="">
                <div class="row">
                    <div class="col m12">
                        <div class="input-field col s12 m6">
                            <p class="tooltipped" data-tooltip="Permite adicionar campo de genero en el formulario usuario.">
                                <input type="checkbox" id="genero"  />
                                <label for="genero">Genero</label>
                            </p>
                        </div>
                        <div class="input-field col s12 m6">
                            <p class="tooltipped" data-tooltip="Permite adicionar campo de Tipo documento en el formulario usuario.">
                                <input type="checkbox" id="tipoDocumento"  />
                                <label for="tipoDocumento">Tipo documento</label>
                            </p>
                        </div>
                        <div class="input-field col s12 m6">
                            <p class="tooltipped" data-tooltip="Permite adicionar campo de Imagen en el formulario usuario.">
                                <input type="checkbox" id="imagen"/>
                                <label for="imagen">Imagen</label>
                            </p>
                        </div>
                        <div class="input-field col s12 m6">
                            <p class="tooltipped" data-tooltip="Permite adicionar campo de Ficha en el formulario usuario.">
                                <input type="checkbox" id="ficha" />
                                <label for="ficha">Ficha</label>
                            </p>
                        </div>
                    </div>
                  </div>
                     <div class="row ">
                <div class=" center-align">
                    <button type="submit" id="btnAddU" name="btnAddU" value="" class="waves-effect waves-light btn teal darken-2">Adiccionar<i class="material-icons right">add</i></button>
                </div>               
            </div>
                  </form>
            </div>
            

            <!--Pie de pagina-->
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

        <script type="text/javascript">
            $(document).ready(function () {
                $(".button-collapse").sideNav();
                $('select').material_select();
            });
        </script>

    </body>
</html>
