<%@ page import="com.example.calebabbottcustomersupport.SessionRegistry" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Session List</title>
</head>
<body>
    <h1>Active Sessions</h1>
    <% List<HttpSession> sessions = SessionRegistry.getActiveSessions();
       if (sessions != null && !sessions.isEmpty()) {
           for (HttpSession session : sessions) { %>
               <p>Session ID: <%= session.getId() %></p>
               <p>Username: <%= session.getAttribute("username") %></p>
               <hr>
           <% }
       } else { %>
           <p>No active sessions found.</p>
       <% } %>
</body>
</html>
