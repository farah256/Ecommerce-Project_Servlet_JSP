
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
