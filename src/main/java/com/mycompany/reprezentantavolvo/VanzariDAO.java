/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robii
 */
public class VanzariDAO {
    private Connection connection;
    
    public VanzariDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Vehicule> getAllVehicule() throws SQLException {
        List<Vehicule> vehiculeList = new ArrayList<>();
        String query = "SELECT * FROM vehicule";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int codveh = rs.getInt("codveh");
            String model = rs.getString("model");
            String motorizare = rs.getString("motorizare");
            int km = rs.getInt("km");
            int an =  rs.getInt("an");
            int codc = rs.getInt("codc");
            vehiculeList.add(new Vehicule(codveh, model, motorizare, km, an, codc));
        }

        rs.close();
        ps.close();
        return vehiculeList;
    }

    public List<Vehicule> cautaVehicule(String model, String motorizare, int codc) throws SQLException {
        List<Vehicule> vehiculeList = new ArrayList<>();
        String query = "SELECT * FROM stoc WHERE 1=1";
        List<String> params = new ArrayList<>();

        if (model != null && !model.trim().isEmpty()) {
            query += " AND LOWER(nume) LIKE LOWER(?)";
            params.add("%" + model.trim() + "%");
        }

        if (codc != 0) {
            params.add("%" + codc + "%");
        }
        

        PreparedStatement ps = connection.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            ps.setString(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int codvehGasit = rs.getInt("codveh");
            String modelGasit = rs.getString("model");
            String motorizareGasit = rs.getString("motorizare");
            int kmGasit = rs.getInt("km");
            int anGasit = rs.getInt("an");
            int codcGasit = rs.getInt("codc");
            vehiculeList.add(new Vehicule(codvehGasit, modelGasit, motorizareGasit, kmGasit, anGasit, codcGasit));
        }

        rs.close();
        ps.close();
        return vehiculeList;
    }
    


    public void insertVehicule(String model, String motorizare, int km, int an, int codc) throws SQLException {
        String query = "INSERT INTO vehicule (model, motorizare, km, an, codc) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, model);
        ps.setString(2, motorizare);
        ps.setInt(3, km);
        ps.setInt(4, an);
        ps.setInt(5, codc);
        ps.executeUpdate();
        ps.close();
    }

    public void updateVehicule(int codveh, String model, String motorizare, int km, int an, int codc) throws SQLException {
        String query = "UPDATE vehicule SET model = ?, motorizare = ?, km = ?, an = ?, codc = ? WHERE \"CODVEH\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, model);
        ps.setString(2, motorizare);
        ps.setInt(3, km);
        ps.setInt(4, an);
        ps.setInt(5, codc);
        ps.setInt(6, codveh);
        ps.executeUpdate();
        ps.close();
    }

        public void deleteVehicule(int codveh) throws SQLException {
        String query = "DELETE FROM vehicule WHERE \"CODVEH\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, codveh);
        ps.executeUpdate();
        ps.close();
    }
}
