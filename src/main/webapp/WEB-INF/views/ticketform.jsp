<%@ page import="com.example.calebabbottcustomersupport.Attachment" %>
<%@ page import="java.util.Map" %>


<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Create Ticket</title>
</head>
<body>
    <h1>Create Ticket</h1>
<form action="create" method="post" enctype="multipart/form-data">
    <label>Customer Name:</label>
    <input type="text" name="customerName"><br>
    <label>Subject:</label>
    <input type="text" name="subject"><br>
    <label>Body:</label>
    <textarea name="body"></textarea><br>
    <label>Attachment:</label>
    <input type="file" name="attachment"><br>
    <input type="submit" value="Create">
</form>

            <p><a href="TicketServlet?action=list">Back to Ticket List</a></p>



</body>
</html>
