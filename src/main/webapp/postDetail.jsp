<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="com.talktown.Post" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html>
<html>
<head>
    <title>投稿詳細</title>
    <link rel="stylesheet" type="text/css" href="css/postDetail.css">
</head>
<body>
    <h2>投稿詳細</h2>
    <div class="container">
        <div class="post-detail">
            <%
                Post post = (Post) request.getAttribute("post");
                String prefecture = request.getParameter("prefecture");
                String city = request.getParameter("city");
                if (post != null) {
                    String gender = post.getSex().equals("men") ? "男" : post.getSex().equals("women") ? "女" : post.getSex();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String formattedCreateAt = post.getCreateAt().toLocalDateTime().format(formatter);
                    String formattedUpdateAt = post.getUpdateAt().toLocalDateTime().format(formatter);
            %>
                    <h3><%= post.getSubject() %></h3>
                    <div class="info-group">
                        <p><span class="label">身長:</span> <%= post.getHeight() %> cm</p>
                        <p><span class="label">体重:</span> <%= post.getWeight() %> kg</p>
                        <p><span class="label">年齢:</span> <%= post.getAge() %></p>
                    </div>
                    <hr>
                    <p><span class="label">内容:</span> <%= post.getContent() %></p>
                    <hr>
                    <div class="info-group">
                        <p><span class="label">性別:</span> <%= gender %></p>
                        <% if (city == null || city.isEmpty() || "null".equals(city)) { %>
                            <p class="city-name">市区町村: <%= post.getCity() %></p>
                        <% } %>
                    </div>
                    <hr>
                    <div class="info-group">
                        <p><span class="label">投稿日時:</span> <%= formattedCreateAt %></p>
                        <p><span class="label">更新日時:</span> <%= formattedUpdateAt %></p>
                    </div>
                    <!-- メールするボタンを追加 -->
                    <div class="footer">
                        <button class="mail-button">
                            <a href="mailto:example@example.com?subject=<%= post.getSubject() %>&body=内容:%20<%= post.getContent() %>%0A%0A身長:%20<%= post.getHeight() %>cm%0A体重:%20<%= post.getWeight() %>kg%0A年齢:%20<%= post.getAge() %>歳%0A性別:%20<%= gender %>%0A投稿日時:%20<%= formattedCreateAt %>%0A更新日時:%20<%= formattedUpdateAt %>">
                                メールする
                            </a>
                        </button>
                    </div>
            <%
                } else {
            %>
                    <p>投稿が見つかりません。</p>
            <%
                }
            %>
        </div>
        <div class="footer">
            <button class="back">
                <a href="posts?prefecture=<%= prefecture %>&city=<%= city %>">投稿一覧に戻る</a>
            </button>
        </div>
    </div>
</body>
</html>
