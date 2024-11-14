package com.example.ecommerce_jsp_servlet.Model.Servlet;

import com.example.ecommerce_jsp_servlet.Connection.DBConnection;
import com.example.ecommerce_jsp_servlet.Model.DAO.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter out = resp.getWriter()){
            String id = req.getParameter("id");
            if(id!=null){
                OrderDAO orderDAO = new OrderDAO(DBConnection.getConnection());
                orderDAO.cancelOrder(Integer.parseInt(id));
            }
            resp.sendRedirect("orders.jsp");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
