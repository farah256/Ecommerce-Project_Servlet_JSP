package com.example.ecommerce_jsp_servlet.Model.Servlet;

import com.example.ecommerce_jsp_servlet.Model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/add-to-cart")
public class AddtoCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()){
            ArrayList<Cart> carts = new ArrayList<>();
            int id = Integer.parseInt(req.getParameter("id"));
            Cart cart = new Cart();
            cart.setId(id);
            cart.setQuantity(1);

            HttpSession session = req.getSession();
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            if(cart_list == null){
                carts.add(cart);
                session.setAttribute("cart-list",carts);
                resp.sendRedirect("index.jsp");
            }else {
                carts = cart_list;
                boolean exist = false;


                for(Cart c:cart_list ){
                    if(c.getId()==id){
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align:center'>Item already exist in Cart.<a href='cart.jsp'>Go to Cart Page</a></h3>");
                    }
                }
                if(!exist){
                    carts.add(cart);
                    resp.sendRedirect("index.jsp");
                }
            }

        }
    }
}
