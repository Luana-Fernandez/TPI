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
    
    public void insertarJuez(Juez juez) throws SQLException {
        String sql = "INSERT INTO jueces (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";    

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, juez.getNombre());
            ps.setString(2, juez.getApellido());
            ps.setString(3, juez.getTelefono());
            ps.setString(4, juez.getMail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    juez.setIdPersona(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Juez insertado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar juez: " + e.getMessage());
        }
    }
    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.
    
}
