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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        String query = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(5, a.isEstado());
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
        String query = "UPDATE alumno SET dni=? apellido=? nombre=? fechaNacimiento=?, estado=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarAlumnos() {
        String query = "SELECT * FROM alumno";
        

        try {
            PreparedStatement ps = con.prepareStatement(query);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            ResultSet resultado;
            resultado = ps.executeQuery();
            LocalDate fecNac = null;

            while (resultado.next()) {
                fecNac = LocalDate.parse(resultado.getString("fechaNacimiento"));
                
                System.out.println("-------------------------------------");
                System.out.println("idAlumno: " + resultado.getInt("idAlumno")
                        + "\nDNI: " + resultado.getInt("dni") + "\nApellido y nombre: " + resultado.getString("apellido") + " " + resultado.getString("nombre")
                        + "\nFecha de nacimiento: " + fecNac.format(formatter)
                        + "\nEstado: " + resultado.getBoolean("estado"));
                System.out.println("-------------------------------------\n");
            }
            
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
