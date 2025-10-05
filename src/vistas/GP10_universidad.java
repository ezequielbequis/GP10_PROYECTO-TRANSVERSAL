package vistas;

import entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
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

    //private AlumnoData alumnoData;    
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

        /*
        private int dni;
        private String apellido;
        private String nombre;
        private LocalDate fechaNac;
         private boolean estado;
         */
        Alumno alum = new Alumno(12223, "dfsdewwesf", "lofsdd", LocalDate.of(2023, 7, 9), true);
        
        al.guardarAlumno(alum);

        /*LocalDate fecha= LocalDate.now();
        Alumno alum = new Alumno (33465789, "Altamirano" , "Karina", LocalDate.now(), true);
        new GP10_universidad().conectar (alum);
        System.out.println("Alumno " + alum.getNombre() + " guardado con éxito");*/
    }

    /*void conectar (Alumno alum){
        
      //  Connection c2= ConexionS.buscarConexion();
        /*
        
        alumnoData = new AlumnoData (conexion); //alumno
        alumnoData.guardarAlumno(alum); //persistencia
        Alumno alu  = alumnoData.buscarAlumno(alum.getId());
        System.out.println("Datos: " + alu); 
    
    } */
}
