<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント管理</title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
    <div class="main-content">
        <h2>アカウント管理</h2>
        <div class="container">
            <div class="account-management">
                <h3>ユーザー情報</h3>
                <%
                    if (currentSession != null && currentSession.getAttribute("user") != null) {
                        User user = (User) currentSession.getAttribute("user");
                %>
                <div class="account-user-info">
                    <p>ユーザーID: <%= user.getUserId() %></p>
                    <p>ユーザー名: <%= user.getUsername() %></p>
                    <p>パスワード: ••••••••••••••••••••••••••••••••••••••••••••••••••</p>
                    <p>メールアドレス: <%= user.getEmail() %></p>
                    <p>ユーザータイプ: <%= user.getType() %></p>
                    <p>ポイント: <%= user.getPoint() %></p>
                    <p>市: <%= user.getCity() %></p>
                    <p>身長: <%= user.getHeight() %> cm</p>
                    <p>体重: <%= user.getWeight() %> kg</p>
                    <p>年齢: <%= user.getAge() %> 歳</p>
                    <p>性別: <%= user.getSex() %></p>
                </div>
                <div class="edit-button">
                    <form action="editAccount.jsp" method="post">
                        <input type="submit" value="編集">
                    </form>
                </div>
                <div class="option-buttons">
                    <form action="purchasePoints.jsp" method="post" style="display: inline;">
                        <input type="submit" value="ポイント購入" disabled>
                    </form>
                    <form action="premiumMembership.jsp" method="post" style="display: inline;">
                        <input type="submit" value="プレミアムユーザーになる" disabled>
                    </form>
                </div>
                <% } else { %>
                <p>ログインが必要です。</p>
                <a href="login.jsp">ログインページへ</a>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
