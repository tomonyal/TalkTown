<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
    <h2>ログイン</h2>
    <form action="login" method="post">
        ユーザー名: <input type="text" name="username" required><br>
        パスワード: <input type="password" name="password" required><br>
        <input type="submit" value="ログイン">
    </form>
    <br>
    <p>アカウントをお持ちでない場合は、<a href="createAccount.jsp">こちらで作成</a>してください。</p>
</body>
</html>
