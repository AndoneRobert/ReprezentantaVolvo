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
    
    public List<Clienti> getAllClienti() throws SQLException {
        List<Clienti> clientiList = new ArrayList<>();
        String query = "SELECT * FROM clienti";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int codc = rs.getInt("codc");
            String nume = rs.getString("nume");
            String localitate = rs.getString("localitate");
            clientiList.add(new Clienti(codc, nume, localitate));
        }

        rs.close();
        ps.close();
        return clientiList;
    }

    public List<Clienti> cautaClienti(String nume, String localitate) throws SQLException {
        List<Clienti> clientiList = new ArrayList<>();
        String query = "SELECT * FROM clienti WHERE 1=1";
        List<String> params = new ArrayList<>();

        if (nume != null && !nume.trim().isEmpty()) {
            query += " AND LOWER(nume) LIKE LOWER(?)";
            params.add("%" + nume.trim() + "%");
        }

        if (localitate != null && !localitate.trim().isEmpty()) {
            query += " AND LOWER(localitate) LIKE LOWER(?)";
            params.add("%" + localitate.trim() + "%");
        }

        PreparedStatement ps = connection.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            ps.setString(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int codc = rs.getInt("codc");
            String foundNume = rs.getString("nume");
            String foundLocalitate = rs.getString("localitate");
            clientiList.add(new Clienti(codc, foundNume, foundLocalitate));
        }

        rs.close();
        ps.close();
        return clientiList;
    }
    


    public void insertClient(String nume, String localitate) throws SQLException {
        String query = "INSERT INTO clienti (nume, localitate) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nume);
        ps.setString(2, localitate);
        ps.executeUpdate();
        ps.close();
    }

    public void updateClient(int codc, String nume, String localitate) throws SQLException {
        String query = "UPDATE clienti SET nume = ?, localitate = ? WHERE codc = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nume);
        ps.setString(2, localitate);
        ps.setInt(3, codc);
        ps.executeUpdate();
        ps.close();
    }

        public void deleteClient(int codc) throws SQLException {
        String query = "DELETE FROM clienti WHERE codc = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, codc);
        ps.executeUpdate();
        ps.close();
    }

}
