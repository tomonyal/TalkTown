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
    <script>
        function copyToClipboard(text) {
            navigator.clipboard.writeText(text).then(function() {
                alert('IDをコピーしました: ' + text);
            }, function(err) {
                alert('IDのコピーに失敗しました: ', err);
            });
        }
    </script>
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
                    String gender = post.getSex();
                    if (gender.equals("male")) {
                        gender = "男";
                    } else if (gender.equals("female")) {
                        gender = "女";
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    String formattedCreateAt = post.getCreateAt().toLocalDateTime().format(formatter);
                    String formattedUpdateAt = post.getUpdateAt().toLocalDateTime().format(formatter);
                    String imagePath = post.getS3Url();  // 画像URLを取得
                    String externalId = post.getExternalId();
                    String email = post.getEmail();  // メールアドレスを取得
            %>
                    <h3><%= post.getSubject() %></h3>
                    <div class="info-group">
                        <p><span class="label">身長:</span> <%= post.getHeight() %> cm</p>
                        <p><span class="label">体重:</span> <%= post.getWeight() %> kg</p>
                        <p><span class="label">年齢:</span> <%= post.getAge() %> 歳</p>
                    </div>
                    <hr>
                    <% if (imagePath != null && !imagePath.isEmpty()) { %>
                        <div class="image-section">
                            <img src="<%= imagePath %>" alt="添付画像" class="post-image">
                        </div>
                        <hr>
                    <% } %>
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
                    <div class="footer">
                        <% if (email != null && !email.isEmpty()) { %>
                            <button class="mail-button">
                                <a href="mailto:<%= email %>?subject=<%= post.getSubject() %>&body=内容:%20<%= post.getContent() %>%0A%0A身長:%20<%= post.getHeight() %>cm%0A体重:%20<%= post.getWeight() %>kg%0A年齢:%20<%= post.getAge() %>歳%0A性別:%20<%= gender %>%0A投稿日時:%20<%= formattedCreateAt %>%0A更新日時:%20<%= formattedUpdateAt %>">
                                    メールする
                                </a>
                            </button>
                        <% } %>
                        <% if (externalId != null && !externalId.isEmpty()) { %>
                            <button class="mail-button" onclick="copyToClipboard('<%= externalId %>')">IDをコピー</button>
                        <% } %>
                    </div>
                    <form action="deletePost" method="post">
                        <input type="hidden" name="postId" value="<%= post.getPostId() %>">
                        削除パスワード: <input type="password" name="deletePassword" required><br>
                        <input type="submit" value="削除">
                    </form>
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
