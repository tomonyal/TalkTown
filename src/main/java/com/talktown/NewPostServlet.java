package com.talktown;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 新規投稿を処理するサーブレット
 */
@WebServlet("/newPost")
public class NewPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String prefectures = request.getParameter("prefectures");
        String city = request.getParameter("city");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        boolean isPremium = Boolean.parseBoolean(request.getParameter("isPremium"));

        Post post = new Post();
        post.setSubject(subject);
        post.setContent(content);
        post.setPrefectures(prefectures);
        post.setCity(city);
        post.setHeight(height);
        post.setWeight(weight);
        post.setAge(age);
        post.setEmail(email);
        post.setSex(sex);
        post.setIsPremium(isPremium);

        PostDAO postDAO;
        try {
            postDAO = new PostDAO();
            postDAO.addPost(post);
        } catch (SQLException e) {
            throw new ServletException("投稿の追加エラー", e);
        }

        response.sendRedirect("posts");
    }
}
