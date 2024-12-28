<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.talktown.Post" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getParameter("prefecture") %> - 掲示板</title>
    <link rel="stylesheet" type="text/css" href="css/bulletinBoard.css">
</head>
<body>
    <h2><%= request.getParameter("prefecture") %> - 掲示板</h2>
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
                String selectedCity = request.getParameter("city");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                if (posts != null) {
                    for (Post post : posts) {
                        String genderClass = post.getSex().equals("men") ? "male" : post.getSex().equals("women") ? "female" : "";
                        String formattedCreateAt = post.getCreateAt().toLocalDateTime().format(formatter);
            %>
                        <div class="post <%= genderClass %>">
                            <a href="postDetail?id=<%= post.getPostId() %>&prefecture=<%= prefecture %>&city=<%= selectedCity %>">
                            <% if (selectedCity == null || selectedCity.isEmpty()) { %>
                                <span class="city-name">市区町村: <%= post.getCity() %></span><br>
                            <% } %>
                                - <%= post.getSubject() %> 
                                <br>- 身長: <%= post.getHeight() %>cm - 体重: <%= post.getWeight() %>kg - 年齢: <%= post.getAge() %> - 性別: <%= post.getSex().equals("men") ? "男" : post.getSex().equals("women") ? "女" : post.getSex() %>
                                <br>- 登録日: <%= formattedCreateAt %>
                            </a>
                        </div>
            <%
                    }
                } else {
            %>
                    <p>投稿が見つかりません。</p>
            <%
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
    var prefecture = "<%= prefecture %>";
    window.location.href = "posts?city=" + city + "&prefecture=" + prefecture;
}
</script>
</html>
