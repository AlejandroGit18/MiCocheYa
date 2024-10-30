/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* global Swal */

// Función para limpiar el formulario de clientes
function limpiar() {
    $("#txt_id").val(0);
    $("#txt_nombres").val('');
    $("#txt_apellidos").val('');
    $("#txt_telefono").val('');
    $("#txt_direccion").val('');
    $("#txt_nit").val('');
    $("#txt_estado").val('Activo'); // o el valor predeterminado
    $("#btn_actualizar").prop('disabled', true);  // Deshabilitar botón Modificar
    $("#btn_eliminar").prop('disabled', true);   // Deshabilitar botón Eliminar
}

// Evento para seleccionar una fila de la tabla de clientes
$('#tbl_clientes').on('click', 'tr', function() {
    var id = $(this).data('id'); // Obtener el ID de la fila seleccionada
    var nombres = $(this).children().eq(1).html(); // Obtener el nombre de la segunda columna
    var apellidos = $(this).children().eq(2).html(); // Obtener el apellido de la tercera columna
    var telefono = $(this).children().eq(3).html(); // Obtener el teléfono de la cuarta columna
    var direccion = $(this).children().eq(4).html(); // Obtener la dirección de la quinta columna
    var nit = $(this).children().eq(5).html(); // Obtener el NIT de la sexta columna
    var estado = $(this).children().eq(6).html(); // Obtener el estado de la séptima columna

    // Asignar valores al formulario
    $("#txt_id").val(id);
    $("#txt_nombres").val(nombres);
    $("#txt_apellidos").val(apellidos);
    $("#txt_telefono").val(telefono);
    $("#txt_direccion").val(direccion);
    $("#txt_nit").val(nit);
    $("#txt_estado").val(estado);

    // Activar botones Modificar y Eliminar
    $("#btn_actualizar").prop('disabled', false);
    $("#btn_eliminar").prop('disabled', false);

    // Resaltar la fila seleccionada
    $(this).addClass('table-active').siblings().removeClass('table-active');
});

// Función para confirmar eliminación usando SweetAlert
function confirmarEliminacion(event) {
    event.preventDefault(); // Evita el envío del formulario inmediatamente

    Swal.fire({
        title: '¿Está seguro?',
        text: "¡No podrá revertir esta acción!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminarlo',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Enviar el formulario si se confirma la eliminación
            document.getElementById('form_cliente').submit();
        }
    });
}

// Función para mostrar alertas de éxito con SweetAlert al agregar/modificar
function mostrarAlerta(event, accion) {
    event.preventDefault(); // Evita el envío inmediato del formulario

    Swal.fire({
        title: '¿Confirmar ' + accion + '?',
        text: "¿Desea " + accion + " el cliente?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, ' + accion,
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Enviar el formulario después de la confirmación
            document.getElementById('form_cliente').submit();
        }
    });
}

// Función para limpiar el formulario con alerta
function limpiarFormulario() {
    document.getElementById("form_cliente").reset();
    Swal.fire({
        title: 'Formulario Limpiado',
        text: 'Todos los campos fueron borrados',
        icon: 'info',
        confirmButtonText: 'Aceptar'
    });
}
