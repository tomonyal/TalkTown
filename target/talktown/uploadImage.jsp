<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>画像アップロード</title>
    <link rel="stylesheet" href="css/imageMarket.css">
</head>
<body>
    <div class="container">
        <section class="upload-section">
            <h2>画像アップロード</h2>
            <form action="uploadImageServlet" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="image">画像ファイル</label>
                    <input type="file" name="image" id="image" required>
                </div>
                <div class="form-group">
                    <label for="price">価格（ポイント）</label>
                    <input type="number" name="price" id="price" required min="0" max="99999"> pt
                </div>
                <div class="form-group">
                    <label for="expiredDate">有効期限</label>
                    <select name="expiredDate" id="expiredDate">
                        <option value="1">1日</option>
                        <option value="3">3日</option>
                        <option value="7">7日</option>
                        <option value="30">1か月</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="content">内容</label>
                    <textarea name="content" id="content" rows="3"></textarea>
                </div>
                <button type="submit" class="button">アップロード</button>
            </form>
        </section>
    </div>
</body>
</html>
