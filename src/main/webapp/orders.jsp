
<%@ page import="com.example.ecommerce_jsp_servlet.Connection.DBConnection" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Order" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.DAO.OrderDAO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if(auth != null){
        request.setAttribute("auth",auth);
        orders = new OrderDAO(DBConnection.getConnection()).userOrders((auth.getId()));
    }else{
       response.sendRedirect("login.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list",cart_list);
    }
%>
 <html>
<head>
    <title>Shopping Cart</title>
    <%@ include file="includes/header.jsp" %>


</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="card-header my-3">All Orders</div>
    <table class="table table-loght">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if(orders != null){
                for(Order o:orders){
        %>

        <tr>
            <td><%= o.getDate()%></td>
            <td><%= o.getName()%></td>
            <td><%= o.getCategory()%></td>
            <td><%= o.getPrice()%></td>
            <td><%= o.getQuantity()%></td>
            <td><a class="btn btn-danger" href="cancel-order?id=<%= o.getOrderId()%>">Cancel</a></td>

        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>

</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>
