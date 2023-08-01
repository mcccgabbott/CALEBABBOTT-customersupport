<%@ page import="com.example.calebabbottcustomersupport.Ticket" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
</head>
<body>
    <h1>Ticket List</h1>
    <p>Welcome! (<a href="logout">Logout</a>)</p>

    <c:if test="${not empty tickets}">
        <c:forEach var="ticket" items="${tickets}">
            <div>
                <h3>Ticket ${ticket.id}</h3>
                <p>Customer Name: ${ticket.customerName}</p>
                <p>Subject: ${ticket.subject}</p>
                <p>Body: ${ticket.body}</p>
                <p>Has Attachments: ${hasAttachmentsMap[ticket.id] ? "Yes" : "No"}</p>
                <p><a href="view/${ticket.id}">View Ticket</a></p>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${empty tickets}">
        <p>No tickets found.</p>
    </c:if>

    <p><a href="form">Create Ticket</a></p>
</body>
</html>