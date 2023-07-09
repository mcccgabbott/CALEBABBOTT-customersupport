<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
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
    <%
        Map<Integer, Ticket> tickets = (Map<Integer, Ticket>) request.getAttribute("tickets");
        if (tickets != null && !tickets.isEmpty()) {
            for (Entry<Integer, Ticket> entry : tickets.entrySet()) {
                int ticketId = entry.getKey();
                Ticket ticket = entry.getValue();
    %>
    <div>
        <h3>Ticket <%= ticketId %></h3>
        <p>Customer Name: <%= ticket.getCustomerName() %></p>
        <p>Subject: <%= ticket.getSubject() %></p>
        <p>Body: <%= ticket.getBody() %></p>
        <p>
            <a href="TicketServlet?action=view&id=<%= ticketId %>">View Ticket</a>

        </p>
    </div>
    <%
            }
        } else {
    %>
    <p>No tickets found.</p>
    <% } %>
    <p><a href="TicketServlet?action=form">Create New Ticket</a></p>

</body>
</html>