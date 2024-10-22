<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
</head>
<body>
    <h2>Register</h2>
    <!-- Display message, such as user not found or username already exists -->
    <p style="color:red;">${message}</p>

    <form method="post" action="/register">
        Name: <input type="text" name="username" value="${username}" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Register">
    </form>
</body>
</html>