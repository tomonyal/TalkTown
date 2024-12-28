package com.talktown;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 投稿一覧を取得するサーブレット
 */
@WebServlet("/posts")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        String prefecture = request.getParameter("prefecture");
        
        // エンコードされた値をデコード
        city = city != null ? URLDecoder.decode(city, StandardCharsets.UTF_8.toString()) : null;
        prefecture = prefecture != null ? URLDecoder.decode(prefecture, StandardCharsets.UTF_8.toString()) : null;
        
        PostDAO postDAO;
        List<Post> posts = null;
        String[] cities = new String[]{};

        try {
            postDAO = new PostDAO();
            if (city == null || city.equals("null") || city.isEmpty()) {
                posts = postDAO.getPostsByPrefectures(prefecture);
            } else {
                posts = postDAO.getPostsByCity(city);
            }

            // 都道府県に応じた市区町村リストを生成
            if ("北海道".equals(prefecture)) {
                cities = Cities.HOKKAIDO;
            } else if ("青森県".equals(prefecture)) {
                cities = Cities.AOMORI;
            } else if ("岩手県".equals(prefecture)) {
                cities = Cities.IWATE;
            } else if ("宮城県".equals(prefecture)) {
                cities = Cities.MIYAGI;
            } else if ("秋田県".equals(prefecture)) {
                cities = Cities.AKITA;
            } else if ("山形県".equals(prefecture)) {
                cities = Cities.YAMAGATA;
            } else if ("福島県".equals(prefecture)) {
                cities = Cities.FUKUSHIMA;
            } else if ("茨城県".equals(prefecture)) {
                cities = Cities.IBARAKI;
            } else if ("栃木県".equals(prefecture)) {
                cities = Cities.TOCHIGI;
            } else if ("群馬県".equals(prefecture)) {
                cities = Cities.GUNMA;
            } else if ("埼玉県".equals(prefecture)) {
                cities = Cities.SAITAMA;
            } else if ("千葉県".equals(prefecture)) {
                cities = Cities.CHIBA;
            } else if ("東京都".equals(prefecture)) {
                cities = Cities.TOKYO;
            } else if ("神奈川県".equals(prefecture)) {
                cities = Cities.KANAGAWA;
            } else if ("新潟県".equals(prefecture)) {
                cities = Cities.NIIGATA;
            } else if ("富山県".equals(prefecture)) {
                cities = Cities.TOYAMA;
            } else if ("石川県".equals(prefecture)) {
                cities = Cities.ISHIKAWA;
            } else if ("福井県".equals(prefecture)) {
                cities = Cities.FUKUI;
            } else if ("山梨県".equals(prefecture)) {
                cities = Cities.YAMANASHI;
            } else if ("長野県".equals(prefecture)) {
                cities = Cities.NAGANO;
            } else if ("岐阜県".equals(prefecture)) {
                cities = Cities.GIFU;
            } else if ("静岡県".equals(prefecture)) {
                cities = Cities.SHIZUOKA;
            } else if ("愛知県".equals(prefecture)) {
                cities = Cities.AICHI;
            } else if ("三重県".equals(prefecture)) {
                cities = Cities.MIE;
            } else if ("滋賀県".equals(prefecture)) {
                cities = Cities.SHIGA;
            } else if ("京都府".equals(prefecture)) {
                cities = Cities.KYOTO;
            } else if ("大阪府".equals(prefecture)) {
                cities = Cities.OSAKA;
            } else if ("兵庫県".equals(prefecture)) {
                cities = Cities.HYOGO;
            } else if ("奈良県".equals(prefecture)) {
                cities = Cities.NARA;
            } else if ("和歌山県".equals(prefecture)) {
                cities = Cities.WAKAYAMA;
            } else if ("鳥取県".equals(prefecture)) {
                cities = Cities.TOTTORI;
            } else if ("島根県".equals(prefecture)) {
                cities = Cities.SHIMANE;
            } else if ("岡山県".equals(prefecture)) {
                cities = Cities.OKAYAMA;
            } else if ("広島県".equals(prefecture)) {
                cities = Cities.HIROSHIMA;
            } else if ("山口県".equals(prefecture)) {
                cities = Cities.YAMAGUCHI;
            } else if ("徳島県".equals(prefecture)) {
                cities = Cities.TOKUSHIMA;
            } else if ("香川県".equals(prefecture)) {
                cities = Cities.KAGAWA;
            } else if ("愛媛県".equals(prefecture)) {
                cities = Cities.EHIME;
            } else if ("高知県".equals(prefecture)) {
                cities = Cities.KOCHI;
            } else if ("福岡県".equals(prefecture)) {
                cities = Cities.FUKUOKA;
            } else if ("佐賀県".equals(prefecture)) {
                cities = Cities.SAGA;
            } else if ("長崎県".equals(prefecture)) {
                cities = Cities.NAGASAKI;
            } else if ("熊本県".equals(prefecture)) {
                cities = Cities.KUMAMOTO;
            } else if ("大分県".equals(prefecture)) {
                cities = Cities.OITA;
            } else if ("宮崎県".equals(prefecture)) {
                cities = Cities.MIYAZAKI;
            } else if ("鹿児島県".equals(prefecture)) {
                cities = Cities.KAGOSHIMA;
            } else if ("沖縄県".equals(prefecture)) {
                cities = Cities.OKINAWA;
            }

        } catch (SQLException e) {
            throw new ServletException("投稿取得エラー", e);
        }

        request.setAttribute("posts", posts);
        request.setAttribute("cities", cities);
        request.setAttribute("selectedCity", city); // 選択された市区町村をリクエスト属性に設定
        request.getRequestDispatcher("bulletinBoard.jsp").forward(request, response);
    }
}
