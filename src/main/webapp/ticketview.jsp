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
        <% Map<Integer Attachment> attachments = ticket.getAllAttachments(); %>
        <% for (Integer index : attachments.keySet()) { %>
            <a href="tickets/download?id=<%= ticket.getId() %>&attachmentIndex=<%= index %>">Attachment <%= index %></a><br>
        <% } %>
    <% } else { %>
        <p>Ticket not found.</p>
    <% } %>
    <p><a href="tickets">Back to Ticket List</a></p>
</body>
</html>