<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if(cart_list != null){
        request.setAttribute("cart_list",cart_list);
    }
%>
<html>
<head>
    <title>Login</title>
    <%@ include file="includes/header.jsp" %>

</head>
<body>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Login</div>
        <div class="card-body">
           <form action="user-login" method="post">
               <div class="form-group">
                   <label>Email Address</label>
                   <input type="email" class="form-control" name="email" placeholder="exemple@gmail.com" required>
               </div>
               <div class="form-group">
                   <label>Password</label>
                   <input type="password" class="form-control" name="password" placeholder="a password" required>
               </div>
               <div class="text-center">
                   <button type="submit" class="btn btn-primary">Login</button>

               </div>
           </form>
        </div>
    </div>

</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>
