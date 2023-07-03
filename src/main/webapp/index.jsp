<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
</head>
    <h1>Ticket List</h1>
<body>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Customer Name</th>
                <th>Subject</th>
            </tr>
        </thead>
        <% for (Ticket ticket : (Collection</Ticket>) request.getAttribute("tickets")) { %>
        <tr>
            <td><a href="tickets/view?id=<%= ticket.getId() %>"><%= ticket.getId() %></a></td>
            <td><%= ticket.getCustomerName() %></td>
            <td><%= ticket.getSubject() %></td>
        </tr>
        <% } %>
    </table>
    <p><a href="tickets/form">Create Ticket</a></p>
</body>
</html>
