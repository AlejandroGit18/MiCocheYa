package controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import modelo.Usuario;
import modelo.MenuItem;

@WebServlet("/sr_login")
public class sr_login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Usuario usuario = new Usuario(username, password);
            String[] respuesta = usuario.Login();

            if (respuesta[0] != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", respuesta[0]);
                int rolId = Integer.parseInt(respuesta[1]);
                session.setAttribute("userIdPuesto", rolId);

                List<MenuItem> menuItems = usuario.obtenerMenuPorRol(rolId);
                session.setAttribute("menuItems", menuItems);

                response.sendRedirect("master.jsp");
            } else {
                response.sendRedirect("index.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=2");
        }
    }
}
