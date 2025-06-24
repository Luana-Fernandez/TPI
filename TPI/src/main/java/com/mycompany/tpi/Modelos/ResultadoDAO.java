package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ResultadoDAO {

    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public ResultadoDAO() {
    }

    public ResultadoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public int insertarResultado(Resultado resultado) {
        String sql = "INSERT INTO resultados (idCompetidor, idCarrera, tiempoCompetidor, estado, numeroCorredor, faltas) VALUES (?, ?, ?, ?, ?, ?)";
        int idGenerado = 0;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, resultado.getIdCompetidor());
            ps.setInt(2, resultado.getIdCarrera());
            ps.setString(3, resultado.getTiempoCompetidor());
            ps.setString(4, resultado.getEstado());
            ps.setInt(5, resultado.getNumCorredor());
            ps.setString(6, String.valueOf(resultado.getFaltas()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    resultado.setIdResultado(idGenerado);
                    vista.mensaje("Registro exitoso");
                    return idGenerado;
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar resultado: " + e.getMessage());
        }
        return idGenerado;
    }

    public List<Resultado> listaResultados() throws SQLException {
        String sql = "SELECT * FROM resultados";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String[] lista = new String[7];

        List<Resultado> listaResultados = new ArrayList<>();
        while (rs.next()) {
            lista[0] = rs.getString("idResultado");
            lista[1] = rs.getString("idCompetidor");
            lista[2] = rs.getString("idCarrera");
            lista[3] = rs.getString("tiempoCompetidor");
            lista[4] = rs.getString("estado");
            lista[5] = rs.getString("numeroCorredor");
            lista[6] = rs.getString("faltas");
            Resultado r = new Resultado(Integer.parseInt(lista[1]), Integer.parseInt(lista[2]), lista[3], lista[4], Integer.parseInt(lista[5]), Integer.parseInt(lista[6]));
            r.setIdResultado(Integer.parseInt(lista[0]));
            listaResultados.add(r);
        }
        vista.mensaje("Carga Resultados Exitosa");
        return listaResultados;
    }

    public void registrarTiempoCorredor(int idCorredor, String tiempoCorredor, int idCarrera) {
        try {
            String sql = "UPDATE resultados SET tiempoCompetidor=? WHERE idCompetidor=? && idCarrera=?;";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, tiempoCorredor);
            ps.setInt(2, idCorredor);
            ps.setInt(3, idCarrera);
            ps.executeUpdate();
            vista.mensaje("Actualizacion Exitoso");
        } catch (SQLException e) {
            vista.mensaje("Error: " + e.getMessage());
        }

    }

    public void registrarFaltas(int idCompetidor, int faltas, int idCarrera) {
        try {
            String sql = "UPDATE resultados SET faltas=? WHERE idCompetidor=? && idCarrera=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, faltas);
            ps.setInt(2, idCompetidor);
            ps.setInt(3, idCarrera);
            ps.executeUpdate();
            vista.mensaje("Actualizacion Exitoso");
        } catch (SQLException e) {
            vista.mensaje("Error al registrar faltas: " + e.getMessage());
        }
    }
    

    public void modificarEstado(int idCompetidor, String nuevoEstado, int idCarrera) {
        try {
            String sql = "UPDATE resultados SET estado=? WHERE idCompetidor=? && idCarrera=?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idCompetidor);
            ps.setInt(3, idCarrera);
            ps.executeUpdate();
            vista.mensaje("Estado modificado Correctamente.");
        } catch (SQLException e) {
            vista.mensaje("Error al modificar el estado: " + e.getMessage());
        }
    }
}
