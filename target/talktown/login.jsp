<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログインページ</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <h2>ログイン</h2>
    <form action="login" method="post">
        <input type="text" name="username" placeholder="ユーザー名" required>
        <input type="password" name="password" placeholder="パスワード" required>
        <input type="submit" value="ログイン">
    </form>
    <p><a href="register.jsp">アカウントを作成</a></p>
    <p><a href="mainPage.jsp">ログインせずに続行</a></p>
</body>
</html>
