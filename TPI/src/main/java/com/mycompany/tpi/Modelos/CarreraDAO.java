package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class CarreraDAO {
    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public CarreraDAO() {
    }
    
    public CarreraDAO(Connection conexion) {
        this.conexion = conexion;
    }
    //int idCarrera, String categoria, String horaInicio, String horaFin, String ubicacion, String detalle, int idJuez
     public int insertarCarrera(Carrera carrera) throws SQLException {
        String sql = "INSERT INTO carreras (categoria, idJuez, horaInicio, horaFin, ubicacion, detalleRecorrido) VALUES (?, ?, ?, ?, ?, ?)";    
        int idGenerado=0;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, carrera.getCategoria());
            ps.setInt(2, carrera.getIdJuez());
            ps.setString(3, carrera.getHoraInicio());
            ps.setString(4, carrera.getHoraFin());
            ps.setString(5, carrera.getUbicacion());
            ps.setString(6, carrera.getDetalle());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    carrera.setIdCarrera(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Registro Exitoso");
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar carrera: " + e.getMessage());
        }
        return idGenerado;
    }
    // Podés agregar más métodos: actualizarCarrera, eliminarCarrera, buscarCarrera, etc.

        public List<Carrera> listaCarreras() throws SQLException {
        String sql = "SELECT * FROM carreras";    
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String[] lista= new String[7];
        
        List<Carrera> listaCarreras = new ArrayList<>();
        while (rs.next()){
            lista[0]=rs.getString("idCarrera");
            lista[1]=rs.getString("categoria");
            lista[2]=rs.getString("horaInicio");
            lista[3]=rs.getString("horaFin");
            lista[4]=rs.getString("ubicacion");  
            lista[5]=rs.getString("detalleRecorrido"); 
            lista[6]=rs.getString("idJuez"); 
            Carrera c = new Carrera(lista[1],lista[2],lista[3],lista[4],lista[5],Integer.parseInt(lista[6]));
            c.setIdCarrera(Integer.parseInt(lista[0]));
            listaCarreras.add(c);
            }
            vista.mensaje("Carga Carreras Exitosa");
        return listaCarreras;
        }
        
         public void registrarHoraFinCarrera(int idCarrera, String horaFin) {
        try {
            String sql = "UPDATE carreras SET horaFin=? WHERE idCarrera=?;";

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1,horaFin);
            ps.setInt(2, idCarrera);
            ps.executeUpdate();
            vista.mensaje("Actualizacion Exitosa");
        } catch (SQLException e) {
            vista.mensaje("Error: " + e.getMessage());
        }

    }
}
