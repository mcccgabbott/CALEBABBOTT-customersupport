<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ page import="java.util.Map" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Create Ticket</title>
</head>
<body>
    <h1>Create Ticket</h1>
    <form action="/TicketServlet" method="POST">
        <label>Customer Name:</label>
        <input type="text" id="customerName" name="customerName"><br>
        <label>Subject:</label>
        <input type="text" id="subject" name="subject"><br>
        <label for="body">Body:</label>
        <textarea id="body" name="body"></textarea><br>
        <input type="Submit" value="Create">
    </form>
    <p><a href="index.jsp">Back to Ticket List</a></p>
</body>
</html>
