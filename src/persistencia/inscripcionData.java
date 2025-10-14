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
import java.util.List;
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
       this.md=new materiaData((miConexion) con);
       this.ad= new alumnoData((miConexion)con);
        
    }

    public void guardarInscripcion(Inscripcion insc) {

        String sql = "INSERT INTO  inscripcion (idAlumno, idMateria, nota) VALUES(?, ?,?) ";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, insc.getIdAlumno());
            ps.setInt(2, insc.getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {

                insc.setIdInscripto(rs.getInt(1));
                JOptionPane.showInternalMessageDialog(null, "Inscripción registrada");

            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción");
        }

    }
    
    public List <Inscripcion> obtenerInscripciones(){
    
            ArrayList <Inscripcion> cursadas = new ArrayList<>();
            
            String sql= "SELECT  * FROM inscripcion";
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
            
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                Alumno alu=ad.buscarAlumnoPorId(rs.getInt("idAlumno"));
                Materia mat= md.buscarMateria(rs.getInt("idMateria"));
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
    
    
}
