package sql;
import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {   
        public static Connection getConexion() {

        Connection con = null;
        try {
           
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "videoclub","videoclub");
        }catch (Exception e) {
            System.out.println("Error SQL : "+e.getMessage());
        }
        return con;
    }
    
}