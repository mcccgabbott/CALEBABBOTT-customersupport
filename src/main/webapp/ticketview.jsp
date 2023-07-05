<%@ page import="com.example.calebabbottcustomersupport.Ticket"%>
<!DOCTYPE html>
<html lang="en" xmlns="">
<head>
    <meta charset="UTF-8">
    <title>Ticket View</title>
</head>
<body>
    <h1>Ticket View</h1>
    <% Ticket ticket = (Ticket) request.getAttribute("ticket"); %>
    <% if (ticket != null) { %>
        <h2>Customer Name: <%= ticket.getCustomerName() %></h2>
        <h2>Subject: <%= ticket.getSubject() %></h2>
        <h2>Body: <%= ticket.getBody() %></h2>
        <h2>Attachments:</h2>
        <% Map<Integer, Ticket> db = (Map<Integer, Ticket>)request.getAttribute("tickets");
        if (db == null || db.size() == 0) {
        // print out no tickets
        }
        else {
        for (int id : db.keySet()) {
        Ticket ticket = db.get(id);%>
        <tr>
    <p><a href="tickets">Back to Ticket List</a></p>
</body>
</html>