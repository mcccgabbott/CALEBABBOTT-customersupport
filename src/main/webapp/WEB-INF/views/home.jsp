<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ page import="com.example.calebabbottcustomersupport.LoginController" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
</head>
<body>
    <h1>Ticket List</h1>
    <p>Welcome! (<a href="logout">Logout</a>)</p>
    <%
        Map<Integer, Ticket> tickets = (Map<Integer, Ticket>) request.getAttribute("tickets");
        Map<Integer, Boolean> hasAttachmentsMap = (Map<Integer, Boolean>) request.getAttribute("hasAttachmentsMap");

        if (tickets != null && !tickets.isEmpty()) {
            for (Map.Entry<Integer, Ticket> entry : tickets.entrySet()) {
                int ticketId = entry.getKey();
                Ticket ticket = entry.getValue();
                boolean hasAttachments = hasAttachmentsMap.get(ticketId);
    %>
    <div>
        <h3>Ticket <%= ticketId %></h3>
        <p>Customer Name: <%= ticket.getCustomerName() %></p>
        <p>Subject: <%= ticket.getSubject() %></p>
        <p>Body: <%= ticket.getBody() %></p>
        <p>Has Attachments: <%= hasAttachments %></p>
        <p><a href="ticket/view?id=<%= ticketId %>">View Ticket</a></p>
    </div>
    <%
            }
        } else {
    %>
    <p>No tickets found.</p>
    <%
        }
    %>
    <p><a href="ticket/form">Create Ticket</a></p>
</body>
</html>