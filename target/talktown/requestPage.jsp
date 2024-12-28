<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理者への要望・違反報告等</title>
    <link rel="stylesheet" type="text/css" href="css/requestPage.css">
</head>
<body>
    <div class="main-content">
        <h2>管理者への要望・違反報告等</h2>
        <form action="submitRequest" method="post">
            <div class="form-group">
                <label for="requestType">要望の種類:</label>
                <select id="requestType" name="requestType">
                    <option value="要望">要望</option>
                    <option value="違反報告">違反報告</option>
                </select>
            </div>
            <div class="form-group">
                <label for="requestContent">内容:</label>
                <textarea id="requestContent" name="requestContent" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="submit-button">送信</button>
            </div>
        </form>
        <div class="footer">
            <button class="back"><a href="mainPage.jsp">メインページに戻る</a></button>
        </div>
    </div>
</body>
</html>
