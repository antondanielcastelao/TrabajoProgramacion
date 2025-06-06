import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase conecta con superbase para manejar los datos remotamente
 */
public class DBConnection {

    private static final String URL = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres";
    private static final String USER = "postgres.klglbcwivvdbefwujolf";
    private static final String PASSWORD = "postgres"; //

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}