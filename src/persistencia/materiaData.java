/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * @author Grupo10 Altamirano Karina Gianfranco Antonacci Matías Bequis Marcos
 * Ezequiel Dave Natalia Quiroga Dorzan Alejo
 */
public class materiaData {

    private Connection con = null;

    public materiaData(miConexion conexion) {
        this.con = conexion.buscarConexion();
    }

    public materiaData() {
       this.con = new miConexion().buscarConexion();
    }

    public void guardarMateria(Materia materia) {
        String comprobacionSql = "SELECT * FROM materia WHERE nombre = ? AND año = ?";
        String insertSql = "INSERT INTO materia(nombre, año, estado) VALUES (?,?,?)";
        boolean existe = false;

        try {
            // Verficacion para ver si existe una materia con el mismo nombre y años antes de cargar
            PreparedStatement ps = con.prepareStatement(comprobacionSql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Ya existe una materia con ese nombre y año.", "Error", JOptionPane.ERROR_MESSAGE);
                ps.close();
                existe = true;
            }
            ps.close();

            // En caso de no existir materia igual la cargamos
            if (existe == false) {
                PreparedStatement psInsert = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, materia.getNombre());
                psInsert.setInt(2, materia.getAnio());
                psInsert.setBoolean(3, materia.getEstado());
                psInsert.executeUpdate();

                rs = psInsert.getGeneratedKeys();
                if (rs.next()) {
                    materia.setIdMateria(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "Materia guardada correctamente.");
                }

                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la materia: " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificarMateria(Materia materia) {

        String query = "UPDATE materia SET  nombre = ?, año = ?, estado = ? WHERE idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.getEstado());
            ps.setInt(4, materia.getIdMateria());

            int exito = ps.executeUpdate();
            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Materia modificada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public void eliminarMateria(int id) {

        String query = "UPDATE materia SET  estado = 0 WHERE idMateria = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int exito = ps.executeUpdate();
            if (exito == 1) {

                JOptionPane.showMessageDialog(null, "Materia eliminada");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }

    }

    public Materia buscarMateria(int id) {
        String sql = "SELECT idMateria, nombre, año, estado FROM materia WHERE idMateria = ?";
        Materia materia = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe esa materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return materia;
    }

    public ArrayList listarMaterias() {

        //String sql = "SELECT idMateria, nombre, año FROM materia WHERE estado=1";
        String sql = "SELECT * FROM materia";
        ArrayList<Materia> materias = new ArrayList<>();
        Materia materia = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                materia = new Materia(rs.getInt("idMateria"), rs.getString("nombre"), rs.getInt("año"), rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
            return materias;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");
        }
        return null;
    }

}
