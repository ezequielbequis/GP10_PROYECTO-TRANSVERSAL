package vistas;

import entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import persistencia.alumnoData;
import persistencia.miConexion;
//import persistencia.ConexionS;

/**
 *
 * @author Grupo10 Altamirano Karina Gianfranco Antonacci Matías Bequis Marcos
 * Ezequiel Dave Natalia Quiroga Dorzan Alejo
 */
public class GP10_universidad {

    private static miConexion conexion;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        conexion = new miConexion("jdbc:mariadb://localhost:3306/gp10_ulp", "root", ""); // constructor
        alumnoData al = new alumnoData(conexion);

        if (conexion.buscarConexion() != null) {
            JOptionPane.showMessageDialog(null, "Conectado correctamente a la base de datos.", "", JOptionPane.INFORMATION_MESSAGE);
        }

        al.guardarAlumno(new Alumno (33465789, "Altamirano" , "Karina", LocalDate.of(1988, 6, 14), true));
        al.guardarAlumno(new Alumno(44075064, "Antonacci", "Matías", LocalDate.of(2002, 3, 15), true));
        al.guardarAlumno(new Alumno(44437768, "Bequis", "Ezequiel", LocalDate.of(2002, 12, 13), true));
        al.guardarAlumno(new Alumno(44953830, "Quiroga", "Alejo", LocalDate.of(2003, 9, 12), true));
        al.guardarAlumno(new Alumno(31092801, "Dave", "Natalia", LocalDate.of(1984, 8, 9), true));
        
        al.mostrarAlumnos();
    }
}
