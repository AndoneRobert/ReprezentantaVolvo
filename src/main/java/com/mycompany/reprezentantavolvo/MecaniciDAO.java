/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

import java.sql.*;
import java.util.*;
/**
 *
 * @author robii
 */
public class MecaniciDAO {
    private Connection connection;
    
    public MecaniciDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Mecanici> getAllMecanici() throws SQLException{
        List<Mecanici> mecaniciList = new ArrayList<>();
        String query = "SELECT * FROM mecanici";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            int codmec = rs.getInt("codmec");
            String nume = rs.getString("nume");
            String specializare = rs.getString("specializare");
            mecaniciList.add(new Mecanici(codmec, nume, specializare));
        }
        rs.close();
        ps.close();
        return mecaniciList;
    }
    
    public List<Mecanici> cautaMecanici(String nume) throws SQLException {
    List<Mecanici> mecaniciList = new ArrayList<>();
    String query = "SELECT * FROM mecanici WHERE 1=1";
    List<String> params = new ArrayList<>();
    
    if(nume != null && !nume.trim().isEmpty()){
        query += " AND LOWER(nume) LIKE LOWER(?)";
        params.add("%" +nume.trim() + "%");
    }
    
    PreparedStatement ps = connection.prepareStatement(query);
    for (int i = 0; i < params.size(); i++){
        ps.setString(i+1, params.get(i));
    }
    
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        int codmecGasit = rs.getInt("codmec");
        String numeGasit = rs.getString("nume");
        String specializareGasit = rs.getString("specializare");
        mecaniciList.add(new Mecanici(codmecGasit, numeGasit, specializareGasit));
    }

    rs.close();
    ps.close();
    return mecaniciList;
}

    
    public void insertMecanici(String nume, String specializare) throws SQLException {
        String query = "INSERT INTO mecanici (nume, specializare) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nume);
        ps.setString(2, specializare);
        ps.executeUpdate();
        ps.close();
    }

    public void updateMecanici(int codmec, String nume, String specializare) throws SQLException {
        String query = "UPDATE mecanici SET nume = ?, specializare = ? WHERE \"CODMEC\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nume);
        ps.setString(2, specializare);
        ps.setInt(3, codmec);
        ps.executeUpdate();
        ps.close();
    }

        public void deleteMecanici(int codmec) throws SQLException {
        String query = "DELETE FROM mecanici WHERE \"CODMEC\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, codmec);
        ps.executeUpdate();
        ps.close();
    }
}
