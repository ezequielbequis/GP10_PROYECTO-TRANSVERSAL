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
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

    public alumnoData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Alumno buscarAlumnoPorDni(int dni) {
        String query = "SELECT * FROM `alumno` WHERE `dni` = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dni);
            Alumno alum = null;
            ResultSet resultado = ps.executeQuery();
            LocalDate fecNac = null;

            while (resultado.next()) {
                fecNac = LocalDate.parse(resultado.getString("fechaNacimiento"));
                alum = new Alumno(resultado.getInt("idAlumno"), resultado.getInt("dni"), 
                        resultado.getString("apellido"), resultado.getString("nombre"),
                        fecNac, resultado.getBoolean("estado"));
            }
            return alum;
        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Alumno buscarAlumnoPorId(int Id) {
        String query = "SELECT * FROM `alumno` WHERE `idAlumno` = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Id);
            Alumno alum = null;
            ResultSet resultado = ps.executeQuery();
            LocalDate fecNac = null;

            while (resultado.next()) {
                fecNac = LocalDate.parse(resultado.getString("fechaNacimiento"));
                alum = new Alumno(resultado.getInt("idAlumno"), resultado.getInt("dni"), resultado.getString("apellido"), resultado.getString("nombre"),
                        fecNac, resultado.getBoolean("estado"));
            }
            return alum;
        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void darBajaAlumno(int idAlumno) {
        String query = "UPDATE alumno SET estado=0 WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, idAlumno);
            int i = ps.executeUpdate();

            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Alumno dado de baja con exito.", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el alumno", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preCargarAlumnos() {
        guardarAlumno(new Alumno(33465789, "Altamirano", "Karina", LocalDate.of(1988, 6, 14), true));
        guardarAlumno(new Alumno(44075064, "Antonacci", "Matías", LocalDate.of(2002, 3, 15), true));
        guardarAlumno(new Alumno(44437768, "Bequis", "Ezequiel", LocalDate.of(2002, 12, 13), true));
        guardarAlumno(new Alumno(44953830, "Quiroga", "Alejo", LocalDate.of(2003, 9, 12), true));
        guardarAlumno(new Alumno(31092801, "Dave", "Natalia", LocalDate.of(1984, 8, 9), true));
    }

    public void guardarAlumno(Alumno a) {

        String query = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(5, a.getEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setIdAlumno(1);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo tener ID.", "", JOptionPane.ERROR_MESSAGE);
            }
            ps.close();

            // JOptionPane.showMessageDialog(null, "Alumno guardado con exito.", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarAlumno(Alumno a) {        
        String query = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ?, estado = ? WHERE idAlumno = ?";
        //System.out.println("["+a.getDni()+"]"+a.getApellido()+"]"+a.getNombre()+"]"+a.getFechaNac()+"]"+a.getEstado()+"]"+a.getIdAlumno()+"] ");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNac()));
            ps.setBoolean(5, a.getEstado());
            ps.setInt(6, a.getIdAlumno());
            int aux = ps.executeUpdate();
            if (aux == 0) {
                JOptionPane.showMessageDialog(null, "El Alumno no ha modificado.");
            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList listarAlumnos() {
        String query = "SELECT * FROM alumno";
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        Alumno alum = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            ResultSet resultado;
            resultado = ps.executeQuery();
            LocalDate fecNac = null;

            while (resultado.next()) {

                fecNac = LocalDate.parse(resultado.getString("fechaNacimiento"));
                alum = new Alumno(resultado.getInt("idAlumno"), resultado.getInt("dni"), resultado.getString("apellido"), resultado.getString("nombre"), fecNac, resultado.getBoolean("estado"));
                listaAlumnos.add(alum);
            }

            ps.close();
            return listaAlumnos;
        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
