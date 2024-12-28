package com.talktown;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 投稿詳細を取得するサーブレット
 */
@WebServlet("/postDetail")
public class PostDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PostDetailServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("PostDetailServlet doGet called");

        String postIdParam = request.getParameter("id");
        logger.info("postIdParam: " + postIdParam);

        if (postIdParam != null && !postIdParam.isEmpty()) {
            try {
                int postId = Integer.parseInt(postIdParam);
                PostDAO postDAO = new PostDAO();
                Post post = postDAO.getPostById(postId);

                logger.info("Received postId: " + postId);
                if (post != null) {
                    logger.info("Post found: " + post.getSubject());
                } else {
                    logger.warning("Post not found for id: " + postId);
                }

                request.setAttribute("post", post);
            } catch (NumberFormatException e) {
                logger.severe("Invalid post ID format: " + postIdParam);
                throw new ServletException("不正な投稿ID", e);
            } catch (SQLException e) {
                logger.severe("Error fetching post details: " + e.getMessage());
                throw new ServletException("投稿詳細取得エラー", e);
            }
        } else {
            logger.warning("Post ID parameter is missing or empty.");
            throw new ServletException("不正な投稿ID");
        }
        request.getRequestDispatcher("postDetail.jsp").forward(request, response);
    }
}
