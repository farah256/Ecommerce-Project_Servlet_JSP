
<%@ page import="com.example.ecommerce_jsp_servlet.Connection.DBConnection" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce_jsp_servlet.Model.DAO.ProductDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if(auth != null){
        request.setAttribute("auth",auth);
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if(cart_list != null){
        ProductDAO productDAO = new ProductDAO(DBConnection.getConnection());
        cartProduct = productDAO.getCartProducts(cart_list);
        float total = productDAO.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list",cart_list);
        request.setAttribute("total",total);
    }

%>


<html>
<head>
    <title>Cart Page</title>
    <%@ include file="includes/header.jsp" %>
    <style type="text/css">
        .table tbody td{
            vertical-align: middle;

        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }
        .product-image{
            width: 80px;
            height: 80px;
        }

    </style>

</head>
<body>
<%@ include file="includes/navbar.jsp" %>
<div class="container">
    <div class="d-flex py-3">
        <h3>Total Price: $${(total>0)?total:0 }</h3>
        <a class="mx-3 btn btn-primary" href="#">Check out</a>
    </div>

    <table class="table table-loght">
            <thead>
            <tr>
                <th scope="col">Image</th>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Price</th>
                <th scope="col">Buy Now</th>
                <th scope="col">Cancel</th>
            </tr>
            </thead>
            <tbody>
            <%
            if(cart_list != null){
                for(Cart c:cartProduct){
                    %>

            <tr>
                <td> <img src="images/<%= c.getImage()%>" class="card-img-top img-fluid product-image" alt="..."></td>
                <td><%= c.getName()%></td>
                <td><%= c.getCategory()%></td>
                <td><%= c.getPrice()%></td>
                <td>
                    <form action="" method="post" class="form-inline">
                        <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                        <div class="form-group d-flex justify-content-between">
                            <a class="btn btn-sm btn-decre" href="quantity"><i class="fas fa-minus-square"></i></a>
                            <input type="text" name="quantity" value="1" class="form-control" readonly>
                            <a class="btn btn-sm btn-incre" href="quantity"><i class="fas fa-plus-square"></i></a>
                        </div>

                    </form>
                </td>
                <td><a class="btn btn-danger" href="">Remove</a></td>

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
