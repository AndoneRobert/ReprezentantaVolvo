/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;

import java.sql.Connection;
import java.sql.Date;
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
    
    public List<Vanzari> getAllVanzari() throws SQLException {
        List<Vanzari> vanzariList = new ArrayList<>();
        String query = "SELECT * FROM vanzari";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int codv = rs.getInt("codv");
            Date datav = rs.getDate("datav");
            int codm = rs.getInt("codm");
            int codc = rs.getInt("codc");
            vanzariList.add(new Vanzari(codv, datav, codm, codc));
        }

        rs.close();
        ps.close();
        return vanzariList;
    }

    public void insertVehicule(Date datav, int CODM, int CODC) throws SQLException {
        String query = "INSERT INTO vehicule (datav, CODM, CODC) VALUES (?, ?, ?); DELETE FROM stoc WHERE \"CODM\" =?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setDate(1, datav);
        ps.setInt(2, CODM);
        ps.setInt(3, CODC);
        ps.setInt(4, CODM);
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
