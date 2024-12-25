<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.talktown.User" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <%
                    HttpSession currentSession = request.getSession(false); // セッションを定義
                    if (currentSession != null && currentSession.getAttribute("user") != null) {
                %>
                <li style="float: left;"><a href="logout">ログアウト</a></li>
                <li style="float: left;"><a href="mainPage.jsp">メインページ</a></li>
                <li class="user-info">
                    <% 
                        User user = (User) currentSession.getAttribute("user");
                        out.print("ようこそ, " + user.getUsername() + " さん<br>");
                        out.print("<a href='accountSettings' class='account-settings-link'>アカウント設定</a>");
                    %>
                </li>
                <% } else { %>
                <li style="float: left;"><a href="mainPage.jsp">メインページ</a></li>
                <li style="float: left;"><a href="login.jsp">ログイン</a></li>
                <% } %>
            </ul>
        </nav>
    </header>
    <div class="main-content">
