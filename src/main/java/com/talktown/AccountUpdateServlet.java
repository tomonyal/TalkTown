package com.talktown;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * アカウント情報更新サーブレット
 * ユーザーのアカウント情報を更新します。
 */
@WebServlet("/updateAccount")
public class AccountUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        String username = SecurityUtils.sanitize(request.getParameter("username"));
        String password = SecurityUtils.sanitize(request.getParameter("password"));
        String email = SecurityUtils.sanitize(request.getParameter("email"));
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
        String sex = request.getParameter("sex");

        try {
            // セッションからユーザーIDを取得
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                int userId = user.getUserId();

                // パスワードが入力された場合のみ更新
                if (password != null && !password.isEmpty()) {
                    password = SecurityUtils.hashPassword(password);
                    user.setPassword(password);
                } else {
                    password = user.getPassword(); // 現在のパスワードを維持
                }

                // ユーザー情報を更新
                UserDAO userDAO = new UserDAO();
                userDAO.updateUser(userId, username, password, email, height, weight, birthDate, sex);

                // 更新した情報をセッションに反映
                user.setUsername(username);
                user.setEmail(email);
                user.setHeight(height);
                user.setWeight(weight);
                user.setBirthDate(birthDate);
                user.setSex(sex);

                // アカウント管理ページへリダイレクト
                response.sendRedirect("accountSettings.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("ユーザー情報更新エラー", e);
        }
    }
}
