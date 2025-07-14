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
    
public void insertVanzari(int codm, int codc) throws SQLException {
        String query = "INSERT INTO vanzari (\"CODM\", \"CODC\", datav) VALUES (?, ?, CURRENT_DATE)";
        PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, codm);
            ps.setInt(2, codc);
            ps.executeUpdate();
            ps.close();
    }
}
