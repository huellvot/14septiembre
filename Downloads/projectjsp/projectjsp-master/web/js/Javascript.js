
function carga() {
    //swal({title: "Recuerde", type: "warning", text: "Seleccionar una opción para la votacion y luego dar click en el boton votar", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
    //varibles
    contador_segundos = 60;
    contador_minutos = 1;
    s = document.getElementById("segundos");
    m = document.getElementById("minutos");

    var cron = setInterval(//funcion que permite ejcutar un codigo cada determinado tiempo
            function () {
                
                if (contador_minutos <= 0 && contador_segundos <= 1) {
                    clearInterval(cron);
                    contador_segundos = 0;
                    window.close();                      
                    //swal({title: "Lo setimos", type: "warning", text: "Su sesíon ha terminado,intente acceder mas tarde.", allowEscapeKey: false, confirmButtonText: "Aceptar"});
                    window.location.assign('index.jsp');

                }
                if (contador_segundos <= 0 && contador_minutos > 0) {
                    contador_segundos = 59;
                    contador_minutos--;
                    m.innerHTML = contador_minutos;
                    if (contador_minutos = 0) {
                        // clearInterval(contador_segundos);
                        contador_segundos = 59;
                        contador_minutos = 0;

                    }
                }
                s.innerHTML = contador_segundos;
                contador_segundos--;
            }, 1000);
}
