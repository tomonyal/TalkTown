<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント管理</title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
    <h2>アカウント管理</h2>
    <div class="container">
        <div class="account-management">
            <a href="mainPage.jsp">メインページへ戻る</a>
            <h3>ユーザー情報</h3>
            <%
                if (session != null && session.getAttribute("user") != null) {
                    User user = (User) session.getAttribute("user");
            %>
            <div class="user-info">
                <p>ユーザー名: <%= user.getUsername() %></p>
                <p>メールアドレス: <%= user.getEmail() %></p>
            </div>
            <div class="edit-button">
                <form action="editAccount.jsp" method="post">
                    <input type="submit" value="編集">
                </form>
            </div>
            <% } else { %>
            <p>ログインが必要です。</p>
            <a href="login.jsp">ログインページへ</a>
            <% } %>
        </div>
    </div>
</body>
</html>
