package com.talktown;

import java.io.IOException;
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
        PostDAO postDAO;
        List<Post> posts = null;
        try {
            postDAO = new PostDAO();
            if (city == null || city.equals("null") || city.isEmpty()) {
                posts = postDAO.getPostsByPrefectures(prefecture);
            } else {
                posts = postDAO.getPostsByCity(city);
            }
        } catch (SQLException e) {
            throw new ServletException("投稿取得エラー", e);
        }
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("bulletinBoard.jsp").forward(request, response);
    }
}
