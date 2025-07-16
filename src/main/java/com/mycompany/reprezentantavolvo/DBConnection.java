package com.mycompany.reprezentantavolvo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.zejkokxtnrzexlrdgeyp";
    private static final String PASSWORD = "hHe2D2jEPRXWrRVR";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
