<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>お礼ページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f9;
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            color: #5d4037;
        }
        p {
            font-size: 1.2em;
        }
        .thank-you-img {
            width: 100px;
            height: 100px;
        }
        .user-input {
            margin-top: 20px;
        }
        input[type="text"], input[type="password"], input[type="email"], input[type="number"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>ありがとうございます！</h1>
        <p>コーヒーの購入ありがとうございます！</p>
        <p>頂いたご支援により、さらに良いサービスを提供できるよう努めてまいります。</p>
        <p>今後ともよろしくお願いいたします。</p>

        <div>
            <p>アカウントをお持ちの方は、ユーザ名の入力をお願いいたします。</p>
            <p>管理者が考えた素敵な特典を用意します。</p>
            <form action="receiveGift" method="post">
                ユーザー名: <input type="text" name="username" required><br>
                <input type="submit" value="送信">
            </form>
            <p>アカウント作成がまだの方はぜひ入力した名前で登録をお願いいたします。</p><br>
            <p><a href="createAccount.jsp">アカウントを作成</a></p>
        </div>
    </div>
</body>
</html>
