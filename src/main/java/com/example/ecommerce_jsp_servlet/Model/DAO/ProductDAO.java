package com.example.ecommerce_jsp_servlet.Model.DAO;

import com.example.ecommerce_jsp_servlet.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public ProductDAO(Connection con) {
        this.con = con;
    }
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<Product>();
        try {
            query = "select * from Product";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()){
                Product row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getFloat("price"));
                row.setImage(rs.getString("image"));
                products.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
