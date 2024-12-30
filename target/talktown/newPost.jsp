<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="com.talktown.User" %>
<%@ page import="com.talktown.SecurityUtils" %>
<!DOCTYPE html>
<html>
<head>
    <title>新規投稿</title>
    <link rel="stylesheet" type="text/css" href="css/newPost.css">
    <script>
        function toggleEmailOrId() {
            var emailRadio = document.getElementById("emailRadio");
            var idRadio = document.getElementById("idRadio");
            var emailField = document.getElementById("emailField");
            var idField = document.getElementById("idField");

            if (emailRadio.checked) {
                emailField.style.display = "block";
                idField.style.display = "none";
            } else if (idRadio.checked) {
                emailField.style.display = "none";
                idField.style.display = "block";
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            toggleEmailOrId(); // 初期状態の設定
        });
    </script>
</head>
<body>
    <div class="main-content">
        <h2>新規投稿</h2>
        <div class="container">
            <%
                String csrfToken = SecurityUtils.generateCsrfToken();
                session.setAttribute("csrfToken", csrfToken);
            %>
            <form action="newPost" method="post" enctype="multipart/form-data">
                <input type="hidden" name="csrfToken" value="<%= csrfToken %>">
                <div class="form-group">
                    <label for="name">名前:</label>
                    <input type="text" id="name" name="name" value="<%= session != null && session.getAttribute("user") != null ? ((User)session.getAttribute("user")).getUsername() : "" %>" required>
                </div>
                <div class="form-group-inline">
                    <div>
                        <label for="prefectures">都道府県:</label>
                        <input type="text" id="prefectures" name="prefectures" value="<%= SecurityUtils.sanitize(request.getParameter("prefecture")) %>" readonly style="background-color: #e9ecef;">
                    </div>
                    <div>
                        <label for="city">市区町村:</label>
                        <select id="city" name="city" required>
                            <option value="">市区町村を選択してください</option>
                            <% String[] cities = (String[]) request.getAttribute("cities");
                            String selectedCity = (String) request.getAttribute("selectedCity");
                            if (cities != null) {
                                for (String city : cities) { %>
                                    <option value="<%= SecurityUtils.sanitize(city) %>" <%= city.equals(selectedCity) ? "selected" : "" %>><%= SecurityUtils.sanitize(city) %></option>
                            <% } } %>
                        </select>
                    </div>
                </div>
                <div class="form-group-inline">
                    <div>
                        <label for="height">身長:</label>
                        <input type="number" id="height" name="height" value="<%= session != null && session.getAttribute("user") != null ? SecurityUtils.sanitize(String.valueOf(((User)session.getAttribute("user")).getHeight())) : "" %>">
                    </div>
                    <div>
                        <label for="weight">体重:</label>
                        <input type="number" id="weight" name="weight" value="<%= session != null && session.getAttribute("user") != null ? SecurityUtils.sanitize(String.valueOf(((User)session.getAttribute("user")).getWeight())) : "" %>">
                    </div>
                    <div>
                        <label for="age">年齢:</label>
                        <input type="number" id="age" name="age" value="<%= session != null && session.getAttribute("user") != null ? SecurityUtils.sanitize(String.valueOf(((User)session.getAttribute("user")).getAge())) : "" %>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sex">性別:</label>
                    <select id="sex" name="sex" required>
                        <option value="male">男性</option>
                        <option value="female">女性</option>
                        <option value="other">その他</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>連絡方法:</label>
                    <div>
                        <input type="radio" id="emailRadio" name="emailOrId" value="email" onclick="toggleEmailOrId()" checked>
                        <label for="emailRadio">メール</label>
                        <input type="radio" id="idRadio" name="emailOrId" value="id" onclick="toggleEmailOrId()">
                        <label for="idRadio">ID</label>
                    </div>
                </div>
                <div class="form-group" id="emailField">
                    <label for="email">メールアドレス:</label>
                    <input type="email" id="email" name="email" value="<%= session != null && session.getAttribute("user") != null ? SecurityUtils.sanitize(((User)session.getAttribute("user")).getEmail()) : "" %>">
                </div>
                <div class="form-group" id="idField" style="display: none;">
                    <label for="externalId">外部ID (カカオIDやLINE ID):</label>
                    <input type="text" id="externalId" name="externalId">
                </div>
                <div class="form-group">
                    <label for="subject">件名:</label>
                    <input type="text" id="subject" name="subject" required>
                </div>
                <div class="form-group">
                    <label for="content">本文:</label>
                    <textarea id="content" name="content" rows="10" required></textarea>
                </div>
                <div class="form-group">
                    <label for="image">添付画像:</label>
                    <input type="file" id="image" name="image">
                </div>
                <div class="form-group">
                    <label for="deletePassword">削除用パスワード:</label>
                    <input type="password" id="deletePassword" name="deletePassword" required>
                </div>
                <div class="form-group">
                    <input type="submit" value="投稿する">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
