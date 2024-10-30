/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function limpiar() {
    $("#txt_id").val(0);
    $("#txt_nombre").val('');
    $("#txt_telefono").val('');
    $("#txt_direccion").val('');
    $("#txt_nit").val('');
    $("#txt_estado").prop('selectedIndex', 0); // Restablecer el select al primer valor
    $("#btn_modificar").prop('disabled', true);  // Deshabilitar botón Modificar
}

$('#tbl_proveedores').on('click', 'tr', function() {
    var id = $(this).data('id'); // Obtener el ID de la fila seleccionada
    var nombre = $(this).children().eq(1).html(); // Obtener el nombre de la segunda columna
    var telefono = $(this).children().eq(2).html(); // Obtener el nombre de la tercera columna
    var direccion = $(this).children().eq(3).html();
    var nit = $(this).children().eq(4).html();
    var estado = $(this).children().eq(5).html(); // Obtener el nombre de la cuarta columna

    $("#txt_id").val(id);
    $("#txt_nombre").val(nombre);
    $("#txt_telefono").val(telefono);
    $("#txt_direccion").val(direccion);
    $("#txt_nit").val(nit);
    // Para txt_estado, aseguramos que el valor coincida con el estado (en caso de ser texto como 'Activo' o 'Inactivo')
    $("#txt_estado").val(estado === 'Activo' ? '1' : '0');
    
    // Activar botones Modificar y Eliminar
    $("#btn_modificar").prop('disabled', false);

    // Resaltar la fila seleccionada
    $(this).addClass('table-active').siblings().removeClass('table-active');
});
