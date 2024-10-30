<%@ page import="modelo.Clientes" %>
<%@ page import="javax.swing.table.DefaultTableModel" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="master.jsp" />

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mantenimiento de Clientes</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="card">
                    <h3 class="card-header text-center">Mantenimiento de Clientes</h3>
                    <div class="card-body">
                        <form action="sr_clientes" method="post" class="form-group">
                            <!-- Campos de formulario -->
                            <div class="row">
                                <div class="col-md-6">
                                    <label for="lbl_id"><b>ID Cliente:</b></label>
                                    <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label for="lbl_estado"><b>Estado:</b></label>
                                    <select name="txt_estado" id="txt_estado" class="form-control">
                                        <option value="1">Activo</option>
                                        <option value="0">Inactivo</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6">
                                    <label for="lbl_nombres"><b>Nombres:</b></label>
                                    <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Juan Alberto" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="lbl_apellidos"><b>Apellidos:</b></label>
                                    <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Pérez Granados" required>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6">
                                    <label for="lbl_telefono"><b>Teléfono:</b></label>
                                    <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="2555-1234" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="lbl_direccion"><b>Dirección:</b></label>
                                    <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Calle 123" required>
                                </div>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6">
                                    <label for="lbl_nit"><b>NIT:</b></label>
                                    <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="123456789" required>
                                </div>
                            </div>
                            <div class="mt-4 text-center">
                                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-success btn-custom btn-lg">Agregar</button>
                                <button name="btn_actualizar" id="btn_actualizar" value="modificar" class="btn btn-outline-info btn-custom btn-lg">Actualizar</button>
                                <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-outline-danger btn-custom btn-lg" onclick="return confirm('¿Desea Eliminar?')">Eliminar</button>
                                <button type="button" class="btn btn-outline-warning btn-custom btn-lg" onclick="limpiar()">Limpiar</button>
                            </div>
                        </form>
                        <!-- Tabla de Clientes -->
                        <table class="table table-hover mt-3">
                            <thead class="table table-hover">
                                <tr>
                                    <th>ID Cliente</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Teléfono</th>
                                    <th>Dirección</th>
                                    <th>NIT</th>
                                    <th>Estado</th>
                                </tr>
                            </thead>
                            <tbody id="tbl_clientes">
                                <%
                                    try {
                                        Clientes clientes = new Clientes();
                                        DefaultTableModel tabla = clientes.leer();
                                        if (tabla != null && tabla.getRowCount() > 0) {
                                            for (int t = 0; t < tabla.getRowCount(); t++) {
                                                out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "'>");
                                                out.println("<td>" + tabla.getValueAt(t, 0) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 2) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 3) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 4) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 5) + "</td>");
                                                out.println("<td>" + (tabla.getValueAt(t, 6).equals("1") ? "Activo" : "Inactivo") + "</td>");
                                                out.println("</tr>");
                                            }
                                        } else {
                                            out.println("<tr><td colspan='7' class='text-center'>No hay datos disponibles</td></tr>");
                                        }
                                    } catch (Exception e) {
                                        out.println("<tr><td colspan='7' class='text-center'>Error al cargar datos</td></tr>");
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/clientes.js"></script>

</body>
</html>
