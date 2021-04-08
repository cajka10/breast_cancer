package Core;

import java.sql.*;

public class DbConnection {

    private Connection dbConnection;

    public Connection getDbConnection() {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/breast_cancer";
        String username = "postgres";
        String passw = "lokca258";
        try {
            dbConnection = DriverManager.getConnection(jdbcUrl, username, passw);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
           dbConnection = null;
        }
        return dbConnection;
    }
}
