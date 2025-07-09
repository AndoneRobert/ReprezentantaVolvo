package com.mycompany.reprezentantavolvo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReprezentantaVolvo {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Volvo", "postgres", "password")){
            System.out.println("connection.isValid(0) = " + connection.isValid(0));  
            
        ClientiDAO dao = new ClientiDAO(connection);
        dao.insertClient("TESTNUME", "TESTLOC");
        } catch (SQLException e){
            e.printStackTrace();
        }
        

    }
}
