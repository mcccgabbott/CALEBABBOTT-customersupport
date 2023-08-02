<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <c:forEach var="attachmentEntry" items="${ticket.allAttachments}">
                    <c:set var="attachmentIndex" value="${attachmentEntry.key}" />
                    <c:set var="attachment" value="${attachmentEntry.value}" />
                    <li>
                        ${attachment.name} -  <a href="<c:url value='${ticket.id}/download/${attachmentIndex}'/>">Download</a>
                    </li>
                </c:forEach>
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
