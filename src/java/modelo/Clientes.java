package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Clientes {
    private int id_cliente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String nit;
    private int estado;
    private String usuario_creacion;
    private String usuario_modificacion;
    private Conexion cn;

    // Constructor vacío
    public Clientes() {
        cn = new Conexion();
    }

    // Constructor con parámetros
    public Clientes(int id_cliente, String nombres, String apellidos, String telefono, String direccion, String nit, int estado, String usuario_creacion, String usuario_modificacion) {
        this.id_cliente = id_cliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.estado = estado;
        this.usuario_creacion = usuario_creacion;
        this.usuario_modificacion = usuario_modificacion;
        this.cn = new Conexion();
    }

    // Getters y Setters
    public int getId_cliente() { return id_cliente; }
    public void setId_cliente(int id_cliente) { this.id_cliente = id_cliente; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public String getUsuario_creacion() { return usuario_creacion; }
    public void setUsuario_creacion(String usuario_creacion) { this.usuario_creacion = usuario_creacion; }
    public String getUsuario_modificacion() { return usuario_modificacion; }
    public void setUsuario_modificacion(String usuario_modificacion) { this.usuario_modificacion = usuario_modificacion; }

    // Método para leer los datos de la tabla TBL_CLIENTES
    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        String query = "SELECT id_cliente, nombres, apellidos, telefono, direccion, nit, estado, fecha_creacion, usuario_creacion, fecha_modificacion, usuario_modificacion FROM TBL_CLIENTES;";
        String[] encabezado = {"ID Cliente", "Nombres", "Apellidos", "Teléfono", "Dirección", "NIT", "Estado", "Fecha Creación", "Usuario Creación", "Fecha Modificación", "Usuario Modificación"};
        tabla.setColumnIdentifiers(encabezado);

        try {
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()) {
                String[] datos = new String[11];
                datos[0] = consulta.getString("id_cliente");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("telefono");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("nit");
                datos[6] = consulta.getString("estado");
                datos[7] = consulta.getString("fecha_creacion");
                datos[8] = consulta.getString("usuario_creacion");
                datos[9] = consulta.getString("fecha_modificacion");
                datos[10] = consulta.getString("usuario_modificacion");
                tabla.addRow(datos);
            }
            consulta.close();  // Cerrar ResultSet
        } catch (SQLException ex) {
            System.out.println("Error en leer: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
        return tabla;
    }

    // Método para agregar un nuevo registro a TBL_CLIENTES
    public int agregar() {
        int retorno = 0;
        String query = "INSERT INTO TBL_CLIENTES(nombres, apellidos, telefono, direccion, nit, estado, fecha_creacion, usuario_creacion) VALUES(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?);";
        
        try {
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, nombres);
            parametro.setString(2, apellidos);
            parametro.setString(3, telefono);
            parametro.setString(4, direccion);
            parametro.setString(5, nit);
            parametro.setInt(6, estado);
            parametro.setString(7, usuario_creacion);
            retorno = parametro.executeUpdate();
            parametro.close();  // Cerrar PreparedStatement
        } catch (SQLException ex) {
            System.out.println("Error en agregar: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
        return retorno;
    }

    // Método para modificar un registro en TBL_CLIENTES
    public int modificar() {
        int retorno = 0;
        String query = "UPDATE TBL_CLIENTES SET nombres = ?, apellidos = ?, telefono = ?, direccion = ?, nit = ?, estado = ?, fecha_modificacion = CURRENT_TIMESTAMP, usuario_modificacion = ? WHERE id_cliente = ?;";
        
        try {
            cn.abrir_conexion();
            PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
            parametro.setString(1, nombres);
            parametro.setString(2, apellidos);
            parametro.setString(3, telefono);
            parametro.setString(4, direccion);
            parametro.setString(5, nit);
            parametro.setInt(6, estado);
            parametro.setString(7, usuario_modificacion);
            parametro.setInt(8, id_cliente);
            retorno = parametro.executeUpdate();
            parametro.close();  // Cerrar PreparedStatement
        } catch (SQLException ex) {
            System.out.println("Error en modificar: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }
        return retorno;
    }

   public int eliminar() {
    int retorno = 0;
    String query = "DELETE FROM TBL_CLIENTES WHERE id_cliente = ?;";
    
    try {
        cn.abrir_conexion();
        PreparedStatement parametro = cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, this.id_cliente);  // Usa el ID que fue establecido
        retorno = parametro.executeUpdate();
        parametro.close();
    } catch (SQLException ex) {
        System.out.println("Error en eliminar: " + ex.getMessage());
    } finally {
        cn.cerrar_conexion();
    }
    return retorno;
}
}
