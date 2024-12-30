package com.talktown;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * 新規投稿サーブレット
 * ユーザーの投稿情報を処理し、データベースに保存します。
 */
@WebServlet("/newPost")
@MultipartConfig
public class NewPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prefecture = request.getParameter("prefecture");
        String city = request.getParameter("city");
        String[] cities = new String[]{};

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

        request.setAttribute("cities", cities);
        request.setAttribute("selectedCity", city); // 選択された市区町村をリクエスト属性に設定
        request.getRequestDispatcher("newPost.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 許可する画像ファイルの拡張子
       final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp", ".tiff", ".heic");

        HttpSession session = request.getSession();
        User user = (session != null && session.getAttribute("user") != null) ? (User) session.getAttribute("user") : null;
        String csrfToken = request.getParameter("csrfToken");
        String sessionCsrfToken = (String) session.getAttribute("csrfToken");
        if (csrfToken == null || !csrfToken.equals(sessionCsrfToken)) {
            throw new ServletException("CSRFトークンが無効です。");
        }

        boolean isMultipart = request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/");
        Map<String, String> formFields = new HashMap<>();
        String fileName = null; // アップロードされたファイル名
        InputStream fileInputStream = null;
        Part filePart = request.getPart("image");

        String name = SecurityUtils.sanitize(formFields.get("name"));
        String prefectures = SecurityUtils.sanitize(formFields.get("prefectures"));
        String city = SecurityUtils.sanitize(formFields.get("city"));
        int height = parseIntOrDefault(formFields.get("height"), (user != null) ? user.getHeight() : 0);
        int weight = parseIntOrDefault(formFields.get("weight"), (user != null) ? user.getWeight() : 0);
        int age = parseIntOrDefault(formFields.get("age"), (user != null) ? user.getAge() : 0);
        String subject = SecurityUtils.sanitize(formFields.get("subject"));
        String content = SecurityUtils.sanitize(formFields.get("content"));
        String deletePassword = SecurityUtils.sanitize(formFields.get("deletePassword"));
        String sex = formFields.get("sex");
        String emailOrId = request.getParameter("emailOrId");
        boolean isPremium = (user != null) && "premium".equals(user.getType());
        int likeCount = 0;
        String email = null;
        String externalId = null;
        Integer userId = (user != null) ? user.getUserId() : 0;
        String userSex = (user != null) ? user.getSex() : sex;
        if ("email".equals(emailOrId)) {
            email = SecurityUtils.sanitize(request.getParameter("email")); 
        } else if ("id".equals(emailOrId)) { 
            externalId = SecurityUtils.sanitize(request.getParameter("externalId"));
        }

        prefectures = prefectures != null ? prefectures : "";
        city = city != null ? city : "";

        String encodedPrefecture = URLEncoder.encode(prefectures, StandardCharsets.UTF_8.toString());
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        if (filePart.getSize() > 3 * 1024 * 1024) { // 3MB
            request.setAttribute("error", "画像サイズが3MBを超えています。");
            request.setAttribute("prefecture", prefectures);
            request.setAttribute("city", city);
            request.getRequestDispatcher("newPost.jsp").forward(request, response);
            return;
        }
        if (isMultipart) {
            try {
                for (Part part : request.getParts()) {
                    if (part.getSubmittedFileName() == null) {
                        formFields.put(part.getName(), new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
                    } else {
                        fileName = part.getSubmittedFileName();
                        if (fileName != null && !fileName.isEmpty()) {
                            fileInputStream = part.getInputStream(); // 後で使用するために入力ストリームを保存
                        }
                    }
                }
            } catch (Exception e) {
                throw new ServletException("Multipart form parsing error", e);
            }
        } else {
            throw new ServletException("Form must have enctype=multipart/form-data.");
        }

        if (fileName != null && !fileName.isEmpty()) {
            boolean isAllowed = ALLOWED_EXTENSIONS.stream().anyMatch(fileName.toLowerCase()::endsWith);
            if (!isAllowed) {
                request.setAttribute("error", "許可されていないファイル形式です。jpg、jpeg、png、gif、bmp、webp、tiff、heicのみ許可されています。");
                request.setAttribute("prefecture", prefectures);
                request.setAttribute("city", city);
                request.getRequestDispatcher("newPost.jsp").forward(request, response);
                return;
            }
        }

        session.setAttribute("lastPostPrefecture", prefectures);
        session.setAttribute("lastPostCity", city);

        try {
            PostDAO postDAO = new PostDAO();
            Post post = new Post(userId, subject, content, prefectures, city, height, weight, age, email, userSex, isPremium, likeCount);
            post.setImagePath(SecurityUtils.sanitize(fileName)); // 画像ファイルのパスをセット
            post.setExternalId(externalId);
            post.setDeletePassword(SecurityUtils.sanitize(deletePassword));

            if (fileInputStream != null && fileName != null && !fileName.isEmpty()) {
                String s3Url = uploadToS3(SecurityUtils.sanitize(fileName), fileInputStream);
                post.setS3Url(s3Url);
            }

            postDAO.addPost(post);

            // パラメータをエンコードしてリダイレクト
            String redirectUrl = String.format("bulletinBoard?prefecture=%s&city=%s", encodedPrefecture, encodedCity);
            response.sendRedirect(redirectUrl);
        } catch (SQLException e) {
            throw new ServletException("投稿処理エラー", e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close(); // 入力ストリームを閉じる
            }
        }
    }

    private String uploadToS3(String fileName, InputStream is) throws IOException {
        S3Client s3Client = S3Util.createS3Client();
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(S3Util.BUCKET_NAME)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(is, is.available()));
        return String.format("https://%s.s3.amazonaws.com/%s", S3Util.BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
