/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;
import entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Grupo10 Altamirano Karina Gianfranco Antonacci Matías Bequis Marcos
 * Ezequiel Dave Natalia Quiroga Dorzan Alejo
 */
public class alumnoData {

    private Connection con = null;

    public alumnoData(miConexion conexion) {
        this.con = conexion.buscarConexion();
        //con = miConexion.buscarConexion();
    }

    public void guardarAlumno(Alumno a) {

        String query = "INSERT INTO alumno(nombre, fecNac, activo) VALUES (?,?,?)";   

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
            ps.setString(1, a.getNombre());
            ps.setDate(2, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(3, a.isEstado());
            ps.executeUpdate();   

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAlumno(1);
            } else {
                System.out.println("No se pudo tener ID");
            }
            ps.close();
            System.out.println("Guardado");
        } catch (SQLException ex) {

            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void actualizarAlumno(Alumno a) {
        String query = "UPDATE alumno SET nombre=? fecNac=?, activo=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, a.getNombre());
            ps.setDate(2, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(3, a.isEstado());
            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {

            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
