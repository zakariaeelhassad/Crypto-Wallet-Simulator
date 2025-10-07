package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class JdbcConnection {

    private static JdbcConnection INSTANCE = null;
    private static Connection CONNECTION = null;

    private String url;
    private String username;
    private String password;

    private JdbcConnection() {
        loadProperties();
        initConnection();
    }

    public static JdbcConnection getInstance() {
        try {
            if (INSTANCE == null || INSTANCE.getConnection() == null || INSTANCE.getConnection().isClosed()) {
                INSTANCE = new JdbcConnection();
            }
            return INSTANCE;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return CONNECTION;
    }

    public static void closeConnection() {
        if (CONNECTION != null) {
            try {
                CONNECTION.close();
                INSTANCE = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadProperties() {
        try (InputStream input = new java.io.FileInputStream("src/config/db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("db.url");
            username = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("Erreur f lecture dyal properties", e);
        }
    }


    private void initConnection() {
        try {
            CONNECTION = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur f connexion DB", e);
        }
    }
}
