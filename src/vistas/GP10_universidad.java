package vistas;

import entidades.Alumno;
import entidades.Inscripcion;
import entidades.Materia;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistencia.alumnoData;
import persistencia.inscripcionData;
import persistencia.materiaData;
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
        materiaData ma = new materiaData(conexion);
        inscripcionData insc = new inscripcionData(conexion);
        
        /*---------- Pruebas -------------
        
        al.preCargarAlumnos();
        
        ArrayList<Alumno> x = new ArrayList<>();
        x = al.listarAlumnos();
        
        for (Alumno aux : x) {
            al.darBajaAlumno(aux.getIdAlumno());
        }
        
        for (Alumno aux : x) {
            System.out.println("----------------------------------");
            System.out.println("ID: " + aux.getIdAlumno() + "\nDNI: " + aux.getDni() +"\nApellido y nombre: " + aux.getApellido() + " " + aux.getNombre() 
                    + "\nFecha de nacimiento: " + aux.getFechaNac() + "\nEstado: " + aux.getEstado());
            System.out.println("----------------------------------");
        }
        
        Alumno aux = al.buscarAlumnoPorId(50);
        
        System.out.println("----------------------------------");
            System.out.println("ID: " + aux.getIdAlumno() + "\nDNI: " + aux.getDni() +"\nApellido y nombre: " + aux.getApellido() + " " + aux.getNombre() 
                    + "\nFecha de nacimiento: " + aux.getFechaNac() + "\nEstado: " + aux.getEstado());
            System.out.println("----------------------------------");
        */
        
        
        /*---------- Pruebas INSCRIPCION DATA -------------
        
        ---------- OBTENER TODAS LAS INSCRIPCIONES -------------
        ArrayList<Inscripcion> lista = new ArrayList<>();
        
        lista = insc.obtenerInscripciones();
        
        for (Inscripcion aux : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID Inscripto: " + aux.getIdInscripto() + "\nNota: " + aux.getNota() + "\nidAlumno: " + aux.getIdAlumno() + "\nidMateria: " + aux.getIdMateria());
            System.out.println("\n----------------------------------");
        }
        */
        
        /*---------- Pruebas INSCRIPCION DATA -------------
        
        ---------- OBTENER TODAS LAS INSCRIPCIONES DE UN ALUMNO POR ID -------------
        ArrayList<Inscripcion> lista = new ArrayList<>();
        
        lista = insc.obtenerInscripcionesPorAlumno(1);
        
        for (Inscripcion aux : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID Inscripto: " + aux.getIdInscripto() + "\nNota: " + aux.getNota() + "\nidAlumno: " + aux.getIdAlumno() + "\nidMateria: " + aux.getIdMateria());
            System.out.println("\n----------------------------------");
        }
        */
        
        /*---------- Pruebas INSCRIPCION DATA -------------
        
        ---------- OBTENER TODAS LAS MATERIAS QUE CURSA EL ALUMNO-------------
        ArrayList<Materia> lista = new ArrayList<>();
        
        lista = insc.obtenerMateriasCursadas(1);
        
        for (Materia aux : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID Materia: " + aux.getIdMateria() + "\nNombre: " + aux.getNombre() + "\nAño: " + aux.getAnio() + "\nEstado: " + aux.getEstado());
            System.out.println("\n----------------------------------");
        }
        */
        
        /*---------- Pruebas INSCRIPCION DATA -------------
        
        ---------- OBTENER TODAS LAS MATERIAS EN LAS QUE NO ESTA EL ALUMNO -------------
        ArrayList<Materia> lista = new ArrayList<>();
        
        lista = insc.obtenerMateriasNOCursadas(1);
        
        for (Materia aux : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID Materia: " + aux.getIdMateria() + "\nNombre: " + aux.getNombre() + "\nAño: " + aux.getAnio() + "\nEstado: " + aux.getEstado());
            System.out.println("\n----------------------------------");
        }
        */
        
        /*---------- Pruebas INSCRIPCION DATA -------------
        
        ---------- OBTENER TODOS LOS ALUMNOS POR IDMATERIA------------- */ /*
        ArrayList<Alumno> lista = new ArrayList<>();
        
        lista = insc.obtenerAlumnosPorMateria(2);
        
        for (Alumno aux : lista) {
            System.out.println("----------------------------------");
            System.out.println("ID: " + aux.getIdAlumno() + "\nDNI: " + aux.getDni() +"\nApellido y nombre: " + aux.getApellido() + " " + aux.getNombre() 
                    + "\nFecha de nacimiento: " + aux.getFechaNac() + "\nEstado: " + aux.getEstado());
            System.out.println("----------------------------------");
        }
        */
        

    }
}
