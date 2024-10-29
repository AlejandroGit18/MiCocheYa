package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    public Usuario() {}

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String[] Login() {
        Conexion conexionDB = new Conexion();
        conexionDB.abrir_conexion();
        Connection conn = conexionDB.conexionBD;
        String[] respuesta = new String[2];

        try {
            String sql = "SELECT u.ID_USUARIO, cup.ID_PUESTO AS ROL " +
                         "FROM TBL_USUARIOS u " +
                         "JOIN TBL_CONTROL_USUARIOS_PUESTOS cup ON u.ID_USUARIO = cup.ID_USUARIO " +
                         "WHERE u.USERNAME = ? AND u._PASSWORD = ? AND u.ESTADO = 1 AND cup.ESTADO = 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getNombreUsuario());
            ps.setString(2, getContrasena());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("ID_USUARIO");
                String userRole = rs.getString("ROL");
                respuesta[0] = Integer.toString(userId);
                respuesta[1] = userRole;
            } else {
                respuesta[0] = null;
                respuesta[1] = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    public List<MenuItem> obtenerMenuPorRol(int rolId) {
        List<MenuItem> menuItems = new ArrayList<>();
        Conexion conexionDB = new Conexion();
        conexionDB.abrir_conexion();
        Connection conn = conexionDB.conexionBD;

        try {
          String sql = "SELECT m.ID_MENU, m.DESCRIPCION, m.HREF, m.ES_SUB_MENU, m.ID_MENU_PADRE, m.ICON " +
                         "FROM TBL_CAT_MENU m " +
                         "JOIN TBL_ACCESO_MENU am ON m.ID_MENU = am.ID_MENU " +
                         "WHERE am.ID_PUESTO = ? AND m.ESTADO = 1 AND am.ESTADO = 1";
          
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rolId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idMenu = rs.getInt("ID_MENU");
                String descripcion = rs.getString("DESCRIPCION");
                String href = rs.getString("HREF");
                boolean esSubMenu = rs.getBoolean("ES_SUB_MENU");
                int idMenuPadre = rs.getInt("ID_MENU_PADRE");
                String Icon = rs.getString("ICON");
                menuItems.add(new MenuItem(idMenu, descripcion, href, esSubMenu, idMenuPadre, Icon));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return menuItems;
    }
}

