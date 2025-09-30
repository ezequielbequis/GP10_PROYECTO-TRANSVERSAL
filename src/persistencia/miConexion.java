package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author matia
 */
public class miConexion {
    private String url;
    private String user;
    private String password;
    
    private static Connection conexion = null; 

    //Constructor para la conexion a la base con la url, su usuaio y su contraseña.
    public miConexion(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    //Establecer y guardar la conexión.
    public Connection buscarConexion(){
        if(conexion == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url,user,password);
            } catch (SQLException | ClassNotFoundException ex) {
                System.out.println("No se conecto a la Base de datos o no sepuede cargar el Driver.");
            }
        }
        return conexion;
    }
    
}
