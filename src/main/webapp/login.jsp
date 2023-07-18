<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>

    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
         <input type="hidden" name="action" value="login">
            <input type="submit" value="Login">
    </form>

     <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>

        <p style="color:red">
            <% String errorMessage = (String) request.getAttribute("error");
               if (errorMessage != null) { %>
                   <%= errorMessage %>
               <% } %>
        </p>

</body>
</html>