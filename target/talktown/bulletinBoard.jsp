<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.talktown.Post" %>
<!DOCTYPE html>
<html>
<head>
    <title>掲示板</title>
    <link rel="stylesheet" type="text/css" href="css/bulletinBoard.css">
</head>
<body>
    <h2>掲示板</h2>
    <div class="container">
        <div class="header">
            <% String prefecture = request.getParameter("prefecture"); %>
            <button class="new-post"><a href="newPost.jsp?prefecture=<%= prefecture %>">新規投稿</a></button>
            <div class="filter">
                <label for="city">市区町村で絞り込み:</label>
                <select id="city" name="city" onchange="filterPosts()">
                    <option value="">すべての市区町村</option>
                    <option value="city1">市区町村1</option>
                    <option value="city2">市区町村2</option>
                    <!-- 他の市区町村を追加 -->
                </select>
            </div>
        </div>
        <div class="post-list">
            <h3>投稿一覧</h3>
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                if (posts != null) {
                    for (Post post : posts) {
            %>
                        <div class="post">
                            <h4><%= post.getSubject() %></h4>
                            <p><%= post.getContent() %></p>
                            <p><small><%= post.getCity() %> - <%= post.getCreateAt() %></small></p>
                        </div>
            <%
                    }
                }
            %>
        </div>
        <div class="footer">
            <button class="back"><a href="mainPage.jsp">メインページへ戻る</a></button>
        </div>
    </div>
</body>
<script>
function filterPosts() {
    var city = document.getElementById("city").value;
    window.location.href = "posts?city=" + city;
}
</script>
</html>
