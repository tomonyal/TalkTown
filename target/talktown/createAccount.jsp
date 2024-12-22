<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント作成</title>
    <link rel="stylesheet" type="text/css" href="css/accountCreate.css">
</head>
<body>
    <h2>アカウント作成</h2>
    <form action="register" method="post">
        ユーザー名: <input type="text" name="username" required><br>
        パスワード: <input type="password" name="password" required><br>
        メールアドレス: <input type="email" name="email" required><br>
        市: <input type="text" name="city"><br>
        身長: <input type="number" name="height"><br>
        体重: <input type="number" name="weight"><br>
        生年月日: <input type="date" name="birth_date" required><br>
        性別: <input type="text" name="sex"><br>
        <input type="submit" value="登録">
    </form>
</body>
</html>
