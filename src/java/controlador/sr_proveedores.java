package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Proveedores;

@WebServlet(name = "sr_proveedores", urlPatterns = {"/sr_proveedores"})
public class sr_proveedores extends HttpServlet {

    Proveedores proveedores;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Instancia del modelo 'Proveedores'
            proveedores = new Proveedores(
                    Integer.parseInt(request.getParameter("txt_id")), // ID del proveedor
                    request.getParameter("txt_nombre"), // Nombre del proveedor
                    request.getParameter("txt_telefono"),
                    request.getParameter("txt_direccion"),
                    request.getParameter("txt_nit"),
                    request.getParameter("txt_estado") // Estado como cadena
            );

            // Acción al presionar "Agregar"
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                if (proveedores.agregar() > 0) {
                    response.sendRedirect("proveedores.jsp"); // Redirige a la página principal tras agregar
                } else {
                    out.println("<h1>Error: No se pudo agregar el proveedor.</h1>");
                    out.println("<a href='proveedores.jsp'>Regresar</a>");
                }
            }

            // Acción al presionar "Modificar"
            if ("modificar".equals(request.getParameter("btn_modificar"))) {
                if (proveedores.modificar() > 0) {
                    response.sendRedirect("proveedores.jsp"); // Redirige a la página principal tras modificar
                } else {
                    out.println("<h1>Error: No se pudo modificar el proveedor.</h1>");
                    out.println("<a href='proveedores.jsp'>Regresar</a>");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();  // En producción, redirigir a una página de error o registrar en logs
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar CRUD de Proveedores";
    }
}
