/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Clientes;

/**
 *
 * @author Andre
 */
@WebServlet(name = "sr_clientes", urlPatterns = {"/sr_clientes"})
public class sr_clientes extends HttpServlet {

Clientes cliente;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Instancia del modelo 'Cliente'
            cliente = new Clientes(
                Integer.parseInt(request.getParameter("txt_id")), // ID del cliente
                request.getParameter("txt_nombres"), // Nombres
                request.getParameter("txt_apellidos"), // Apellidos
                request.getParameter("txt_telefono"), // Teléfono
                request.getParameter("txt_direccion"), // Dirección
                request.getParameter("txt_nit"), // NIT
                Integer.parseInt(request.getParameter("txt_estado")), // Estado como entero
                request.getParameter("txt_usuario_creacion"), // Usuario que creó el registro
                request.getParameter("txt_usuario_modificacion") // Usuario que modificó el registro
                    
            );

            // Acción al presionar "Agregar"
            if ("agregar".equals(request.getParameter("btn_agregar"))) {
                if (cliente.agregar() > 0) {
                    response.sendRedirect("clientes.jsp"); // Redirige a la página principal tras agregar
                } else {
                    out.println("<h1>Error: No se pudo agregar el cliente.</h1>");
                    out.println("<a href='clientes.jsp'>Regresar</a>");
                }
            }

            // Acción al presionar "Modificar"
            if ("modificar".equals(request.getParameter("btn_actualizar"))) {
                if (cliente.modificar() > 0) {
                    response.sendRedirect("clientes.jsp"); // Redirige a la página principal tras modificar
                } else {
                    out.println("<h1>Error: No se pudo modificar el cliente.</h1>");
                    out.println("<a href='clientes.jsp'>Regresar</a>");
                }
            }

            // Acción al presionar "Eliminar"
            if ("eliminar".equals(request.getParameter("btn_eliminar"))) {
                if (cliente.eliminar() > 0) {
                    response.sendRedirect("clientes.jsp"); // Redirige a la página principal tras eliminar
                } else {
                    out.println("<h1>Error: No se pudo eliminar el cliente.</h1>");
                    out.println("<a href='clientes.jsp'>Regresar</a>");
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
