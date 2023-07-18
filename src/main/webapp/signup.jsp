<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>
<body>
    <h1>Sign Up</h1>

    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"><br>
        <input type="hidden" name="action" value="signup">
        <input type="submit" value="Sign Up">
    </form>

    <p>Already have an account? <a href="login.jsp">Login</a></p>
</body>
</html>
