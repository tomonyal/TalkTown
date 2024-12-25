package com.talktown;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * アカウント設定サーブレット
 * ユーザーのアカウント情報を取得し、セッションに格納します。
 */
@WebServlet("/accountSettings")
public class AccountSettingsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            try {
                UserDAO userDAO = new UserDAO();
                User updatedUser = userDAO.getUserById(user.getUserId());
                session.setAttribute("user", updatedUser);
                response.sendRedirect("accountSettings.jsp");
            } catch (SQLException e) {
                throw new ServletException("ユーザー情報の取得に失敗しました", e);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
