<%@ page import="com.example.ecommerce_jsp_servlet.Connection.DBConnection" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.DAO.ProductDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Product" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth",auth);
    }
    ProductDAO productDAO = new ProductDAO(DBConnection.getConnection());
    List<Product> products = productDAO.getAllProducts();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list",cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>

    <%@ include file="includes/header.jsp" %>
</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<style>
    .product-image {
        height: 300px;
        object-fit: cover;
    }
</style>
<div class="container">
    <div class="card-header my-3">
        All Products
    </div>
    <div class="row">
        <%
            if (!products.isEmpty()){
                for(Product p:products){
        %>
         <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img src="images/<%= p.getImage()%>" class="card-img-top img-fluid product-image" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><%= p.getName()%></h5>
                    <h6 class="price">Price: <%= p.getPrice()%></h6>
                    <h6 class="category">Category: <%= p.getCategory()%></h6>
                    <div class="mt-3 d-flex justify-content-between">
                        <a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-black">Add to Cart</a>
                        <a href="#" class="btn btn-primary">Buy Now</a>
                    </div>
                </div>
            </div>
         </div>
        <%}
        }
        %>


    </div>

</div>
<%@ include file="includes/footer.jsp" %>

</body>
</html>