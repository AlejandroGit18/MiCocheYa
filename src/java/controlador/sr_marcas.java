package controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Marca;

@WebServlet(name = "sr_marcas", urlPatterns = {"/sr_marcas"})
@MultipartConfig
public class sr_marcas extends HttpServlet {
    Marca marca;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            // Definir la ruta donde se guardarán las imágenes
            String uploadPath = "C:\\Users\\nicol\\OneDrive\\Escritorio\\Proyecto Final\\MiCocheYa\\web\\images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Obtener la imagen del formulario
            Part filePart = request.getPart("txt_imagen");
            String fileName = filePart.getSubmittedFileName();
            String imagePath = uploadPath + File.separator + fileName;

            // Guardar la imagen en el servidor
            try (InputStream fileContent = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(imagePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Convertir la imagen a base64
            byte[] imageBytes = filePart.getInputStream().readAllBytes();
            String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

            // Crear el objeto Marca con los datos del formulario
            marca = new Marca(
                Integer.parseInt(request.getParameter("txt_id")),
                request.getParameter("txt_marca"),
                fileName,  // Guardamos el nombre del archivo para la ruta física
                request.getParameter("txt_estado"),
                imageBase64  // Guardamos la imagen en base64
            );

            // Determinar la acción a realizar (agregar, modificar o eliminar)
            String action = request.getParameter("btn_agregar") != null ? "agregar" :
                            request.getParameter("btn_modificar") != null ? "modificar" : "eliminar";

            switch (action) {
                case "agregar":
                    if (marca.agregar() > 0) {
                        response.sendRedirect("marcas.jsp");
                    } else {
                        out.println("Error al agregar la marca.");
                    }
                    break;
                case "modificar":
                    if (marca.modificar() > 0) {
                        response.sendRedirect("marcas.jsp");
                    } else {
                        out.println("Error al modificar la marca.");
                    }
                    break;
                
            }
        }    }

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
        return "Servlet para gestionar el CRUD de marcas";
    }
}
