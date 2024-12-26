<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>アカウント情報編集</title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
    <div class="main-content">
        <h2>アカウント情報編集</h2>
        <div class="container">
            <div class="account-management">
                <a href="mainPage.jsp">メインページへ戻る</a>
                <h3>ユーザー情報編集</h3>
                <%
                    if (currentSession != null && currentSession.getAttribute("user") != null) {
                        User user = (User) currentSession.getAttribute("user");
                %>
                <form action="updateAccount" method="post">
                    <div class="form-group">
                        <label for="username">ユーザー名:</label>
                        <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="password">パスワード:</label>
                        <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="email">メールアドレス:</label>
                        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="city">市:</label>
                        <input type="text" id="city" name="city" value="<%= user.getCity() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="height">身長 (cm):</label>
                        <input type="number" id="height" name="height" value="<%= user.getHeight() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="weight">体重 (kg):</label>
                        <input type="number" id="weight" name="weight" value="<%= user.getWeight() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="sex">性別:</label>
                        <input type="text" id="sex" name="sex" value="<%= user.getSex() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">生年月日:</label>
                        <input type="date" id="birthDate" name="birthDate" value="<%= user.getBirthDate() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="type">ユーザータイプ:</label>
                        <input type="text" id="type" name="type" value="<%= user.getType() %>" required>
                    </div>
                    <div class="edit-button">
                        <input type="submit" value="保存">
                    </div>
                </form>
                <% } else { %>
                <p>ログインが必要です。</p>
                <a href="login.jsp">ログインページへ</a>
                <% } %>
            </div>
        </div>
    </div>
</body>
</html>
