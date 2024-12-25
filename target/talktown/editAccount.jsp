<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント編集</title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
    <h2>アカウント編集</h2>
    <div class="container">
        <div class="account-edit">
            <a href="accountSettings.jsp">アカウント設定に戻る</a>
            <h3>編集フォーム</h3>
            <%
                if (session != null && session.getAttribute("user") != null) {
                    User user = (User) session.getAttribute("user");
            %>
            <form action="updateAccount" method="post">
                <div class="form-group">
                    <label for="username">ユーザー名:</label>
                    <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>
                </div>
                <div class="form-group">
                    <label for="email">メールアドレス:</label>
                    <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                </div>
                <div class="form-group">
                    <label for="password">パスワード:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="icon">アイコン:</label>
                    <input type="file" id="icon" name="icon">
                </div>
                <div class="form-group">
                    <input type="submit" value="変更">
                </div>
            </form>
            <% } else { %>
            <p>ログインが必要です。</p>
            <a href="login.jsp">ログインページへ</a>
            <% } %>
        </div>
    </div>
</body>
</html>
