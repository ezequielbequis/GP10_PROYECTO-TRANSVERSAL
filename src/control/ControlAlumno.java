package control;

import entidades.Alumno;
import persistencia.alumnoData;
import vistas.VistaGestionAlumno;

public class ControlAlumno {
    alumnoData alumData = null;
    VistaGestionAlumno vistaAlumno = null;
    Alumno alumno = null;
    
    public void mostrarAlumno() {
        vistaAlumno.getId();
    }
    
    public void crearAlumno() {
        
    }
}
