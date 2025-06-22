package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class CompetidorDAO {
    
    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public CompetidorDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public int insertarCompetidor(Competidor competidor) throws SQLException {
        String sql = "INSERT INTO competidores (nombre, apellido, telefono, email) VALUES (?, ?, ?, ?)";    
        int idGenerado=0;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            System.out.println("Intentando insertar en la base: " + competidor.getNombre());
            ps.setString(1, competidor.getNombre());
            ps.setString(2, competidor.getApellido());
            ps.setString(3, competidor.getTelefono());
            ps.setString(4, competidor.getMail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    competidor.setIdPersona(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Competidor insertado con ID: " + idGenerado);
                }
            }
        } catch (SQLException e) {
            //vista.mensaje("Error al insertar competidor: " + e.getMessage());
            e.printStackTrace();
        }
        return idGenerado;
    }
    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.
    
    public List<Competidor> listaCompetidores() throws SQLException {
        String sql = "SELECT * FROM competidores";    
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println(rs);
        String[] lista= new String[5];
        
        List<Competidor> listaCompetidores = new ArrayList<>();
        while (rs.next()){
            lista[0]=rs.getString("idCompetidor");
            lista[1]=rs.getString("nombre");
            lista[2]=rs.getString("apellido");
            lista[3]=rs.getString("email");
            lista[4]=rs.getString("telefono");   
            Competidor c = new Competidor(lista[1],lista[2],lista[3],lista[4]);
            c.setIdPersona(Integer.parseInt(lista[0]));
            listaCompetidores.add(c);
            }
        
        return listaCompetidores;
        }

}
    
