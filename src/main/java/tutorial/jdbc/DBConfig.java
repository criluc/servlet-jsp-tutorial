/**
 *
 */
package tutorial.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author cristian
 *
 */
public class DBConfig {

  public final static String DB_URL = "jdbc:h2:~/corsojava";
  public final static String DB_USER = "sa";
  public final static String DB_PASSWORD = "";

  public static Connection getConnection() throws SQLException {
    try {
      //Necessario per far si che Tomcat carichi il driver JDBC correttamente
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Driver h2 not found");
    }
    return DriverManager.getConnection(
        DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
  }

}
