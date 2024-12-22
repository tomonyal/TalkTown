<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Creation / Login</title>
</head>
<body>
    <h2>Create an Account</h2>
    <form action="register" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        Email: <input type="email" name="email"><br>
        <input type="submit" value="Register">
    </form>

    <h2>Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
