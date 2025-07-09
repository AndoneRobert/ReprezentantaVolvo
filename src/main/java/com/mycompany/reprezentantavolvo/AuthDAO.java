/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reprezentantavolvo;
import java.sql.*;
/**
 *
 * @author robii
 */
public class AuthDAO {
    private Connection conn;
    
    public AuthDAO(Connection conn){
        this.conn=conn;
    }
    
    public boolean auth(String username, String password){
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs =  ps.executeQuery();
            return rs.next();
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
