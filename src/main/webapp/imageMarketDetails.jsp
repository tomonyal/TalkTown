<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.talktown.ImageMarket, com.talktown.ImageMarketDAO" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>画像詳細</title>
    <link rel="stylesheet" href="css/imageMarket.css">
</head>
<body>
    <div class="container">
        <%
            int imageMarketId = Integer.parseInt(request.getParameter("id"));
            ImageMarketDAO dao = new ImageMarketDAO();
            ImageMarket image = dao.getImageById(imageMarketId);

            if (image != null) {
        %>
        <section class="image-detail">
            <h2>画像詳細</h2>
            <p>件名: <%= image.getTitle() %></p>
            <p>身長: <%= image.getHeight() %> cm</p>
            <p>体重: <%= image.getWeight() %> kg</p>
            <p>年齢: <%= image.getAge() %> 歳</p>
            <p>性別: <%= image.getSex() == 'M' ? "男" : "女" %></p>
            <p>価格: <%= image.getIsFree() ? "無料" : image.getPrice() %> pt</p>
            <p>内容: <%= image.getContent() %></p>
            <p>作成日時: <%= image.getCreateAt() %></p>
            <p>更新日時: <%= image.getUpdateAt() %></p>
        </section>
        <% } else { %>
        <p>指定された画像は存在しません。</p>
        <% } %>
    </div>
</body>
</html>
