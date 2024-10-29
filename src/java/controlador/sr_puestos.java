package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Puesto;

public class sr_puestos extends HttpServlet {

    Puesto puesto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Instancia del modelo 'Puesto'
            puesto = new Puesto(
                Integer.valueOf(request.getParameter("txt_id")), // ID del puesto
                request.getParameter("txt_nombre") // Nombre del puesto
            );

            // Acción al presionar "Agregar"
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                if (puesto.agregar() > 0) {
                    response.sendRedirect("puestos.jsp"); // Redirige a la página principal tras agregar
                } else {
                    out.println("<h1>Error: No se pudo agregar el puesto.</h1>");
                    out.println("<a href='puestos.jsp'>Regresar</a>");
                }
            }

            // Acción al presionar "Modificar"
            if ("modificar".equals(request.getParameter("btn_modificar"))) {
                if (puesto.modificar() > 0) {
                    response.sendRedirect("puestos.jsp"); // Redirige a la página principal tras modificar
                } else {
                    out.println("<h1>Error: No se pudo modificar el puesto.</h1>");
                    out.println("<a href='puestos.jsp'>Regresar</a>");
                }
            }

            // Acción al presionar "Eliminar"
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (puesto.eliminar() > 0) {
                    response.sendRedirect("puestos.jsp"); // Redirige a la página principal tras eliminar
                } else {
                    out.println("<h1>Error: No se pudo eliminar el puesto.</h1>");
                    out.println("<a href='puestos.jsp'>Regresar</a>");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
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
        return "Servlet para gestionar CRUD de Puestos";
    }
}
