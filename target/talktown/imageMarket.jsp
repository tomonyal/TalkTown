<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.talktown.ImageMarket" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>画像市場</title>
    <link rel="stylesheet" href="css/imageMarket.css">
</head>
<body>
    <div class="container">
        <section class="images-section">
            <h2>画像市場</h2>
            <% 
                HttpSession existingSession = request.getSession(false);
                User user = existingSession != null ? (User) existingSession.getAttribute("user") : null;
                if (user == null) { 
            %>
                <div class="alert">
                    <p>詳細ページやアップロード機能を利用するには、ログインしてください。</p>
                </div>
            <% } %>
            <div class="upload-button">
                <%
                    if (user == null) { 
                %>
                    <a href="login.jsp" class="button">画像をアップロード</a>
                <%
                    } else {
                %>
                    <a href="uploadImage.jsp" class="button">画像をアップロード</a>
                <%
                    }
                %>
            </div>
            <table border="1">
                <tr>
                    <th>件名</th>
                    <th>身長</th>
                    <th>体重</th>
                    <th>年齢</th>
                    <th>性別</th>
                    <th>価格</th>
                    <th>作成日時</th>
                </tr>
                <% 
                    List<ImageMarket> imageList = (List<ImageMarket>) request.getAttribute("imageList");
                    if (imageList != null) {
                        for (int i = 0; i < imageList.size(); i++) {
                            ImageMarket image = imageList.get(i);
                %>
                <tr>
                    <td><a href="imageMarketDetails.jsp?id=<%= image.getImageMarketId() %>"><%= image.getTitle() %></a></td>
                    <td><%= image.getHeight() %> cm</td>
                    <td><%= image.getWeight() %> kg</td>
                    <td><%= image.getAge() %> 歳</td>
                    <td><%= image.getSex() == 'M' ? "男" : "女" %></td>
                    <td><%= image.getIsFree() ? "無料" : image.getPrice() %> pt</td>
                    <td><%= image.getCreateAt() %></td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7">表示する画像がありません。</td>
                </tr>
                <% 
                    }
                %>
            </table>
        </section>
    </div>
</body>
</html>
