<%-- 
    Document   : prueba
    Created on : 4/07/2016, 09:39:42 PM
    Author     :Eliana Marquez,  Estiven Mazo , Sergio Buitrago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
        <script src="js/scriptajax.js" type="text/javascript"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>



        <script type="text/javascript" src="JS-Estadisticas/Chart.bundle.min.js"></script>
        <script src="js/JS-Estadisticas/jquery-1.12.0.min.js" type="text/javascript"></script>
    </head>
    <body>
        
        <button type="submit" id="btnEstadisticas" class="waves-effect waves-light btn teal darken-2"  >Generar Estadisticas<i class="material-icons right">add</i></button>
        <div id="canvas-container" style="width:50%;">
            <canvas id="chart" width="500" height="350"></canvas>
        </div>






        <script type="text/javascript">
            $(document).ready(function () {
                $("#canvas-container").hide();
                $("#btnEstadisticas").click(function () {
                    $("#canvas-container").show();
                });
                var datos = {
                    type: "pie",
                    data: {
                        datasets: [{
                                data: [
                                    5,
                                    10,
                                    40,
                                    12,
                                    23,
                                ],
                                backgroundColor: [
                                    "#F7464A",
                                    "#46BFBD",
                                    "#FDB45C",
                                    "#949FB1",
                                    "#4D5360",
                                ],
                            }],
                        labels: [
                            "Datos 1",
                            "Datos 2",
                            "Datos 3",
                            "Datos 4",
                            "Datos 5",
                        ]
                    },
                    options: {
                        responsive: true,
                    }
                };

                var canvas = document.getElementById('chart').getContext('2d');
                window.pie = new Chart(canvas, datos);

                setInterval(function () {
                    datos.data.datasets.splice(0);
                    var newData = {
                        backgroundColor: [
                            "#F7464A",
                            "#46BFBD",
                            "#FDB45C",
                            "#949FB1",
                            "#4D5360",
                        ],
                        data: [getRandom(), getRandom(), getRandom(), getRandom(), getRandom()]
                    };

                    datos.data.datasets.push(newData);

                    window.pie.update();

                }, 5000);



                function getRandom() {
                    return Math.round(Math.random() * 100);
                }


            });

        </script>
    </body>
</html>
