package entidades;

/**
 * @author Grupo10 
 * Altamirano Karina
 * Gianfranco Antonacci Matías
 * Bequis Marcos Ezequiel
 * Dave  Natalia
 * Quiroga Dorzan Alejo
 */
public class Materia {
    private int idMateria = -1;
    private String nombre;
    private int anio;
    private boolean estado;
    
    //Constructores
    public Materia() {}

    public Materia(String nombre, int anio, boolean estado) {
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }
    
    public Materia(int idMateria, String nombre, int anio, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }
    
    
    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    
    // ToString
    @Override
    public String toString() {
        return "Materia: " + "idMateria=" + idMateria + ", nombre=" + nombre + ", anio=" + anio + ", estado=" + estado;
    }
}
