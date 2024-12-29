package com.talktown;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/uploadImageServlet")
@MultipartConfig
public class UploadImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UploadImageServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (session != null && session.getAttribute("user") != null) ? (User) session.getAttribute("user") : null;

        boolean isMultipart = request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/");
        Map<String, String> formFields = new HashMap<>();
        String fileName = null; // アップロードされたファイル名
        InputStream fileInputStream = null;

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

        String price = formFields.get("price");
        int expiredDays = Integer.parseInt(formFields.get("expiredDate"));
        String content = formFields.get("content");

        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            // S3にファイルアップロード
            if (fileInputStream != null && fileName != null && !fileName.isEmpty()) {
                String s3Url = S3Util.uploadToS3(fileName, fileInputStream);

                // データベースに情報保存
                String query = "INSERT INTO ImageMarket (user_id, category_id, is_free, price, expired_date, is_buy, content, create_at, update_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
                PreparedStatement pstmt = connection.prepareStatement(query);

                int userId = user != null ? user.getUserId() : 0;
                int categoryId = 1; // カテゴリIDを指定する場合はここで設定
                boolean isFree = "0".equals(price);
                Date expiredDate = Date.valueOf(LocalDate.now().plusDays(expiredDays));

                pstmt.setInt(1, userId);
                pstmt.setInt(2, categoryId);
                pstmt.setBoolean(3, isFree);
                pstmt.setBigDecimal(4, new BigDecimal(price));
                pstmt.setDate(5, expiredDate);
                pstmt.setBoolean(6, false); // 初期値として購入済みをfalseに設定
                pstmt.setString(7, content);
                pstmt.executeUpdate();

                logger.info("Image uploaded successfully");
            }

            response.sendRedirect("imageMarket.jsp");
        } catch (SQLException e) {
            logger.severe("Error uploading image: " + e.getMessage());
            throw new ServletException("画像アップロードエラー", e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close(); // 入力ストリームを閉じる
            }
        }
    }
}
