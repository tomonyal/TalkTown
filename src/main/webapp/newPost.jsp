<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>新規投稿</title>
    <link rel="stylesheet" type="text/css" href="css/newPost.css">
</head>
<body>
    <h2>新規投稿</h2>
    <div class="container">
        <form action="newPost" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">名前:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group-inline">
                <div>
                    <label for="prefectures">都道府県:</label>
                    <input type="text" id="prefectures" name="prefectures" value="<%= request.getParameter("prefecture") %>" required>
                </div>
                <div>
                    <label for="city">市区町村:</label>
                    <input type="text" id="city" name="city" required>
                </div>
            </div>
            <div class="form-group-inline">
                <div>
                    <label for="height">身長:</label>
                    <input type="number" id="height" name="height">
                </div>
                <div>
                    <label for="weight">体重:</label>
                    <input type="number" id="weight" name="weight">
                </div>
                <div>
                    <label for="age">年齢:</label>
                    <input type="number" id="age" name="age">
                </div>
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
                <label for="email">メールアドレス:</label>
                <input type="email" id="email" name="email" required>
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
</body>
</html>
