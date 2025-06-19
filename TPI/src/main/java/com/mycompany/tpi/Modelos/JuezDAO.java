package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JuezDAO {

    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public JuezDAO(Connection conexion) {
        this.conexion = conexion;
    }

    //int idPersona, String nombre, String apellido, String mail, String categoria)
    public void insertarJuez(Juez juez) throws SQLException {
        /*String sql = "INSERT INTO jueces (idJuez, nombre, apellido,telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt (

            1, juez.getIdPersona());
            ps.setString (

            2, juez.getNombre());
            ps.setString (

            3, juez.getApellido());
            ps.setString (

            4, juez.getMail());
            ps.setString (

            5, juez.getCategoria());
            ps.executeUpdate ();
        }
    catch (Exception e) {
        vista.mensaje("Error de lectura" + e.getMessage());
    }*/
    try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        String sql = "INSERT INTO jueces (idJuez, nombre, apellido,telefono, email) VALUES (?, ?, ?, ?, ?)";
        ps.setString(1, juez.getNombre());
        ps.setString(2, juez.getApellido());
        ps.setString(3, juez.());
        ps.setString(4, juez.getMail());
        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                juez.setIdPersona(idGenerado); // Si tenés un setter
            }
    }
}

    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.
    }
    
}
