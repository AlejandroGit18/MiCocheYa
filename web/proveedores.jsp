<%@page import="modelo.Proveedores" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="master.jsp" />

<!DOCTYPE html>
<html>

    <body>
        <div class="d-flex justify-content-center align-items-center mt-3">
            <div class="col-md-9 ml-3">
                <div class="card">
                    <h3 class="card-header text-center">Mantenimiento Proveedores</h3>
                    <div class="card-body">
                        <div class="container">
                            <!-- Formulario para manejar el CRUD -->
                            <form action="${pageContext.request.contextPath}/sr_proveedores" method="post" class="form-group">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label for="lbl_id"><b>ID</b></label>
                                        <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>

                                        <label for="lbl_nombre"><b>Nombre del proveedor</b></label>
                                        <input type="text" name="txt_nombre" id="txt_nombre" class="form-control" placeholder="Ejemplo: Oscar Martinez" required>

                                        <label for="lbl_telefono"><b>Teléfono</b></label>
                                        <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 47849814" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="lbl_direccion"><b>Dirección</b></label>
                                        <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: Zona 2 de Guatemala" required>

                                        <label for="lbl_nit"><b>NIT</b></label>
                                        <input type="number" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 154263" required>

                                        <label for="lbl_estado"><b>Estado</b></label>
                                        <select name="txt_estado" id="txt_estado" class="form-control" required>
                                            <option value="1">Activo</option>
                                            <option value="0">Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <br>
                                <div class="text-center">
                                    <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-success btn-lg">Agregar</button>
                                    <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-outline-info btn-lg">Modificar</button>
                                    <button type="button" class="btn btn-outline-warning btn-lg" onclick="limpiar()">Limpiar</button>
                                </div>
                            </form>


                            <!-- Tabla para mostrar los datos de los puestos -->
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                        <th>Telefono</th>
                                        <th>Direccion</th>
                                        <th>Nit</th>
                                        <th>Estado</th>
                                    </tr>
                                </thead>
                                <tbody id="tbl_proveedores">
                                    <%
                                        Proveedores proveedores = new Proveedores();
                                        DefaultTableModel tabla = proveedores.leer(); // Llamada a la función que lee la tabla TBL_PUESTOS

                                        // Verificamos si la tabla tiene datos
                                        if (tabla != null && tabla.getRowCount() > 0) {
                                            for (int t = 0; t < tabla.getRowCount(); t++) {
                                                out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "'>");
                                                out.println("<td>" + tabla.getValueAt(t, 0) + "</td>"); // ID en la columna 0
                                                out.println("<td>" + tabla.getValueAt(t, 1) + "</td>"); // Nombre en la columna 1
                                                out.println("<td>" + tabla.getValueAt(t, 2) + "</td>"); // Telefono en la columna 2
                                                out.println("<td>" + tabla.getValueAt(t, 3) + "</td>"); // Direccion en la columna 3
                                                out.println("<td>" + tabla.getValueAt(t, 4) + "</td>"); // Nit en la columna 4
                                                out.println("<td>" + tabla.getValueAt(t, 5) + "</td>"); // Estado en la columna 4
                                                out.println("</tr>");
                                            }
                                        } else {
                                            // Si no hay datos, mostramos un mensaje
                                            out.println("<tr>");
                                            out.println("<td colspan='6' style='text-align: center;'>No hay datos disponibles</td>");
                                            out.println("</tr>");
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


        <script src="${pageContext.request.contextPath}/js/proveedores.js"></script>

    </body>
</html>
