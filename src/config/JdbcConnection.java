//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static JdbcConnection INSTANCE = null;
    private static Connection CONNECTION = null;
    private final String url = "jdbc:postgresql://localhost:5432/crypto_wallet";
    private final String username = "postgres";
    private final String password = "zakaria";

    private JdbcConnection() {
        this.initConnection();
    }

    public static JdbcConnection getInstance() {
        try {
            if (INSTANCE == null || INSTANCE.getConnection() == null || INSTANCE.getConnection().isClosed()) {
                INSTANCE = new JdbcConnection();
            }

            return INSTANCE;
        } catch (Exception var1) {
            throw new RuntimeException(var1);
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
            } catch (SQLException var1) {
                throw new RuntimeException(var1);
            }
        }

    }

    private void initConnection() {
        try {
            CONNECTION = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crypto_wallet", "postgres", "zakaria");
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }
}
