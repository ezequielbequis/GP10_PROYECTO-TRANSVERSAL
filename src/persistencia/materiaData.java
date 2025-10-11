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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Grupo10 Altamirano Karina Gianfranco Antonacci Matías Bequis Marcos
 * Ezequiel Dave Natalia Quiroga Dorzan Alejo
 */
public class materiaData {
     private Connection con = null;

    public materiaData(miConexion conexion) {
        this.con = conexion.buscarConexion();
        //con = miConexion.buscarConexion();
    }
      public void guardarMateria(Materia m) {

        String query = "INSERT INTO materia(idMateria, nombre, anio, estado) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getIdMateria());
            ps.setString(2, m.getNombre());
            ps.setInt(3,anio(m.getAnio()));
            ps.setBoolean(4, m.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                m.setIdMateria(1);
            } else {
                System.out.println("No se pudo tener ID");
            }
            ps.close();
            System.out.println("Guardado");
        } catch (SQLException ex) {
            Logger.getLogger(materiaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int anio(int anio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Materia buscarMateria(int id){
    String sql="SELECT idMateria, nombre, anio FROM materia WHERE idMateria = ? AND estado=1";
    Materia materia=null;
    try {
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            materia=new Materia();
            materia.setIdMateria(rs.getInt("id"));
            materia.setNombre(rs.getString("nombre"));
            materia.setAnio(rs.getInt("anio"));
            materia.setEstado(true);
        }else{
            JOptionPane.showMessageDialog(null,"No existe esa materia");
        }
        ps.close();
    }catch (SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
    }return materia;
}
}
