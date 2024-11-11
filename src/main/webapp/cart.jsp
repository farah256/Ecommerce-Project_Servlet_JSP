
<%@ page import="com.example.ecommerce_jsp_servlet.Connection.DBConnection" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth",auth);
    }
%>

<html>
<head>
    <title>Cart Page</title>
    <%@ include file="includes/header.jsp" %>

</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<%@ include file="includes/footer.jsp" %>

</body>
</html>
