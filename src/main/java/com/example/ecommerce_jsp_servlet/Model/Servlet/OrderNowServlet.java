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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){
            SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) req.getSession().getAttribute("auth");
            if(auth != null){
                String productId = req.getParameter("id");
                int productQuantity = Integer.parseInt(req.getParameter("quantity"));
                if(productQuantity <=0){
                    productQuantity = 1;
                }
                Order order = new Order();
                order.setId(Integer.parseInt(productId));
                order.setUserId(auth.getId());
                order.setQuantity(productQuantity);
                order.setDate(formatt.format(date));
                OrderDAO orderDAO = new OrderDAO(DBConnection.getConnection());
                boolean result = orderDAO.insertOrder(order);
                if(result){
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
                    if(cart_list != null){
                        for(Cart c:cart_list){
                            if(c.getId()==Integer.parseInt(productId)){
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }
                    resp.sendRedirect("orders.jsp");
                }else {
                    out.print("Order failed");
                }

            }else {
                resp.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
