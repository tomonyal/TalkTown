package com.talktown;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deletePassword = SecurityUtils.sanitize(request.getParameter("deletePassword"));
        int postId = Integer.parseInt(request.getParameter("postId"));

        try {
            PostDAO postDAO = new PostDAO();
            Post post = postDAO.getPostById(postId);

            if (post != null && post.getDeletePassword().equals(deletePassword)) {
                // 投稿を削除
                postDAO.deletePost(postId);

                // 画像をS3から削除
                String imagePath = post.getS3Url();
                if (imagePath != null && !imagePath.isEmpty()) {
                    String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
                    S3Util.deleteFromS3(fileName);
                }

                response.sendRedirect("deleteSuccess.jsp");
            } else {
                response.sendRedirect("deleteFailed.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("投稿削除エラー", e);
        }
    }
}
