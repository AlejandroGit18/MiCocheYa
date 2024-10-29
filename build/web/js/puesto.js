function limpiar() {
    $("#txt_id").val(0);
    $("#txt_nombre").val('');
    $("#btn_modificar").prop('disabled', true);  // Deshabilitar botón Modificar
    $("#btn_eliminar").prop('disabled', true);   // Deshabilitar botón Eliminar
}

$('#tbl_puestos').on('click', 'tr', function() {
    var id = $(this).data('id'); // Obtener el ID de la fila seleccionada
    var nombre = $(this).children().eq(1).html(); // Obtener el nombre de la segunda columna

    $("#txt_id").val(id);
    $("#txt_nombre").val(nombre);
    
    // Activar botones Modificar y Eliminar
    $("#btn_modificar").prop('disabled', false);
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
                    document.getElementById('form_puesto').submit();
                }
            });
        }

        // Función para mostrar alertas de éxito con SweetAlert al agregar/modificar
        function mostrarAlerta(event, accion) {
            event.preventDefault(); // Evita el envío inmediato del formulario

            Swal.fire({
                title: '¿Confirmar ' + accion + '?',
                text: "¿Desea " + accion + " el puesto?",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, ' + accion,
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Enviar el formulario después de la confirmación
                    document.getElementById('form_puesto').submit();
                }
            });
        }

        // Función para limpiar el formulario
        function limpiarFormulario() {
            document.getElementById("form_puesto").reset();
            Swal.fire({
                title: 'Formulario Limpiado',
                text: 'Todos los campos fueron borrados',
                icon: 'info',
                confirmButtonText: 'Aceptar'
            });
        }