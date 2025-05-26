package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Hernan Medina
 */
public class ConexionDB {
    private final String db_nombre;
    private final String user;
    private final String password;
    private final String url;
    
    Connection conexion = null;
    
    public ConexionDB(){
        db_nombre = "vehiculosDB";
        user = "root";
        password = "univalle";
        url = "jdbc:mysql://localhost:3306/"+this.db_nombre;
    }
    
    public Connection obtenerConexion(){
        
        if (conexion == null) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(this.url, this.user, this.password);
                
            } catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "No se encontró el driver JDBC ");
                System.out.println("Ha ocurrido un ClassNotFoundException "+e.getMessage());
                
            } catch (SQLException ex){
                 JOptionPane.showMessageDialog(null, "Ocurrio un error en la conexion a la base de datos. ");
                 System.out.println("Ha ocurrido un SQLException "+ex.getMessage());
                 
            }
        }
        return conexion;
    }
}
