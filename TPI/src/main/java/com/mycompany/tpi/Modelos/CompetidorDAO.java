package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CompetidorDAO {
    
    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public CompetidorDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public void insertarCompetidor(Competidor competidor) throws SQLException {
        String sql = "INSERT INTO competidores (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";    

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Intentando insertar en la base: " + competidor.getNombre());
            ps.setString(1, competidor.getNombre());
            ps.setString(2, competidor.getApellido());
            ps.setString(3, competidor.getTelefono());
            ps.setString(4, competidor.getMail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    competidor.setIdPersona(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Competidor insertado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            //vista.mensaje("Error al insertar competidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.

}
    
