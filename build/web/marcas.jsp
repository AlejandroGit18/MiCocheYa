<%@page import="modelo.Marca" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="master.jsp" />

<!DOCTYPE html>
<html>
    <body>
        <div class="d-flex justify-content-center align-items-center mt-3">
            <div class="col-md-9 ml-3">
                <div class="card">
                    <h3 class="card-header text-center">Mantenimiento Puestos</h3>
                    <div class="card-body">

                        <div class="container">
                            <form action="${pageContext.request.contextPath}/sr_marcas" method="post" class="form-group" enctype="multipart/form-data">
                                <!-- Formulario de CRUD -->
                                <label for="lbl_id"><b>ID</b></label>
                                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                                <label for="lbl_marca"><b>Nombre de la marca</b></label>
                                <input type="text" name="txt_marca" id="txt_marca" class="form-control" placeholder="Ejemplo: Toyota" required>
                                <label for="lbl_imagen"><b>Logotipo</b></label>
                                <input type="file" name="txt_imagen" id="txt_imagen" class="form-control">
                                <label for="lbl_estado"><b>Estado</b></label>
                                <select name="txt_estado" id="txt_estado" class="form-control" required>
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                                
                                <br>
                                <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-outline-success btn-lg">Agregar</button>
                                <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-outline-info btn-lg">Modificar</button>
                                <button type="button" class="btn btn-outline-warning btn-lg" onclick="limpiar()">Limpiar</button>
                            </form>

                            <!-- Tabla para mostrar datos -->
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre de la marca</th>
                                        <th>Logotipo</th>
                                        <th>Estado</th>
                                    </tr>
                                </thead>
                                <tbody id="tbl_marcas">
                                    <%
                                        Marca marca = new Marca();
                                        DefaultTableModel tabla = marca.leer();

                                        if (tabla != null && tabla.getRowCount() > 0) {
                                            for (int t = 0; t < tabla.getRowCount(); t++) {
                                                out.println("<tr data-id='" + tabla.getValueAt(t, 0) + "'>");
                                                out.println("<td>" + tabla.getValueAt(t, 0) + "</td>");
                                                out.println("<td>" + tabla.getValueAt(t, 1) + "</td>");

                                                String imgPath = (tabla.getValueAt(t, 2) != null && !tabla.getValueAt(t, 2).toString().isEmpty())
                                                        ? "${pageContext.request.contextPath}/images/" + tabla.getValueAt(t, 2).toString()
                                                        : "${pageContext.request.contextPath}/images/default.jpg";

                                                String imgBase64 = (tabla.getValueAt(t, 3) != null) ? tabla.getValueAt(t, 3).toString() : "";

                                                if (!imgBase64.isEmpty()) {
                                                    out.println("<td><img src='data:image/png;base64," + imgBase64 + "' alt='Logotipo' style='width: 100px; height: auto;'></td>");
                                                } else {
                                                    out.println("<td><img src='" + imgPath + "' alt='Logotipo' style='width: 100px; height: auto;'></td>");
                                                }

                                                out.println("<td>" + (tabla.getValueAt(t, 4) != null ? tabla.getValueAt(t, 4) : "No especificado") + "</td>");
                                                out.println("</tr>");
                                            }
                                        } else {
                                            out.println("<tr><td colspan='4' style='text-align: center;'>No hay datos disponibles</td></tr>");
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
        <script src="${pageContext.request.contextPath}/js/marca.js"></script>
    </body>
</html>
