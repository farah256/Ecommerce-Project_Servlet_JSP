package com.example.ecommerce_jsp_servlet.Model.Servlet;

import com.example.ecommerce_jsp_servlet.Connection.DBConnection;
import com.example.ecommerce_jsp_servlet.Model.Cart;
import com.example.ecommerce_jsp_servlet.Model.DAO.OrderDAO;
import com.example.ecommerce_jsp_servlet.Model.Order;
import com.example.ecommerce_jsp_servlet.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()){
            SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
            User auth = (User) req.getSession().getAttribute("auth");
            if(cart_list!=null && auth != null){
                for(Cart c:cart_list){
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUserId(auth.getId());
                    order.setQuantity(c.getQuantity());
                    order.setDate(formatt.format(date));
                    OrderDAO orderDAO = new OrderDAO(DBConnection.getConnection());
                    boolean result = orderDAO.insertOrder(order);
                    if(!result)break;
                }
                cart_list.clear();
                resp.sendRedirect("orders.jsp");
            }else {
                if(auth == null){
                    resp.sendRedirect("login.jsp");
                }else {
                    resp.sendRedirect("cart.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
