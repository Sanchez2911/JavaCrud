package supergiros.controller;

import supergiros.database.Conexion;
import supergiros.model.mdlUsuario;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ctlUsuario {
    Conexion conexion = new Conexion();

    ArrayList<mdlUsuario> listaUsuarios = new ArrayList<>();

    public ArrayList<mdlUsuario> consultar() {
        String sql = "SELECT * FROM usuarios";
        ResultSet datos = conexion.EjecutarSelect (conexion.Conexion(), sql);
        try {
            while (datos.next()) {
                mdlUsuario modelo = new mdlUsuario();
                modelo.setId(datos.getInt("id"));
                modelo.setNombre(datos.getString("nombre"));
                modelo.setApellido(datos.getString("apellido"));
                modelo.setEdad(datos.getInt("edad"));
                modelo.setEstado(datos.getBoolean("estado"));
                listaUsuarios.add(modelo);
            }
        } catch (SQLException e) {
            System.out.printf("Fallo consulta BD");
        }
        return listaUsuarios;
    }

    public mdlUsuario consultaId(String nombre, String apellido, int edad){
        mdlUsuario modelo = new mdlUsuario();
        String sql = "SELECT * FROM usuarios WHERE nombre = '"+nombre+"' AND apellido = '"+apellido+"' AND edad = '"+edad+"'";
        ResultSet datos = conexion.EjecutarSelect(conexion.Conexion(), sql);
        try {
            while (datos.next()) {
                modelo.setId(datos.getInt("id"));
                modelo.setNombre(datos.getString("nombre"));
                modelo.setApellido(datos.getString("apellido"));
                modelo.setEdad(datos.getInt("edad"));
                modelo.setEstado(datos.getBoolean("estado"));
            }
        }catch(SQLException e) {
            System.out.println("Error al consultar");
        }
        return modelo;
    }

    public void crearUsuario(String nombre, String apellido, int edad){
        String sql = "INSERT INTO usuarios (nombre, apellido, edad, estado) VALUES ('"+nombre+"','"+apellido+"',"+edad+",TRUE)";
        if(conexion.ejecutarInsert(conexion.Conexion(), sql) == true){
            JOptionPane.showMessageDialog(null, "Se ha guardado el usuario correctamente");
            listaUsuarios.clear();
        }else{
            JOptionPane.showMessageDialog(null, "No se ha podido guardar el usuario.");
        }

    }

    public void eliminarUsuario(int id){
        String sql = "DELETE FROM usuarios WHERE id = "+id;
        if(conexion.ejecutarDelete(conexion.Conexion(), sql) == true){
            JOptionPane.showMessageDialog(null, "Se ha eliminado el usuario correctamente");
            listaUsuarios.clear();
        }else{
            JOptionPane.showMessageDialog(null, "No se ha podido eliminar el usuario.");
        }

    }

    public void modificarUsuario(int id, String nombre, String apellido, Integer edad){
        String sql = "UPDATE usuarios SET nombre = '"+nombre+"', apellido = '"+apellido+"', edad  ="+edad+" WHERE id = "+id;
        if(conexion.ejecutarUpdate(conexion.Conexion(), sql) == true){
            JOptionPane.showMessageDialog(null, "Se ha editado el usuario correctamente");
            listaUsuarios.clear();
        }else{
            JOptionPane.showMessageDialog(null, "No se ha podido editar el usuario.");
        }
    }


}


