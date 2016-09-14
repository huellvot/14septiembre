<%-- 
    Document   : reportes
    Created on : 18-ago-2016, 10:27:14
    Author     :Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>
<!-- Estadisticas -->
        <script type="text/javascript" src="JS-Estadisticas/Chart.bundle.min.js"></script>
        <script src="js/Validaciones.js" type="text/javascript"></script>
<div class="row">
                    <div class="col s12 m12 l12">
                        <font size="20" face="Arial" color=""><div class="card-panel hoverable center">Reportes</div></font>
                    </div>
                </div>
                <form name="reporte" action="PDF" method="POST" >
                    <div class="row">
                        <div class="col m3 s12">
                            <button type="submit"  class="waves-effect waves-light btn teal darken-2"  name="btnver" value="visualizarpdf" >Generar reporte<i class="material-icons right">add</i></button>
                        </div>
                    </div>
                </form> 
                <button type="submit" id="btnEstadisticas" class="waves-effect waves-light btn teal darken-2">Generar Estadisticas<i class="material-icons right">add</i></button>
                <div id="canvas-container" style="width:50%;">

                    <p id="mf"></p>


                    <canvas id="chart" width="500" height="350"></canvas>

                </div>
