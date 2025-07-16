package com.mycompany.reprezentantavolvo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ReprezentantaVolvo {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:5432/postgres",
                "postgres.zejkokxtnrzexlrdgeyp",
                "hHe2D2jEPRXWrRVR")) {

            System.out.println("connection.isValid(0) = " + connection.isValid(0));  

            ClientiDAO dao = new ClientiDAO(connection);
            dao.insertClient("TESTNUME", "TESTLOC");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
