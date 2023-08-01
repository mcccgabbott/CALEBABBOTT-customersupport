<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Ticket</title>
</head>
<body>

    <h1>Create Ticket</h1>
    <form:form method="POST" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="customerName">Customer Name:</form:label>
        <form:input path="customerName"/><br>
        <form:label path="subject">Subject:</form:label>
        <form:input path="subject"/><br>
        <form:label path="body">Body:</form:label>
        <form:textarea path="body"/><br>
        <form:label path="attachment">Attachment:</form:label>
        <form:input type="file" path="attachment"/><br>
        <input type="submit" value="Create">
    </form:form>

    <p><a href="list">Back to Ticket List</a></p>
</body>
</html>