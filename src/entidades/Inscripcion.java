/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


/**
 *
 * @author alejo
 */
   

public class Inscripcion {
    private int idInscripto;
    private int nota;
    private Alumno alumno;
    private Materia materia;

    // Constructores
    public Inscripcion() {}

    public Inscripcion(int nota, Alumno alumno, Materia materia) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public Inscripcion(int idInscripto, int nota, Alumno alumno, Materia materia) {
        this.idInscripto = idInscripto;
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    // Getters y Setters
    public int getIdInscripto() { return idInscripto; }
    public void setIdInscripto(int idInscripto) { this.idInscripto = idInscripto; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }

    public Materia getMateria() { return materia; }
    public void setMateria(Materia materia) { this.materia = materia; }

    @Override
    public String toString() {
        return "Inscripcion{" + 
                "idInscripto=" + idInscripto + 
                ", nota=" + nota + 
                ", alumno=" + alumno + 
                ", materia=" + materia + 
                '}';
    }

}
