/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.connections.DBConnections;
import com.models.Product;
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
public class ProductDAO {

    private Connection conn;

    public ProductDAO() {
        conn = DBConnections.getConnection();
    }

    public ResultSet getAllProduct() {
        ResultSet resultSet = null;
        String query = "SELECT * FROM `product` "
                + "LEFT OUTER JOIN product_category "
                + "ON product.category_id = product_category.catagory_id";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(query);
            resultSet = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;

    }

    public String usingSubstringMethod(String text, int length) {
        if (text.length() <= length) {
            return text;
        } else {
            return text.substring(0, length) + "...";
        }
    }

    public Product getAllByID(String product_id) {
        Product product = null;

        String query = "SELECT * FROM product WHERE product_id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getString("product_id"), rs.getString("product_name"),
                        rs.getFloat("selling_price"), rs.getString("category_id"),
                        rs.getString("product_desc"), rs.getInt("quantity"), rs.getString("image"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return product;
    }

    public int UpdateProduct(Product product) {
        int count = 0;

        String query = "UPDATE product"
                + " SET product_name=?,selling_price=?,category_id=?,"
                + "product_desc=?,quantity=?,image=? "
                + "WHERE product_id=?";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getProduct_name());
            pst.setFloat(2, product.getSelling_price());
            pst.setString(3, product.getCategory_id());
            pst.setString(4, product.getProduct_desc());
            pst.setInt(5, product.getQuantity());
            pst.setString(6, product.getImage());
            pst.setString(7, product.getProduct_id());
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public int InsertProduct(Product product) {
        int count = 0;

        String query = "INSERT INTO product"
                + " (product_id,product_name,selling_price,category_id,"
                + "product_desc,quantity,image) "
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getProduct_id());
            pst.setString(2, product.getProduct_name());
            pst.setFloat(3, product.getSelling_price());
            pst.setString(4, product.getCategory_id());
            pst.setString(5, product.getProduct_desc());
            pst.setInt(6, product.getQuantity());
            pst.setString(7, product.getImage());
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }
    
    
    public int deleteProduct(String product_id){
        int count = 0;
        String query = "DELETE FROM product WHERE product_id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product_id);
            count = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }

}
