<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
</head>
<body>
    <h1>Ticket List</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Customer Name</th>
                <th>Subject</th>
            </tr>
        </thead>
        <tbody>
            <%
            Map<Integer, Ticket> ticketMap = (Map<Integer, Ticket>) request.getAttribute("tickets");
            if (ticketMap != null) {
                for (Ticket ticket : ticketMap.values()) {
            %>
                <tr>
                    <td><a href="/TicketServlet/view?id=<%= ticket.getId() %>"><%= ticket.getId() %></a></td>
                    <td><%= ticket.getCustomerName() %></td>
                    <td><%= ticket.getSubject() %></td>
                </tr>
            <%
                }
            } else {
            %>
                <tr>
                    <td colspan="3">No tickets found</td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
    <p><a href="/ticketform">Create Ticket</a></p>
</body>
</html>