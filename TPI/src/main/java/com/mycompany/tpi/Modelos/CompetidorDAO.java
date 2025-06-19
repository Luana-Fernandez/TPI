/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpi.Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Equipo
 */
public class CompetidorDAO {
    
    private Connection conexion;

    public CompetidorDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    //int idPersona, String nombre, String apellido, String mail, String categoria)
    public void insertarCompetidor(Competidor competidor) throws SQLException {
        String sql = "INSERT INTO competidores (nombre, apellido,telefono, mail) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, competidor.getNombre());
            ps.setString(2, competidor.getApellido());
            ps.setString(3, competidor.getMail());
            ps.setString(4, competidor.getCategoria());
            ps.executeUpdate();
        }
    }

    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.

}
    
