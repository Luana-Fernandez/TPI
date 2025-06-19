package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CarreraDAO {
    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public CarreraDAO(Connection conexion) {
        this.conexion = conexion;
    }
    //int idCarrera, String categoria, String horaInicio, String horaFin, String ubicacion, String detalle, int idJuez
     public void insertarCarrera(Carrera carrera) throws SQLException {
        String sql = "INSERT INTO carreras (categoria, idJuez, horaInicio, horaFin, ubicacion, detalleRecorrido) VALUES (?, ?, ?, ?, ?, ?)";    

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, carrera.getCategoria());
            ps.setString(2, carrera.getIdJuez());
            ps.setString(3, carrera.getHoraInicio());
            ps.setString(4, carrera.getHoraFin());
            ps.setString(5, carrera.getUbicacion());
            ps.setString(6, carrera.getDetalle());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    carrera.setIdCarrera(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Carrera insertado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar carrera: " + e.getMessage());
        }
    }
    // Podés agregar más métodos: actualizarCarrera, eliminarCarrera, buscarCarrera, etc.

}
