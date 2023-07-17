<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>

    <% if (request.getAttribute("signupSuccess") != null && (boolean) request.getAttribute("signupSuccess")) { %>
        <p>Registration successful! You can now log in with your new account.</p>
    <% } %>

    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Login">
    </form>

    <p><a href="SignupServlet">Sign up</a></p>
</body>
</html>