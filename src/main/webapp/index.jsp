<%@ page import="com.example.calebabbottcustomersupport.Ticket, java.util.Map" %>

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
            <% for (Ticket ticket : (Map<Integer, Ticket>) request.getAttribute("tickets").values()) { %>
                <tr>
                    <td><a href="/ticketview?id=<%= ticket.getId() %>"><%= ticket.getId() %></a></td>
                    <td><%= ticket.getCustomerName() %></td>
                    <td><%= ticket.getSubject() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <p><a href="/ticketform">Create Ticket</a></p>
</body>
</html>
