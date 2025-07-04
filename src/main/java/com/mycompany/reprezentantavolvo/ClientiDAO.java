/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author robii
 */
public class ClientiDAO {
    private Connection connection;
    
    public ClientiDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Clienti> cautaClientiDupaNume(String nume) throws SQLException{
        List<Clienti> rezultat = new ArrayList<>();
        String query = "SELECT * FROM clienti WHERE nume = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nume);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()){
            int codc = rs.getInt("CODC");
            String localitate = rs.getString("localitate");
            Clienti c = new Clienti(codc, nume, localitate);
            rezultat.add(c);
        }
        rs.close();
        ps.close();
        return rezultat;
    }
}
