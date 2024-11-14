package com.example.ecommerce_jsp_servlet.Model.Servlet;

import com.example.ecommerce_jsp_servlet.Model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/quantity")
public class QuantityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()){
            String action = req.getParameter("action");
            int id = Integer.parseInt(req.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) req.getSession().getAttribute("cart-list");
            if(action != null && id>=1){
                if(action.equals("inc")){
                    for(Cart c:cart_list){
                        if(c.getId() == id){
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            resp.sendRedirect("cart.jsp");

                        }
                    }
                }
                if(action.equals("dec")){
                    for(Cart c:cart_list){
                        if(c.getId() == id && c.getQuantity()>1){
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;

                        }
                    }
                    resp.sendRedirect("cart.jsp");
                }
            }else{
                resp.sendRedirect("cart.jsp");

            }

        }
    }
}
