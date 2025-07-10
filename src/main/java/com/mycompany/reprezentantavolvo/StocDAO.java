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
public class StocDAO {
    private Connection connection;
    
    public StocDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Stoc> getAllStoc() throws SQLException {
        List<Stoc> stocList = new ArrayList<>();
        String query = "SELECT * FROM stoc";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int codm = rs.getInt("codm");
            String model = rs.getString("model");
            int an = rs.getInt("an");
            String motorizare = rs.getString("motorizare");
            String culoare = rs.getString("culoare");
            int km = rs.getInt("km");
            String starea = rs.getString("starea");
            int pret = rs.getInt("pret");
            String tip = rs.getString("tip");
            stocList.add(new Stoc(codm, model, an, motorizare, culoare, km, starea, pret, tip));
        }

        rs.close();
        ps.close();
        return stocList;
    }

    public List<Stoc> cautaStoc(String model, String motorizare) throws SQLException {
        List<Stoc> stocList = new ArrayList<>();
        String query = "SELECT * FROM stoc WHERE 1=1";
        List<String> params = new ArrayList<>();

        if (model != null && !model.trim().isEmpty()) {
            query += " AND LOWER(nume) LIKE LOWER(?)";
            params.add("%" + model.trim() + "%");
        }

        if (motorizare != null && !motorizare.trim().isEmpty()) {
            query += " AND LOWER(localitate) LIKE LOWER(?)";
            params.add("%" + motorizare.trim() + "%");
        }

        PreparedStatement ps = connection.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            ps.setString(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int codmGasit = rs.getInt("codm");
            String modelGasit = rs.getString("model");
            int anGasit = rs.getInt("an");
            String motorizareGasit = rs.getString("motorizare");
            String culoareGasit = rs.getString("culoare");
            int kmGasit = rs.getInt("km");
            String stareaGasit = rs.getString("starea");
            int pretGasit = rs.getInt("pret");
            String tipGasit = rs.getString("tip");
            stocList.add(new Stoc(codmGasit, modelGasit, anGasit, motorizareGasit, culoareGasit, kmGasit, stareaGasit, pretGasit, tipGasit));
        }

        rs.close();
        ps.close();
        return stocList;
    }
    


    public void insertStoc(String model, int an, String motorizare, String culoare, int km, String starea, int pret, String tip) throws SQLException {
        String query = "INSERT INTO stoc (model, an, motorizare, culoare, km, starea, pret, tip) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, model);
        ps.setInt(2, an);
        ps.setString(3, motorizare);
        ps.setString(4, culoare);
        ps.setInt(5, km);
        ps.setString(6, starea);
        ps.setInt(7, pret);
        ps.setString(8, tip);
        ps.executeUpdate();
        ps.close();
    }

    public void updateStoc(int codm, String model, int an, String motorizare, String culoare, int km, String starea, int pret, String tip) throws SQLException {
        String query = "UPDATE clienti SET model = ?, an = ?, motorizare = ?, culoare = ?, km = ?, starea = ?, pret = ?, tip =? WHERE \"CODM\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, model);
        ps.setInt(2, an);
        ps.setString(3, motorizare);
        ps.setString(4, culoare);
        ps.setInt(5, km);
        ps.setString(6, starea);
        ps.setInt(7, pret);
        ps.setString(8, tip);
        ps.setInt(9, codm);
        ps.executeUpdate();
        ps.close();
    }

        public void deleteStoc(int codm) throws SQLException {
        String query = "DELETE FROM clienti WHERE \"CODM\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, codm);
        ps.executeUpdate();
        ps.close();
    }
}
