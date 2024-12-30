<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>TalkTown(市区町村で絞り込める異性愛、同性愛の出会い掲示板 画像、ID交換、メール)</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="admin-message">
        <h3>管理人からのメッセージ</h3>
        <p>
            <%
                String filePath = application.getRealPath("/WEB-INF/admin_message.txt");
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath));
                String line;
                while ((line = reader.readLine()) != null) {
                    out.println(line);
                }
                reader.close();
            %>
        </p>
        <div class="admin-request">
            <a href="requestPage.jsp">管理者への要望・報告等はこちら</a>
        </div>
        <div class="admin-request">
            <br><a href="https://www.paypal.com/ncp/payment/6EYVK8TF9A2DG">寄付はこちら（サーバ維持費、機能拡充に充てさせて頂きます。）</a>
        </div>
    </div>
    <div class="main-content">
        <h2>TalkTown(市区町村で絞り込める異性愛、同性愛の出会い掲示板 画像、ID交換、メール掲示板)</h2>
        <h3>画像市場</h3>
        <div class="image-market">
            <!--<a href="imageMarket"> --><a href="#">画像市場へ(準備中)</a>
        </div>
        <h3>都道府県一覧</h3>
        <div class="regions-container">
            <div class="region-block">
                <h4>北海道・東北</h4>
                <ul>
                    <li><a href="posts?prefecture=北海道">北海道</a></li>
                    <li><a href="posts?prefecture=青森県">青森県</a></li>
                    <li><a href="posts?prefecture=岩手県">岩手県</a></li>
                    <li><a href="posts?prefecture=宮城県">宮城県</a></li>
                    <li><a href="posts?prefecture=秋田県">秋田県</a></li>
                    <li><a href="posts?prefecture=山形県">山形県</a></li>
                    <li><a href="posts?prefecture=福島県">福島県</a></li>
                </ul>
            </div>
        <div class="region-block">
            <h4>関東</h4>
            <ul>
                <li><a href="posts?prefecture=茨城県">茨城県</a></li>
                <li><a href="posts?prefecture=栃木県">栃木県</a></li>
                <li><a href="posts?prefecture=群馬県">群馬県</a></li>
                <li><a href="posts?prefecture=埼玉県">埼玉県</a></li>
                <li><a href="posts?prefecture=千葉県">千葉県</a></li>
                <li><a href="posts?prefecture=東京都">東京都</a></li>
                <li><a href="posts?prefecture=神奈川県">神奈川県</a></li>
            </ul>
        </div>
        <div class="region-block">
            <h4>中部</h4>
            <ul>
                <li><a href="posts?prefecture=新潟県">新潟県</a></li>
                <li><a href="posts?prefecture=富山県">富山県</a></li>
                <li><a href="posts?prefecture=石川県">石川県</a></li>
                <li><a href="posts?prefecture=福井県">福井県</a></li>
                <li><a href="posts?prefecture=山梨県">山梨県</a></li>
                <li><a href="posts?prefecture=長野県">長野県</a></li>
                <li><a href="posts?prefecture=岐阜県">岐阜県</a></li>
                <li><a href="posts?prefecture=静岡県">静岡県</a></li>
                <li><a href="posts?prefecture=愛知県">愛知県</a></li>
            </ul>
        </div>
        <div class="region-block">
            <h4>近畿</h4>
            <ul>
                <li><a href="posts?prefecture=三重県">三重県</a></li>
                <li><a href="posts?prefecture=滋賀県">滋賀県</a></li>
                <li><a href="posts?prefecture=京都府">京都府</a></li>
                <li><a href="posts?prefecture=大阪府">大阪府</a></li>
                <li><a href="posts?prefecture=兵庫県">兵庫県</a></li>
                <li><a href="posts?prefecture=奈良県">奈良県</a></li>
                <li><a href="posts?prefecture=和歌山県">和歌山県</a></li>
            </ul>
        </div>
        <div class="region-block">
            <h4>中国</h4>
            <ul>
                <li><a href="posts?prefecture=鳥取県">鳥取県</a></li>
                <li><a href="posts?prefecture=島根県">島根県</a></li>
                <li><a href="posts?prefecture=岡山県">岡山県</a></li>
                <li><a href="posts?prefecture=広島県">広島県</a></li>
                <li><a href="posts?prefecture=山口県">山口県</a></li>
            </ul>
        </div>
        <div class="region-block">
            <h4>四国</h4>
            <ul>
                <li><a href="posts?prefecture=徳島県">徳島県</a></li>
                <li><a href="posts?prefecture=香川県">香川県</a></li>
                <li><a href="posts?prefecture=愛媛県">愛媛県</a></li>
                <li><a href="posts?prefecture=高知県">高知県</a></li>
            </ul>
        </div>
        <div class="region-block">
            <h4>九州・沖縄</h4>
            <ul>
                <li><a href="posts?prefecture=福岡県">福岡県</a></li>
                <li><a href="posts?prefecture=佐賀県">佐賀県</a></li>
                <li><a href="posts?prefecture=長崎県">長崎県</a></li>
                <li><a href="posts?prefecture=熊本県">熊本県</a></li>
                <li><a href="posts?prefecture=大分県">大分県</a></li>
                <li><a href="posts?prefecture=宮崎県">宮崎県</a></li>
                <li><a href="posts?prefecture=鹿児島県">鹿児島県</a></li>
                <li><a href="posts?prefecture=沖縄県">沖縄県</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
