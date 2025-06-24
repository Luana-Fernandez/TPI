package com.mycompany.tpi.Modelos;

import com.mycompany.tpi.vista.VistaPrincipal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JuezDAO {

    private Connection conexion;
    private VistaPrincipal vista = new VistaPrincipal();

    public JuezDAO() {
    }
    
    public JuezDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    public int insertarJuez(Juez juez) throws SQLException {
        String sql = "INSERT INTO jueces (nombre, apellido, telefono, email, dni) VALUES (?, ?, ?, ?, ?)";    
        int idGenerado=0;
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, juez.getNombre());
            ps.setString(2, juez.getApellido());
            ps.setString(3, juez.getTelefono());
            ps.setString(4, juez.getMail());
            ps.setInt(5,juez.getDni());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                    juez.setIdPersona(idGenerado); // Asignás el ID generado a tu objeto
                    vista.mensaje("Registro Exitoso");
                }
            }
        } catch (SQLException e) {
            vista.mensaje("Error al insertar juez: " + e.getMessage());
        }
        return idGenerado;
    }
    // Podés agregar más métodos: actualizarCliente, eliminarCliente, buscarClientePorId, etc.
        public List<Juez> listaJueces() throws SQLException {
        String sql = "SELECT * FROM jueces";    
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        String[] lista= new String[6];
        
        List<Juez> listaJueces = new ArrayList<>();
        while (rs.next()){
            lista[0]=rs.getString("idJuez");
            lista[1]=rs.getString("nombre");
            lista[2]=rs.getString("apellido");
            lista[3]=rs.getString("email");
            lista[4]=rs.getString("telefono");   
            lista[5]=rs.getString("dni");
            Juez j = new Juez(lista[1],lista[2],lista[3],lista[4],Integer.parseInt(lista[5]));
            j.setIdPersona(Integer.parseInt(lista[0]));
            listaJueces.add(j);
            }
        vista.mensaje("Carga Jueces Exitosa");
        return listaJueces;
        }
}
