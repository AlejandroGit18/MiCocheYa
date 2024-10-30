/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicolle
 */
public class Marca {
    private int id_marca;
    private String nombre_m, imagen, estado, imagen_base64;
    Conexion cn;
    
    //Constructor vacío
    public Marca (){}
    //Constructor con parámetros

    public Marca(int id_marca, String nombre_m, String imagen, String estado, String imagen_base64) {
        this.id_marca = id_marca;
        this.nombre_m = nombre_m;
        this.imagen = imagen;
        this.estado = estado;
        this.imagen_base64 = imagen_base64;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNombre() {
        return nombre_m;
    }

    public void setNombre(String nombre_m) {
        this.nombre_m = nombre_m;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getImagen_base64() {
        return imagen_base64;
    }

    public void setImagen_base64(String imagen_base64) {
        this.imagen_base64 = imagen_base64;
    }

    
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT ID_MARCA, NOMBRE, IMAGEN_RUTA, IMAGEN_BASE64, IF(estado = 1, 'Activo', 'Inactivo') AS estado FROM TBL_MARCAS;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"ID Marca", "Nombre de la Marca", "Imagen de Marca", "Imagen Base64", "Estado"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[5];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_marca");
                datos[1] = consulta.getString("nombre");
                datos[2] = consulta.getString("imagen_ruta");
                datos[3] = consulta.getString("imagen_base64");
                datos[4] = consulta.getString("estado");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tabla;
    }

    // Método para agregar un nuevo registro a TBL_PUESTOS
    public int agregar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "INSERT INTO TBL_MARCAS (nombre, imagen_ruta, estado, IMAGEN_BASE64) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();

        // Guardamos solo el nombre del archivo en lugar de la ruta completa
        String fileName = getImagen().substring(getImagen().lastIndexOf("\\") + 1);
        
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getNombre());
        parametro.setString(2, fileName);
        parametro.setString(3, getEstado());
        parametro.setString(4, getImagen_base64());
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return retorno;
}

    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE TBL_MARCAS SET nombre = ?, imagen_ruta = ?, estado = ?, IMAGEN_BASE64 = ? WHERE id_marca = ?;";
            cn.abrir_conexion();

            // Guardamos solo el nombre del archivo en lugar de la ruta completa
            String fileName = getImagen().substring(getImagen().lastIndexOf("\\") + 1);

            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setString(2, fileName);
            parametro.setString(3, getEstado());
            parametro.setString(4, getImagen_base64());
            parametro.setInt(5, getId_marca());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
    
}