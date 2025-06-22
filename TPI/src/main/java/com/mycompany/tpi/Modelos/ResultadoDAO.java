package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ResultadoDAO {
    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public ResultadoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarResultado(Resultado resultado) {
        String sql = "INSERT INTO resultados (idCompetidor, idCarrera, tiempoCompetidor, estado, numeroCorredor, faltas) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, resultado.getIdCompetidor());
            ps.setInt(2, resultado.getCarrera());
            ps.setString(3, resultado.getTiempoCompetidor());
            ps.setString(4, resultado.getEstado());
            ps.setInt(5, resultado.getNumCorredor());
            ps.setString(6, String.valueOf(resultado.getFaltas()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    resultado.setIdResultado(idGenerado);
                    vista.mensaje("Resultado registrado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar resultado: " + e.getMessage());
        }
    }
}
