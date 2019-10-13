package Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PersistanteManager implements AutoCloseable {

    private static final String PROPS_FILE = "./ressources/db.properties";

    private static Connection connection;

    private PersistanteManager(){}//Prevents initialization

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {

        if ( connection == null || !connection.isValid( 2 )) {

            Properties props = new Properties();
            try ( FileInputStream fis = new FileInputStream( PROPS_FILE ) ) {
                props.load( fis );
            }

            String driverClass = props.getProperty( "jdbc.class.driver" );
            String dbURL = props.getProperty( "jdbc.db.url" );
            String dbLogin = props.getProperty( "jdbc.db.login" );
            String dbPWD = props.getProperty( "jdbc.db.pwd" );

            Class.forName( driverClass );
            connection = DriverManager.getConnection( dbURL, dbLogin, dbPWD );
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && connection.isValid( 2 )) {
            connection.close();
        }
    }
}
