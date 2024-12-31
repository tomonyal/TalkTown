<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/header.css">
    <meta name="viewport" content="width=device-width">
</head>
<body>
    <header>
        <nav>
            <ul>
                <%
                    HttpSession currentSession = request.getSession(false);
                    System.out.println("session:" + currentSession);
                    if (currentSession != null && currentSession.getAttribute("user") != null) {
                        User user = (User) currentSession.getAttribute("user");
                %>
                <li><a href="logout">ログアウト</a></li>
                <li><a href="mainPage.jsp">メインページ</a></li>
                <li class="user-info">
                    <span>ポイント残高: <%= user.getPoint() %> ポイント</span>
                    <% 
                        out.print("ようこそ, " + user.getUsername() + " さん");
                    %>
                    <a href='accountSettings' class='account-settings-link'>アカウント設定</a>
                </li>
                <% } else { %>
                <li><a href="mainPage.jsp">メインページ</a></li>
                <li><a href="login.jsp">ログイン・ユーザ登録</a></li>
                <% } %>
            </ul>
        </nav>
    </header>
    <% if (request.getAttribute("error") != null) { %>
        <div class="error-message"><%= request.getAttribute("error") %></div>
    <% } %>
</body>
</html>
