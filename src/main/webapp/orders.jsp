
<%@ page import="com.example.ecommerce_jsp_servlet.Connection.DBConnection" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth",auth);
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

<%@ include file="includes/footer.jsp" %>

</body>
</html>
