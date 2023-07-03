<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Ticket</title>
</head>
<body>
    <h1>Create Ticket</h1>
    <form action="tickets/create" method="POST">
        <label>Customer Name:</label>
        <input type="text" id="customerName" name="customerName"><br>
        <label>Subject:</label>
        <input type="text" id="subject" name="subject"><br>
        <label for="body">Body:</label>
        <textarea id="body" name="body"></textarea><br>
        <input type="submit" value="Create">
    </form>
    <p><a href="tickets">Back to Ticket List</a></p>
</body>
</html>
