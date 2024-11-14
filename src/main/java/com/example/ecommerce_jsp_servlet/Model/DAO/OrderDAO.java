package com.example.ecommerce_jsp_servlet.Model.DAO;

import com.example.ecommerce_jsp_servlet.Model.Order;
import com.example.ecommerce_jsp_servlet.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDAO(Connection con) {
        this.con = con;
    }
    public boolean insertOrder(Order model){
        boolean result = false;

        try {
            query="insert into Orders (idProducts, idUsers, Quantity, Date) values(?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1,model.getId());
            pst.setInt(2,model.getUserId());
            pst.setInt(3,model.getQuantity());
            pst.setString(4,model.getDate());
            pst.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  result;
    }
    public List<Order> userOrders(int id){
        List<Order> list = new ArrayList<>();
        try {
            query="select * from Orders where idUsers=? order by Orders.idOrders desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            while(rs.next()){
                Order order = new Order();
                ProductDAO productDAO = new ProductDAO(this.con);
                int productId = rs.getInt("idProducts");
                Product product = productDAO.getSingleProduct(productId);
                order.setOrderId(rs.getInt("idOrders"));
                order.setId(productId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*rs.getInt("Quantity"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setDate(rs.getString("Date"));
                list.add(order);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public void cancelOrder(int id){
        try {
            query="delete from Orders where idOrders=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1,id);
            pst.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
