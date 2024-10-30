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
public class Proveedores {

    private int id_proveedores;
    private String nombre, telefono, direccion, nit, estado;
    Conexion cn;

    public Proveedores() {
    }

    public Proveedores(int id_proveedores, String nombre, String telefono, String direccion, String nit, String estado) {
        this.id_proveedores = id_proveedores;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nit = nit;
        this.estado = estado;
    }

    public int getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(int id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT ID_PROVEEDOR, NOMBRE, TELEFONO, DIRECCION, NIT, \n"
                    + "IF(estado = 1, 'Activo', 'Inactivo') AS estado FROM TBL_PROVEEDORES;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[] = {"ID Proveedores", "Nombre del Proveedor", "Telefono", "Direccion", "Nit", "Estado"};
            tabla.setColumnIdentifiers(encabezado);

            String datos[] = new String[6];
            while (consulta.next()) {
                datos[0] = consulta.getString("id_proveedor");
                datos[1] = consulta.getString("nombre");
                datos[2] = consulta.getString("telefono");
                datos[3] = consulta.getString("direccion");
                datos[4] = consulta.getString("nit");
                datos[5] = consulta.getString("estado");
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
            String query = "INSERT INTO TBL_PROVEEDORES (NOMBRE, TELEFONO, DIRECCION, NIT, ESTADO) VALUES (?, ?, ?, ?, ?);";
            cn.abrir_conexion();

            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setString(2, getTelefono());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getNit());
            parametro.setString(5, getEstado());
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
            String query = "UPDATE TBL_PROVEEDORES SET NOMBRE = ?, TELEFONO = ?, DIRECCION = ?, NIT = ?, ESTADO = ? WHERE ID_PROVEEDOR = ?;";
            cn.abrir_conexion();

            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setString(2, getTelefono());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getNit());
            parametro.setString(5, getEstado());
            parametro.setInt(6, getId_proveedores());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return retorno;
    }

}
