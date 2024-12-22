<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント設定</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>アカウント設定</h2>
    <form action="updateAccount" method="post">
        ユーザー名: <input type="text" name="username"><br>
        パスワード: <input type="password" name="password"><br>
        メールアドレス: <input type="email" name="email"><br>
        <input type="submit" value="更新">
    </form>
</body>
</html>
