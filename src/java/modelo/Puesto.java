package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Puesto {
    private int id_puesto;
    private String nombre;
    private int estado; // Nuevo campo 'estado'
    Conexion cn;

    // Constructor vacío
    public Puesto() {}

    // Constructor con parámetros
    public Puesto(int id_puesto, String nombre, int estado) {
        this.id_puesto = id_puesto;
        this.nombre = nombre;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    // Método para leer los datos de la tabla TBL_PUESTOS
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_puesto, nombre, estado FROM TBL_PUESTOS;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"ID Puesto", "Nombre", "Estado"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[3];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_puesto");
                datos[1] = consulta.getString("nombre");
                datos[2] = consulta.getString("estado");
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
            String query = "INSERT INTO TBL_PUESTOS(nombre, estado) VALUES(?, ?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setInt(2, getEstado()); // Agregar el valor del estado
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    // Método para modificar un registro en TBL_PUESTOS
    public int modificar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE TBL_PUESTOS SET nombre = ?, estado = ? WHERE id_puesto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setInt(2, getEstado()); // Modificar el estado
            parametro.setInt(3, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

    // Método para eliminar un registro de TBL_PUESTOS
    public int eliminar() {
        int retorno = 0;
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM TBL_PUESTOS WHERE id_puesto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_puesto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }
}
