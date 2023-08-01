<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket View</title>
</head>
<body>
    <h1>Ticket Details</h1>
    <% Ticket ticket = (Ticket) request.getAttribute("ticket");
       if (ticket != null) { %>
       <h2>Ticket <%= ticket.getId() %></h2>
        <h2><%= ticket.getSubject() %></h2>
        <p>Customer Name: <%= ticket.getCustomerName() %></p>
        <p>Body: <%= ticket.getBody() %></p>
        <% Map<Integer, Attachment> attachments = ticket.getAllAttachments();
           if (!attachments.isEmpty()) { %>
            <h3>Attachments:</h3>
            <ul>
                <% for (Map.Entry<Integer, Attachment> entry : attachments.entrySet()) {
                       int attachmentIndex = entry.getKey();
                       Attachment attachment = entry.getValue();
                %>
                <li>
                    <%= attachment.getName() %> - <a href="TicketServlet?action=download&id=<%= ticket.getId() %>&attachmentIndex=<%= attachmentIndex %>">Download</a>
                </li>
                <% } %>
            </ul>
        <% } else { %>
            <p>No attachments</p>
        <% } %>
    <% } else { %>
        <p>Ticket not found</p>
    <% } %>
    <p><a href="list">Back to Ticket List</a></p>
</body>
</html>
