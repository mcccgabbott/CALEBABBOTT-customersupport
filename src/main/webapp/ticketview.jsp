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
    <% Ticket ticket = (Ticket) request.getAttribute("ticket"); %>
    <% if (ticket != null) { %>
        <h2>Ticket: <%= ticket.getId() %></h2>
        <h2>Customer Name: <%= ticket.getCustomerName() %></h2>
        <h2>Subject: <%= ticket.getSubject() %></h2>
        <h2>Body: <%= ticket.getBody() %></h2>
        <h2>Attachments:</h2>
        <%
        Map<Integer, com.example.calebabbottcustomersupport.Attachment> attachments = ticket.getAllAttachments();
        if (attachments.isEmpty()) {
            out.println("No attachments");
        } else {
            for (Map.Entry<Integer, com.example.calebabbottcustomersupport.Attachment> entry : attachments.entrySet()) {
                int attachmentIndex = entry.getKey();
                com.example.calebabbottcustomersupport.Attachment attachment = entry.getValue();
        %>
        <p>Attachment <%= attachmentIndex %>: <%= attachment.getName() %></p>
        <% } } %>
    <% } else { %>
        <p>Ticket not found</p>
    <% } %>
    <p><a href="TicketServlet?action=list">Back to Ticket List</a></p>
</body>
</html>
