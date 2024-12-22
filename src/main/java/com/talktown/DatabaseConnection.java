package com.talktown;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static final String JDBC_URL = "jdbc:postgresql://postgres-tt.craoiwu6kgeo.ap-northeast-1.rds.amazonaws.com:5432/postgres";
    private static final String DB_USER = "tomonyal";
    private static final String DB_PASSWORD = "bAlsing1224";

    private DatabaseConnection() {
        // ドライバーのロード
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}