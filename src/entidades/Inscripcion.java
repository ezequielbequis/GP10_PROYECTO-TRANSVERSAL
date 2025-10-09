/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
/**
 * @author Grupo10 
 * Altamirano Karina
 * Gianfranco Antonacci Matías
 * Bequis Marcos Ezequiel
 * Dave  Natalia
 * Quiroga Dorzan Alejo
 */
public class Inscripcion {
    private int idInscripto = -1;
    private int nota;
    private int idAlumno;
    private int idMateria;

    // Constructores
    public Inscripcion() {}

    public Inscripcion(int nota, int idAlumno, int idMateria) {
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public Inscripcion(int idInscripto, int nota, int idAlumno, int idMateria) {
        this.idInscripto = idInscripto;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    // Getters y Setters

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
   

    @Override
    public String toString() {
        return "Inscripcion{" + 
                "idInscripto=" + idInscripto + 
                ", nota=" + nota + 
                ", alumno=" + idAlumno + 
                ", materia=" + idAlumno + 
                '}';
    }

}
