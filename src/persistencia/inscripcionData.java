/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Grupo10 Altamirano Karina Gianfranco Antonacci Matías Bequis Marcos
 * Ezequiel Dave Natalia Quiroga Dorzan Alejo
 */
public class inscripcionData {

    private Connection con = null;
    private materiaData md;
    private alumnoData ad;

    public inscripcionData(miConexion conexion) {
        this.con = conexion.buscarConexion();
        this.md = new materiaData(conexion);
        this.ad = new alumnoData(conexion);
    }

    public void guardarInscripcion(Inscripcion insc) {

        String sql = "INSERT INTO  inscripcion (nota, idAlumno, idMateria) VALUES(?, ?,?) ";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getIdAlumno());
            ps.setInt(3, insc.getIdMateria());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                insc.setIdInscripto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "\"Inscripción registrada\"");

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");
        }

    }

    public ArrayList<Inscripcion> obtenerInscripciones() {

        ArrayList<Inscripcion> cursadas = new ArrayList<>();

        String sql = "SELECT  * FROM inscripcion";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                Alumno alu = ad.buscarAlumnoPorId(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setIdAlumno(alu.getIdAlumno());
                insc.setIdMateria(mat.getIdMateria());
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");
        }
        return cursadas;
    }

    public void actualizarNota(double nota, int idAlumno, int idMateria) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? and idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);

            int fila = ps.executeUpdate();

            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada con exito.", "", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarNotaPorIdInscripto(double nota, int idInscripto) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE idInscripto = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idInscripto);

            int fila = ps.executeUpdate();

            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Nota actualizada con exito.", "", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void borrarInscripcion(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? and idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            int fila = ps.executeUpdate();

            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Inscripcion anulada con exito.", "", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Inscripcion> obtenerInscripcionesPorAlumno(int idAlumno) {

        ArrayList<Inscripcion> cursadas = new ArrayList<>();

        String sql = "SELECT  * FROM inscripcion WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                Alumno alu = ad.buscarAlumnoPorId(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                insc.setIdAlumno(alu.getIdAlumno());
                insc.setIdMateria(mat.getIdMateria());
                insc.setNota(rs.getDouble("nota"));
                cursadas.add(insc);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");

        }
        return cursadas;

    }

    public ArrayList<Materia> obtenerMateriasCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT inscripcion.idMateria, materia.nombre, materia.año, materia.estado "
                + "FROM inscripcion "
                + "JOIN materia ON inscripcion.idMateria = materia.idMateria "
                + "WHERE inscripcion.idAlumno = ?;";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");
        }

        return materias;

    }

    public ArrayList<Materia> obtenerMateriasNOCursadas(int idAlumno) {
        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria not in (SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");
        }

        return materias;
    }

    public ArrayList<Alumno> obtenerAlumnosPorMateria(int idMateria) {
        ArrayList<Alumno> alumnosMateria = new ArrayList<>();

        String sql = "SELECT a.idAlumno, dni, nombre, apellido, fechaNacimiento, estado "
                + "FROM inscripcion i, alumno a "
                + "WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado = 1";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMateria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setDni(rs.getInt("dni")); // ✅ agregada esta línea
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                    alumno.setEstado(rs.getBoolean("estado"));
                    alumnosMateria.add(alumno);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción: " + ex.getMessage());
        }

        return alumnosMateria;
    }
}
