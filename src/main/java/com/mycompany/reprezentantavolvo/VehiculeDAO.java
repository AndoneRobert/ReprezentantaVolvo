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
public class VehiculeDAO {
    private Connection connection;
    
    public VehiculeDAO(Connection connection){
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

public List<Vehicule> cautaVehicule(String model, String motorizare, Integer codc) throws SQLException {
    List<Vehicule> vehiculeList = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT * FROM vehicule WHERE 1=1");
    List<Object> params = new ArrayList<>();

    if (model != null && !model.trim().isEmpty()) {
        query.append(" AND LOWER(model) LIKE LOWER(?)");
        params.add("%" + model.trim() + "%");
    }

    if (motorizare != null && !motorizare.trim().isEmpty()) {
        query.append(" AND LOWER(motorizare) LIKE LOWER(?)");
        params.add("%" + motorizare.trim() + "%");
    }

    if (codc != null) {
        query.append(" AND \"CODC\" = ?");
        params.add(codc);
    }

    PreparedStatement ps = connection.prepareStatement(query.toString());

    for (int i = 0; i < params.size(); i++) {
        Object param = params.get(i);
        if (param instanceof String) {
            ps.setString(i + 1, (String) param);
        } else if (param instanceof Integer) {
            ps.setInt(i + 1, (Integer) param);
        }
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
        String query = "INSERT INTO vehicule (model, motorizare, km, an, \"CODC\") VALUES (?, ?, ?, ?, ?)";
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
        String query = "UPDATE vehicule SET model = ?, motorizare = ?, km = ?, an = ?, \"CODC\" = ? WHERE \"CODVEH\" = ?";
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
