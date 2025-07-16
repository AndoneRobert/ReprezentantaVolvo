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
public class ServiceDAO {
    private Connection connection;
    
    public ServiceDAO(Connection connection){
        this.connection = connection;
    }
    
    public List<Service> getAllService() throws SQLException {
        List<Service> serviceList = new ArrayList<>();
        String query = "SELECT * FROM service";
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int cods = rs.getInt("cods");
            String diagnostic = rs.getString("diagnostic");
            int cost = rs.getInt("cost");
            int codveh = rs.getInt("codveh");
            int codmec = rs.getInt("codmec");
            serviceList.add(new Service(cods, diagnostic, cost, codveh, codmec));
        }

        rs.close();
        ps.close();
        return serviceList;
    }

    public List<Service> cautaService(Integer codveh) throws SQLException {
    List<Service> serviceList = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT * FROM service WHERE 1=1");

    PreparedStatement ps;

    if (codveh != null) {
        query.append(" AND \"CODVEH\" = ?");
        ps = connection.prepareStatement(query.toString());
        ps.setInt(1, codveh);
    } else {
        ps = connection.prepareStatement(query.toString());
    }

    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        int codsGasit = rs.getInt("cods");
        String diagnosticGasit = rs.getString("diagnostic");
        int costGasit = rs.getInt("cost");
        int codvehGasit = rs.getInt("codveh");
        int codmecGasit = rs.getInt("codmec");
        serviceList.add(new Service(codsGasit, diagnosticGasit, costGasit, codvehGasit, codmecGasit));
    }

    rs.close();
    ps.close();
    return serviceList;
}

    public void insertService(String diagnostic, int cost, int codveh, int codmec) throws SQLException {
        String query = "INSERT INTO service (diagnostic, cost, \"CODVEH\", \"CODMEC\") VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, diagnostic);
        ps.setInt(2, cost);
        ps.setInt(3, codveh);
        ps.setInt(4, codmec);
        ps.executeUpdate();
        ps.close();
    }

    public void updateService(int cods, String diagnostic, int cost, int codveh, int codmec) throws SQLException {
        String query = "UPDATE service SET diagnostic = ?, cost = ?, \"CODVEH\" = ?, \"CODMEC\" = ? WHERE \"CODS\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, diagnostic);
        ps.setInt(2, cost);
        ps.setInt(3, codveh);
        ps.setInt(4, codmec);
        ps.setInt(5, cods);
        ps.executeUpdate();
        ps.close();
    }

        public void deleteService(int cods) throws SQLException {
        String query = "DELETE FROM service WHERE \"CODS\" = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, cods);
        ps.executeUpdate();
        ps.close();
    }
}