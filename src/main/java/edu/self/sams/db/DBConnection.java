package edu.self.sams.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private final String username = "root";
    private final String password = "1234";
    private final String url = "jdbc:mysql://localhost:3306/attendance_management";

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DBConnection getInstance() throws SQLException {
        return dbConnection != null ? dbConnection : new DBConnection();
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

}
