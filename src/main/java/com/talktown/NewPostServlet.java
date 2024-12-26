package com.talktown;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.Part;

/**
 * 新規投稿サーブレット
 * ユーザーの投稿情報を処理し、データベースに保存します。
 */
@WebServlet("/newPost")
@MultipartConfig
public class NewPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションからユーザー情報を取得
        jakarta.servlet.http.HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");

        // Check if this is a file upload request
        boolean isMultipart = request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/");
        Map<String, String> formFields = new HashMap<>();

        if (isMultipart) {
            try {
                for (Part part : request.getParts()) {
                    if (part.getSubmittedFileName() == null) {
                        // It's a form field
                        formFields.put(part.getName(), new String(part.getInputStream().readAllBytes(), "UTF-8"));
                    } else {
                        // It's a file upload
                        // Handle file upload here (not required for this example)
                    }
                }
            } catch (Exception e) {
                throw new ServletException("Multipart form parsing error", e);
            }
        } else {
            throw new ServletException("Form must has enctype=multipart/form-data.");
        }

        // リクエストパラメータの取得
        String name = formFields.get("name");
        String prefectures = formFields.get("prefectures");
        String city = formFields.get("city");
        int height = parseIntOrDefault(formFields.get("height"), user.getHeight());
        int weight = parseIntOrDefault(formFields.get("weight"), user.getWeight());
        int age = parseIntOrDefault(formFields.get("age"), user.getAge());
        String subject = formFields.get("subject");
        String content = formFields.get("content");
        String email = formFields.get("email");
        String deletePassword = formFields.get("deletePassword");
        boolean isPremium = "premium".equals(user.getType());
        int likeCount = 0;

        // デバッグログ
        System.out.println("New Post - User ID: " + user.getUserId());
        System.out.println("Name: " + name);
        System.out.println("Prefectures: " + prefectures);
        System.out.println("City: " + city);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("Age: " + age);
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
        System.out.println("Email: " + email);
        System.out.println("Delete Password: " + deletePassword);
        System.out.println("Is Premium: " + isPremium);

        try {
            // 投稿情報をデータベースに保存
            PostDAO postDAO = new PostDAO();
            Post post = new Post(user.getUserId(), subject, content, prefectures, city, height, weight, age, email, user.getSex(), isPremium, likeCount);
            postDAO.addPost(post);

            // 投稿完了後に掲示板ページへリダイレクト
            response.sendRedirect("bulletinBoard.jsp");
        } catch (SQLException e) {
            throw new ServletException("投稿処理エラー", e);
        }
    }

    // nullまたは空の文字列をデフォルト値に変換するヘルパーメソッド
    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
