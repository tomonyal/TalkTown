<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.talktown.Post" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <title><%= request.getParameter("prefecture") != null ? URLDecoder.decode(request.getParameter("prefecture"), StandardCharsets.UTF_8.toString()) : "掲示板" %> - 掲示板</title>
    <link rel="stylesheet" type="text/css" href="css/bulletinBoard.css">
</head>
<body>
    <h2><%= request.getParameter("prefecture") != null ? URLDecoder.decode(request.getParameter("prefecture"), StandardCharsets.UTF_8.toString()) : "掲示板" %> - 掲示板</h2>
    <div class="container">
        <div class="header">
            <% 
                String prefecture = request.getParameter("prefecture");
                String city = request.getParameter("city");
                prefecture = prefecture != null ? URLDecoder.decode(prefecture, StandardCharsets.UTF_8.toString()) : null;
                city = city != null ? URLDecoder.decode(city, StandardCharsets.UTF_8.toString()) : null;
            %>
            <button class="new-post"><a href="newPost?prefecture=<%= prefecture != null ? URLEncoder.encode(prefecture, StandardCharsets.UTF_8.toString()) : "" %>&city=<%= city != null ? URLEncoder.encode(city, StandardCharsets.UTF_8.toString()) : "" %>">新規投稿</a></button>
            <div class="filter">
                <label for="city">市区町村で絞り込み:</label>
                <select id="city" name="city" onchange="filterPosts()">
                    <option value="">すべての市区町村</option>
                    <% String[] cities = (String[]) request.getAttribute("cities");
                    String selectedCity = (String) request.getAttribute("selectedCity");
                    if (cities != null) {
                        for (String cityOption : cities) { %>
                            <option value="<%= cityOption %>" <%= cityOption.equals(selectedCity) ? "selected" : "" %>><%= cityOption %></option>
                    <% } } %>
                </select>
            </div>
        </div>
        <div class="post-list">
            <h3>投稿一覧</h3>
            <%
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                if (posts != null) {
                    for (Post post : posts) {
                        String genderClass = post.getSex().equals("male") ? "male" : post.getSex().equals("female") ? "female" : "";
                        String formattedCreateAt = post.getCreateAt().toLocalDateTime().format(formatter);
            %>
                        <div class="post <%= genderClass %>">
                            <a href="postDetail?id=<%= post.getPostId() %>&prefecture=<%= prefecture != null ? URLEncoder.encode(prefecture, StandardCharsets.UTF_8.toString()) : "" %>&city=<%= city != null ? URLEncoder.encode(city, StandardCharsets.UTF_8.toString()) : "" %>">
                            <% if (city == null || city.isEmpty()) { %>
                                <span class="city-name">市区町村: <%= post.getCity() %></span><br>
                            <% } %>
                                - <%= post.getSubject() %> 
                                <br>- 身長: <%= post.getHeight() %>cm - 体重: <%= post.getWeight() %>kg - 年齢: <%= post.getAge() %> - 性別: <%= post.getSex().equals("men") ? "男" : post.getSex().equals("women") ? "女" : post.getSex() %>
                                <br>- 登録日: <%= formattedCreateAt %>
                                <% if (post.getS3Url() != null && !post.getS3Url().isEmpty()) { %>
                                    <br><a href="<%= post.getS3Url() %>" class="image-link" style="color:red;">画像</a>
                                <% } %>
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
    var prefecture = "<%= prefecture != null ? URLEncoder.encode(prefecture, StandardCharsets.UTF_8.toString()) : "" %>";
    window.location.href = "posts?city=" + encodeURIComponent(city) + "&prefecture=" + encodeURIComponent(prefecture);
}
</script>
</html>
