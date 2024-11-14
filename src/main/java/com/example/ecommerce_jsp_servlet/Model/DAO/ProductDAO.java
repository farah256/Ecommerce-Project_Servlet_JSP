package com.example.ecommerce_jsp_servlet.Model.DAO;

import com.example.ecommerce_jsp_servlet.Model.Cart;
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
    public  List<Cart> getCartProducts(ArrayList<Cart> cartList){
        List<Cart> products = new ArrayList<Cart>();
        try{
            if(cartList.size()>0){
                for(Cart item:cartList){
                    query="select * from Product where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1,item.getId());
                    rs = pst.executeQuery();
                    while(rs.next()){
                        Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setCategory(rs.getString("category"));
                        row.setPrice(rs.getFloat("price")*item.getQuantity());
                        row.setImage(rs.getString("image"));
                        row.setQuantity(item.getQuantity());
                        products.add(row);

                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return products;
    }
    public Product getSingleProduct(int id){
        Product row = null;
        try{
            query="select * from Product where id=?";
            pst=this.con.prepareStatement(query);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while (rs.next()){
                row = new Product();
                row.setId(rs.getInt("id"));
                row.setName(rs.getString("name"));
                row.setCategory(rs.getString("category"));
                row.setPrice(rs.getFloat("price"));
                row.setImage(rs.getString("image"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
    public  float getTotalCartPrice(ArrayList<Cart> cartList){
        float sum = 0;
        try{
            if(cartList.size()>0){
                for(Cart item:cartList){
                    query = "select price from Product where id=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1,item.getId());
                    rs = pst.executeQuery();
                    while(rs.next()){
                        sum+=rs.getFloat("price")* item.getQuantity();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sum;
    }

}
