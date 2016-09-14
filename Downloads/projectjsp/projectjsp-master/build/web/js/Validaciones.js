/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * Author     : Eliana Marquez,Estiven Mazo,Sergio Buitrago
 * Proyecto   : Huellvot
 */
function voto(bu) {
    var idO = bu.value;
    swal({title: "¿Seguro?", text: "DESEA VOTAR POR: " + bu.name + "?", type: "warning", showCancelButton: true, allowEscapeKey: false, cancelButtonText: "Cancelar", cancelButtonColor: "#fc7323", closeOnCancel: true, confirmButtonColor: "#238276", confirmButtonText: "Votar", closeOnConfirm: false, animation: "slide-from-top"}, function (isConfirm) {
        if (isConfirm) {
            $.ajax({method: "POST",
                url: "../ServletVoto",
                data: {Opcion: idO
                },
                beforeSend: function () {
                    if (idO <= 0) {
                        swal({title: "Error", type: "error", text: "Debe seleccionar una opción", timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
                    }
                }, success: function () {
                    swal({title: "Gracias por votar", text: "Te esperamos pronto.", type: "success", confirmButtonText: "Aceptar",confirmButtonColor: "#238276", allowEscapeKey: false}, function (isConfirm) {
                    if (isConfirm) {
                        window.location.assign('index.jsp');
                        window.open("Administrar.jsp");
                    }
                });
                },
                error: function (respuesta) {
                    swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar"});

                }
            });

        }
    });
}
// validacion para escribir solo  letras
function sololetras() {
    if (((event.keyCode >= 33) && (event.keyCode < 65)) || ((event.keyCode > 90) && (event.keyCode < 97)) || ((event.keyCode > 122) && (event.keyCode < 193)))
        event.returnValue = false;
}//fin funcion validar solo letras

// validacion para escribir solo  numeros
function solonum() {
    if ((event.keyCode < 48) || (event.keyCode > 57))
        event.returnValue = false;
}//fin funcion validar solo numeros

//Funcion para ingresar al sistema
function ingresar() {
    var usuario = document.getElementById("usuario").value;
    var clave = document.getElementById("clave").value;
    $.ajax({method: "POST",
        url: "../ServletLogin",
        data: {usuario: $("#usuario").val(),
            documento: $("#clave").val()
                    //validacion de campos en el inicio
        }, beforeSend: function () {
            if (usuario === "" || usuario.length === 0 || usuario === null) {
                $("#usuario").focus();
                $("#vldrusuario").html("Ingrese usuario.").slideDown(500);
            } else {
                $("#vldrusuario").html("").slideUp(300);
            }
            if (clave === null || clave.length === 0 || clave === "") {
                $("#clave").focus();
                $("#vldrdocumento").html("Ingrese clave.").slideDown(500);
            } else {
                $("#vldrdocumento").html("").slideUp(300);
            }

            if (usuario === "" && clave === "") {
                swal({title: "Error", text: "Los campos estan vacios", type: "error", allowEscapeKey: false, confirmButtonText: "Aceptar", confirmButtonColor: "#238276"});
            }
        }, success: function (msj) {
            if (msj === "admin") {
                window.location.assign('Admin.jsp');
            }
            if (msj === "votacion") {
                swal({title: "Tiene 2 minutos para votar:", type: "warning", text: "De click al número que corresponde a la opción que eligió.", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"},
                        function () {
                            window.location.assign('Votacion.jsp');
                        });

            }
        }, error: function (res) {
            swal({title: "Error", type: "error", text: res.responseText, allowEscapeKey: false, confirmButtonText: "Aceptar", confirmButtonColor: "#238276"});
        }
    }).done(function () {
        $("#usuario").val("");
        $("#usuario").focus();
        $("#clave").val("");
    });
}//fin funcion ingresar

//Funcion para detectar el enter en login
function enviar(evento) {
    if (evento.keyCode === 13) {
        ingresar();
    }
}//fin funcion intro login

////////////////////////////////////- MODULO USUARIO - ///////////////////////////

//tabla usuarios
function tabla() {
    $.ajax({method: "POST",
        url: "../ServletTabUsu",
        error: function (respuesta) {
            swal({title: "Advertencia", type: "warning", text: respuesta.responseText, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
        }

    }).done(function (msj) {
//mostrar datos de la tabla
        $("#tabUsu").html(msj);
        //paginacion y buscar
        $('.tblUsuario').addClass("grey lighten-2");
        $('.tblUsuario').dataTable({
            dom: 'Bfrtip',
            buttons: [
                {extend: 'print',
                    message: 'Reporte de usuarios creado por Huellvot (Version 1.0)',
                    text: 'Imprimir', exportOptions: {
                        columns: [0, 1, 2, 3, 4]
                    }}, {extend: 'copy',
                    text: 'Copiar', exportOptions: {
                        columns: [0, 1, 2, 3, 4]
                    }
                },
//                {extend: 'csv',
//            text: 'Exportar a csv' ,exportOptions: {
//                    columns: [ 0, 1, 2 ]
//                } },
                {extend: 'excel',
                    message: 'Reporte de usuarios creado desde HuellVot (Version1.0).',
                    text: 'Exportar a excel', exportOptions: {
                        columns: [0, 1, 2, 3, 4]
                    }},
                {extend: 'pdf',
                    background: 'simple text',
                    pageMargins: [40, 60, 40, 60],
                    text: 'Exportar a PDF'
                    , exportOptions: {
                        columns: [0, 1, 2, 3, 4]
                    },
                    message: 'Reporte de usuarios creado desde HuellVot (Version1.0).'}

            ],
            "language": {
                "decimal": "",
                "emptyTable": "No hay registros en la tabla",
                "infoPostFix": "",
                "thousands": ",",
                "loadingRecords": "Loading...",
                "processing": "Processing...",
                "infoFiltered": "(Filtrado de _MAX_ registros)",
                "zeroRecords": "No se encontraron registros",
                "lengthMenu": "",
                "search": "Buscar:",
                "info": "Registros del _START_ al _END_  de _TOTAL_ ",
                "infoEmpty": "Mostrando 0 a 0 de 0 resultados",
                "paginate": {
                    "next": "Siguiente",
                    "previous": "Anterior"
                },
                buttons: {
                    copyTitle: 'Listo',
                    copySuccess: {
                        _: 'Se han copiados %d registros al portapepeles',
                        1: 'Se ha copiado 1 registro al portapapeles'
                    }
                }
            }});
    });
}//fin funcion  tabla usuario

//ajax insertar usuario
function insertarU() {
    $.ajax({method: "POST",
        url: "../ServletUsuario",
        beforeSend: function () {
            function ValidarCorreo(correo) {
                var tstCorreo = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-]+)\.)+([a-zA-Z0-9{2,4}])+$/;
                return tstCorreo.test(correo);
            }
            function ValidarNom(nombre) {
                var tstnom = /^([a-zA-Z])/;
                return tstnom.test(nombre);
            }
            //variables
            var nombre = document.getElementById("nomU").value;
            var documento = document.getElementById("docu").value;
            var correo = document.getElementById("correo").value;
            var sexo = document.getElementById("sexo").value;
            if (nombre === null || nombre.length === 0) {
                $('#vldrNombre').html('Debe ingresar nombre.').slideDown(500);
                $('#lblnm').css("color", "#b71c1c");
                $('#nomU').focus();
                return  false;
            } else if (ValidarNom(nombre) === false) {
                $('#vldrNombre').html('El nombre debe comenzar con letras').slideDown(500);
                $('#lblnm').css("color", "#f44336");
                $('#nomU').focus();
                return  false;
            } else if (nombre.length > 69) {
                $('#vldrNombre').html('El nombre es muy largo.').slideDown(500);
                $('#lblnm').css("color", "#f44336");
                $('#nomU').focus();
                return  false;
            } else {
                $('#lblnm').css("color", "#009688");
                $('#vldrNombre').html('').slideUp(30);
            }
            if (documento === "") {
                $('#vldrDocumento').html('Debe ingresar numero de documento.').slideDown(500);
                $('#lbldocu').css("color", "#f44336");
                $('#docu').focus();
                return  false;
            } else if (documento.length > 11) {
                $('#vldrDocumento').html('El numero de documento es muy largo.').slideDown(500);
                $('#lbldocu').css("color", "#f44336");
                $('#docu').focus();
                return  false;
            } else {
                $('#lbldocu').css("color", "#009688");
                $('#vldrDocumento').html('').slideUp(300);
            }
            if (isNaN(documento)) {
                $('#vldrDocumento').html('El documento ingresado no es un numero.').slideDown(500);
                $('#lbldocu').css("color", "#f44336");
                $('#docu').focus();
                return  false;
            } else {
                $('#lbldocu').css("color", "#009688");
                $('#vldrDocumento').html('').slideUp(300);
            }
            if (sexo === "") {
                $('#vldrsexo').html('Debe elegir un sexo').slideDown(500);
                $('#lblsexo').css("color", "#f44336");
                $('#sexo').focus();
                return  false;
            } else {
                $('#vldrsexo').html('').slideUp(300);
                $('#lblsexo').css("color", "#009688");
            }
            if (ValidarCorreo(correo) === false) {
                $('#vldrCorreo').html('Debe ingresar un correo Valido.').slideDown(500);
                $('#lblcorreo').css("color", "#f44336");
                $('#correo').focus();
                return  false;
            } else if (correo.length > 80) {
                $('#vldrCorreo').html('El correo es muy largo.').slideDown(500);
                $('#lblcorreo').css("color", "#f44336");
                $('#correo').focus();
                return  false;
            } else {
                $('#vldrCorreo').html('').slideUp(300);
                $('#lblcorreo').css("color", "#009688");
            }

        },
        data: {nombre: $("#nomU").val(),
            documento: $("#docu").val(),
            correo: $("#correo").val(),
            sexo: $("#sexo").val()
        }, error: function (respuesta) {
            swal({title: "Error", type: "error", text: respuesta.responseText, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
            $("#nomU").val("");
            $("#docu").val("");
            $("#correo").val("@");
        }, success: function () {
            swal({title: "Resultado:", text: "Usuario registrado.", timer: 2500, type: "success", showConfirmButton: false, allowEscapeKey: false});
            tabla();
        }
    })
            .done(function () {
                $("#nomU").val("");
                $("#docu").val("");
                $("#correo").val("@");
            });
}//fin dela funcion insertarU 

//Validar enter insertar usuario
function introinsertU(evento) {
    if (evento.keyCode === 13) {
        insertarU();
    }
}//fin funcion intro insertar usuario

//Ajax de actualizar usuario
function actualizarU() {
    $.ajax({method: "POST",
        url: "../ServletModiUsu",
        beforeSend: function () {
            //Validar usuario  modal
            function ValidarCorreo(correo) {
                var tstCorreo = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-]+)\.)+([a-zA-Z0-9{2,4}])+$/;
                return tstCorreo.test(correo);
            }
            function ValidarNom(nombre) {
                var tstnom = /^([a-zA-Z])/;
                return tstnom.test(nombre);
            }
//variables 
            var nombre = document.getElementById("nomU1").value;
            var documento = document.getElementById("docu1").value;
            var correo = document.getElementById("correo1").value;
            if (nombre === null || nombre.length === 0) {
                $('#vldrNombre1').html('Debe ingresar nombre.').slideDown(500);
                $('#lblnm1').css("color", "#f44336");
                $('#nomU1').focus();
                return  false;
                // e.preventDefault();//evitar accion por defecto por ejemplo enlaces
            } else if (ValidarNom(nombre) === false) {
                $('#vldrNombre1').html('El nombre debe comenzar con letras').slideDown(500);
                $('#lblnm1').css("color", "#f44336");
                $('#nomU1').focus();
                return  false;
            } else if (nombre.length > 69) {
                $('#vldrNombre1').html('El nombre es muy largo.').slideDown(500);
                $('#lblnm1').css("color", "#f44336");
                $('#nomU1').focus();
                return  false;
            } else {
                $('#lblnm1').css("color", "#009688");
                $('#vldrNombre1').html('').slideUp(30);
            }
            if (documento === "") {
                $('#vldrDocumento1').html('Debe ingresar numero de documento.').slideDown(500);
                $('#lbldocu1').css("color", "#f44336");
                $('#docu1').focus();
                return  false;
            } else if (documento.length > 11) {
                $('#vldrDocumento1').html('El numero de documento es muy largo.').slideDown(500);
                $('#lbldocu1').css("color", "#f44336");
                $('#docu1').focus();
                return  false;
            } else {
                $('#lbldocu1').css("color", "#009688");
                $('#vldrDocumento1').html('').slideUp(300);
            }
            if (isNaN(documento)) {
                $('#vldrDocumento1').html('El documento ingresado no es un numero.').slideDown(500);
                $('#lbldocu1').css("color", "#f44336");
                $('#docu1').focus();
                return  false;
            } else {
                $('#lbldocu1').css("color", "#009688");
                $('#vldrDocumento1').html('').slideUp(300);
            }
            if (ValidarCorreo(correo) === false) {
                $('#vldrCorreo1').html('Debe ingresar un correo Valido.').slideDown(500);
                $('#lblcorreo1').css("color", "#f44336");
                $('#correo1').focus();
                return  false;
            } else if (correo.length > 80) {
                $('#vldrCorreo1').html('El correo es muy largo.').slideDown(500);
                $('#lblcorreo1').css("color", "#f44336");
                $('#correo1').focus();
                return  false;
            } else {
                $('#vldrCorreo1').html('').slideUp(300);
                $('#lblcorreo1').css("color", "#009688");
            }
            if (nombre !== null && documento !== null && correo !== null) {
                //cerrrar modal
                $("#modal1").closeModal();
                return true;
            }//finalizar funcion validar datos de usuario
        },
        data: {nombre: $("#nomU1").val(),
            documento: $("#docu1").val(),
            correo: $("#correo1").val(),
            id: $("#id1").val()
        }
    })
            .done(function (msj) {
                tabla();
                if (msj === "Usuario actualizado correctamente.") {
                    swal({title: "Resultado:", text: msj, timer: 2000, type: "success", showConfirmButton: false, allowEscapeKey: false});
                    //$("tabUsu").load('tabla.jsp');
                } else {
                    swal({title: "Resultado:", text: msj, timer: 3000, type: "error", showConfirmButton: false, allowEscapeKey: false});
                }

            });
}//fin funcion actualizar usuario

//Abrir modal actualizar usuario
function abrirModalU() {
    $(".acUsu").click(function () {
        $.ajax({method: "POST",
            url: "../ServletModUsu",
            data: {id: $(this).val()}
        })
                .done(function (msj) {
                    $('#modal1').openModal({
                        dismissible: false,
                        opacity: .5,
                        in_duration: 300,
                        out_duration: 200,
                        starting_top: '4%',
                        ending_top: '10%'
                    });
                    $("#modalUsuario").html(msj);
                });
    });
}//fin funcion abrir modal usaurio

//validar enter del modal  actualizar usuario
function intromodalU(evento) {
    if (evento.keyCode === 13) {
        actualizarU();
    }
}//fin funcion intro modal actulizar usuario

// Ajax de eliminar usuario
function eliminarU() {
    $(".eliminarU").click(function () {
        var documento = $(this).val();
        swal({title: "Esta seguro?", text: "Desea eliminar el usuario", type: "warning", allowEscapeKey: false, showCancelButton: true, cancelButtonText: "Cancelar", closeOnCancel: false, confirmButtonColor: "#238276", confirmButtonText: "Aceptar", closeOnConfirm: false, animation: "slide-from-top"}, function (isConfirm) {
            if (isConfirm) {
                $.ajax({method: "POST",
                    url: "../ServletEliU",
                    data: {documento: documento
                    }, error: function (respuesta) {
                        swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar"});

                    }
                })
                        .done(function (msj) {

                            if (msj === "Usuario eliminado correctamente.") {
                                swal({title: "Eliminado", type: "success", text: "Usuario eliminado correctamente.", allowEscapeKey: false, confirmButtonText: "Aceptar", confirmButtonColor: "#238276"});
                                tabla();
                            } else {
                                swal({title: "Error", type: "error", text: "Error al eliminar usuario.", allowEscapeKey: false, confirmButtonText: "Aceptar", confirmButtonColor: "#238276"});
                            }
                        });
            } else {
                swal({title: "Cancelado", type: "error", text: "Operacion cancelada.", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
            }
        });
    });
}//fin funcion eliminar usuario

//////////////////////////////////// MODULO OPCIÓN /////////////////////////////

//tabla Opcion
function tablaOpcion() {
    $.ajax({method: "POST",
        url: "../ServletTabOpcion",
        error: function (respuesta) {
            swal({title: "Advertencia", type: "warning", text: respuesta.responseText, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
        }

    }).done(function (msj) {
//mostrar datos de la tabla
        $("#tabOp").html(msj);
        //paginacion y buscar
//        $('.tblOpcion').addClass("grey lighten-2");

        $('.tblOpcion').dataTable({
            dom: 'Bfrtip',
            buttons: [
                {extend: 'print',
                    message: 'Reporte de opciones creado desde HuellVot (Version1.0).',
                    text: 'Imprimir', exportOptions: {
                        columns: [0, 1, 2]
                    }}
                , {extend: 'copy',
                    text: 'Copiar', exportOptions: {
                        columns: [0, 1, 2]
                    }
                },
//                {extend: 'csv',
//            text: 'Exportar a csv' ,exportOptions: {
//                    columns: [ 0, 1, 2 ]
//                } },
                {extend: 'excel',
                    text: 'Exportar a excel', exportOptions: {
                        columns: [0, 1, 2]
                    }},
                {extend: 'pdf',
                    message: 'Reporte de opciones creado desde HuellVot (Version1.0).',
                    text: 'Exportar a PDF', exportOptions: {
                        columns: [0, 1, 2]
                    }}

            ],
            "language": {
                "decimal": "",
                "emptyTable": "No hay registros en la tabla",
                "infoPostFix": "",
                "thousands": ",",
                "loadingRecords": "Loading...",
                "processing": "Processing...",
                "infoFiltered": "(Filtrado de _MAX_ registros)",
                "zeroRecords": "No se encontraron registros",
                "lengthMenu": "",
                "search": "Buscar:",
                "info": "Registros del _START_ al _END_  de _TOTAL_ ",
                "infoEmpty": "Mostrando 0 a 0 de 0 resultados",
                "paginate": {
                    "next": "Siguiente",
                    "previous": "Anterior"
                },
                buttons: {
                    copyTitle: 'Listo',
                    copySuccess: {
                        _: 'Se han copiados %d registros al portapepeles',
                        1: 'Se ha copiado 1 registro al portapapeles'
                    }
                }
            }});
    });
}//fin funcion tabla opcion

//Ajax insertar Opcion
function insertarO() {
    $.ajax({method: "POST",
        url: "../ServletOpcion",
        beforeSend: function () {
            //variables de  opción
            var nombreO = document.getElementById("nomO").value;
            var catO = document.getElementById("catO").value;
            var desO = document.getElementById("descriO").value;
            if (nombreO === null || nombreO.length === 0) {
                $('#vldrNombreO').html('Debe ingresar nombre de opción.').slideDown(500);
                $('#lblnomO').css("color", "#f57c00");
                $('#nomO').focus();
                return  false;
            } else if (nombreO.length > 120) {
                $('#vldrNombreO').html('El nombre  de la opción es muy largo.').slideDown(500);
                $('#lblnomO').css("color", "#f57c00");
                $('#nomO').focus();
                return  false;
            } else {
                $('#lblnomO').css("color", "#009688");
                $('#vldrNombreO').html('').slideUp(30);
            }
            if (catO === null || catO === "") {
                $('#vldrcatO').html('Debe seleccionar categoria.').slideDown(500);
                $('#catO').focus();
                $('#lblcatO').css("color", "#f57c00");
                return false;
            } else {
                $('#vldrcatO').html('').slideUp(30);
                $('#lblcatO').css("color", "#009688");
            }
            if (desO === null || desO.length === 0) {
                $('#vldrdescO').html('Debe ingresar una descripción.').slideDown(500);
                $('#lbldesO').css("color", "#f57c00");
                $('#descriO').focus();
                return false;
            } else {
                $('#lbldesO').css("color", "#009688");
                $('#vldrdescO').html('').slideUp(30);
            }

        }, data: {nomO: $("#nomO").val(),
            cateO: $("#catO").val(),
            descriO: $("#descriO").val()

        }, error: function (respuesta) {
            swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
            $("#nomO").val("");
            $("#catO").val("");
            $("#descriO").val("");
        },
        success: function () {
            swal({title: "Resultado:", text: "Opcion registrada.", timer: 4000, type: "success", showConfirmButton: false, allowEscapeKey: false});
            tablaOpcion();
        }
    }).done(function () {
        $("#nomO").val("");
        $("#catO").val("");
        $("#descriO").val("");
    });
}//fin funcion insertar opcion

//ajax modificar Opcion
function introinsertO() {
}//fin funcion intro insertar opcion

//Ajax de actualizar Opción
function actualizarO() {
    $.ajax({method: "POST",
        url: "ServletModiOpcion",
        beforeSend: function () {
//variables de  opción
            var nombreO = document.getElementById("nomO1").value;
            var catO = document.getElementById("catO1").value;
            var desO = document.getElementById("descriO1").value;
            if (nombreO === null || nombreO.length === 0) {
                $('#vldrNombreO1').html('Debe ingresar nombre de opción.').slideDown(500);
                $('#lblnomO1').css("color", "#f57c00");
                $('#nomO1').focus();
                return  false;
            } else if (nombreO.length > 120) {
                $('#vldrNombreO1').html('El nombre  de la opción es muy largo.').slideDown(500);
                $('#lblnomO1').css("color", "#f57c00");
                $('#nomO1').focus();
                return  false;
            } else {
                $('#lblnomO1').css("color", "#009688");
                $('#vldrNombreO1').html('').slideUp(30);
            }
            if (catO === null || catO === "") {
                $('#vldrcatO1').html('Debe seleccionar categoria.').slideDown(500);
                $('#catO1').focus();
                $('#lblcatO1').css("color", "#f57c00");
                return false;
            } else {
                $('#vldrcatO1').html('').slideUp(30);
                $('#lblcatO1').css("color", "#009688");
            }
            if (desO === null || desO.length === 0) {
                $('#vldrdescO1').html('Debe ingresar una descripción.').slideDown(500);
                $('#lbldesO1').css("color", "#f57c00");
                $('#descriO1').focus();
                return false;
            } else {
                $('#lbldesO1').css("color", "#009688");
                $('#vldrdescO1').html('').slideUp(30);
            }
            if (nombreO !== null && catO !== null && desO !== null) {
                //cerrrar modal
                $("#modal2").closeModal();
                return true;
            }//finalizar funcion validar datos de usuario
        }, data: {idO: $("#idO1").val(),
            nomO: $("#nomO1").val(),
            cateO: $("#catO1").val(),
            descriO: $("#descriO1").val()

        }, error: function (respuesta) {
            swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
            $("#nomO1").val("");
            $("#catO1").val("");
            $("#descriO1").val("");
        },
        success: function () {
            swal({title: "Resultado:", text: "Opcion registrada.", timer: 4000, type: "success", showConfirmButton: false, allowEscapeKey: false});
            tablaOpcion();
        }
    }).done(function () {
        $("#nomO1").val("");
        $("#catO1").val("");
        $("#descriO1").val("");
    });
}//fin funcion actualizar opcion

//Abrir modal actualizar  Opción
function abrirModalO() {
    $(".acOpc").click(function () {
        $.ajax({method: "POST",
            url: "../ServletModalOpc",
            data: {id: $(this).val()}
        })
                .done(function (msj) {
                    $('#modal2').openModal({
                        dismissible: false,
                        opacity: .5,
                        in_duration: 300,
                        out_duration: 200,
                        starting_top: '4%',
                        ending_top: '10%'
                    });
                    $("#modalOpcion").html(msj);
                });
    });
}//fin funcion abrir modal opcion

//validar enter del modal  actualizar Opción
function intromodalO() {}//fin funcion intro modal opcion

// Ajax de eliminar  Opción
function eliminarO() {
    $(".eliminarO").click(function () {
        var id = $(this).val();
        swal({title: "Esta seguro?", text: "Desea eliminar la Opción", type: "warning", allowEscapeKey: false, showCancelButton: true, cancelButtonText: "Cancelar", closeOnCancel: false, confirmButtonColor: "#238276", confirmButtonText: "Aceptar", closeOnConfirm: false, animation: "slide-from-top"}, function (isConfirm) {
            if (isConfirm) {
                $.ajax({method: "POST",
                    url: "../ServletEliOpci",
                    data: {idO: id
                    }, error: function (respuesta) {
                        swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
                    }
                })
                        .done(function (msj) {
                            if (msj === "Opción eliminada correctamente.") {
                                swal({title: "Eliminada", type: "success", text: "Opción eliminada correctamente.", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
                                tablaOpcion();
                            } else {
                                swal({title: "Error", type: "error", text: "Error al eliminar Opción.", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
                            }
                        });
            } else {
                swal({title: "Cancelado", type: "error", text: "Operacion cancelada.", allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
            }
        });
    });
}//fin funcion eliminar opcion 


//mostrar y ocultar modulos 
$(document).ready(function () {
    cache:false;
    $(".formUsuario").hide();
    $(".formOpcion").hide();
    $(".formReporte").hide();
    $(".Inicio").show();
    
    //mostrar inicio
    $(".logo").click(function () {
        $(".formUsuario").hide();
        $(".formOpcion").hide();
        $(".formReporte").hide();
        $(".Inicio").show();
    });
    //mostrar modulo Usuario
    $(".showUser").click(function () {
        $("#mnU").css("background-color","#238276");
        $("#mnO").css("background-color","#00796b");
        $("#mnR").css("background-color","#00796b");
        $(".formUsuario").show();
        $(".formOpcion").hide();
        $(".formReporte").hide();
        $(".Inicio").hide();
        tabla();
    });
//mostrar modulo opción
    $(".showOp").click(function () {
        $("#mnO").css("background-color","#238276");
        $("#mnR").css("background-color","#00796b");
        $("#mnU").css("background-color","#00796b");
        $(".formUsuario").hide();
        $(".formOpcion").show();
        $(".formReporte").hide();
        $(".Inicio").hide();
        tablaOpcion();
    });
    //mostrar modulo reportes
    $(".showRep").click(function () {
        $("#mnR").css("background-color","#238276");
        $("#mnO").css("background-color","#00796b");
        $("#mnU").css("background-color","#00796b");
        
        $(".formUsuario").hide();
        $(".formOpcion").hide();
        $(".formReporte").show();
        $(".Inicio").hide();
        $("#canvas-container").hide();
    });
    //mostrar estadisticas
    $("#btnEstadisticas").click(function () {
        $("#canvas-container").slideToggle("slow");
    });
    //////////////////////////////////// MODULO VOTACIÓN /////////////////////////////

    $("#Votar").click(function () {
        var idO = $("input[type='radio']:checked");
        $.ajax({method: "POST",
            url: "../ServletVoto",
            beforeSend: function () {
                if (idO.length === 0) {
                    swal({title: "Error", type: "error", text: "Debe seleccionar una opción", timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});
                }
            }, success: function () {
                swal({title: "Resultado:", text: "Votación exitosa, Gracias por votar.", type: "success", confirmButtonText: "Aceptar",confirmButtonColor: "#238276", allowEscapeKey: false}, function (isConfirm) {
                    if (isConfirm) {
                        window.location.assign('index.jsp');
                        window.open("Administrar.jsp");
                    }
                });
            },
            error: function (respuesta) {
                swal({title: "Error", type: "error", text: respuesta.responseText, timer: 4000, allowEscapeKey: false, confirmButtonText: "Aceptar",confirmButtonColor: "#238276"});

            },
            data: {Opcion: idO.val()
            }
        });
//        var h = window.open("Administrar.jsp");

    });


    //MODULO DE REPORTES
    //Abrir el modal de mensaje reporte
    $("#msjReport").click(function () {
        $('#modalReport').openModal({
            dismissible: false,
            opacity: .5,
            in_duration: 300,
            out_duration: 200,
            starting_top: '4%',
            ending_top: '10%'
        });
    });
    $("#rep").click(function () {
        if (!($('#trep').val()).isEmptyObject()) {
            $('#modalReport').closeModal();
        }
    });
    //Estadisticas de género
    $("#btnEstadisticas").click(function () {
        $.ajax({method: "POST",
            url: "../ServletEstadisticasGenero"
        })
                .done(function (msj) {

                    var object = eval(msj);
                    var x = object[0].v;
                    var y = object[0].y;
                    var datos = {
                        type: "pie",
                        data: {
                            datasets: [{
                                    data: [
                                        x, y
                                    ],
                                    backgroundColor: [
                                        "#F7464A",
                                        "#46BFBD"
                                    ]
                                }],
                            labels: [
                                "Femenino",
                                "Masculino"
                            ]
                        },
                        options: {
                            responsive: true
                        }
                    };
                    var canvas = document.getElementById('chart').getContext('2d');
                    window.pie = new Chart(canvas, datos);
                });
    });
    //generar estadisticas
//    var datos = {
//        type: "pie",
//        data: {
//            datasets: [{
//                    data: [
//                        5,
//                        10,
//                        40,
//                        12,
//                        23
//                    ],
//                    backgroundColor: [
//                        "#F7464A",
//                        "#46BFBD",
//                        "#FDB45C",
//                        "#949FB1",
//                        "#4D5360"
//                    ]
//                }],
//            labels: [
//                "Datos 1",
//                "Datos 2",
//                "Datos 3",
//                "Datos 4",
//                "Datos 5"
//            ]
//        },
//        options: {
//            responsive: true
//        }
//    };
//    var canvas = document.getElementById('chart').getContext('2d');
//    window.pie = new Chart(canvas, datos);

    //tipo cel
    $(".button-collapse").sideNav();
    $('select').material_select();

});





