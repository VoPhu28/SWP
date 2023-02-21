/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.connections.DBConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class CateogoryDAO {
    private Connection conn;

    public CateogoryDAO() {
        conn =  DBConnections.getConnection();
    }
    
    public ResultSet getAllCategory(){
        ResultSet resultSet = null;
        
        String query = "SELECT * FROM product_category";
        
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            resultSet = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CateogoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return resultSet;
    }
    
    
}
