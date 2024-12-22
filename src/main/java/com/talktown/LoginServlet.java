package com.talktown;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ログインサーブレット
 * ユーザーの認証情報を受け取り、ログイン処理を行います。
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POSTリクエストを処理します。
     * ユーザー情報を受け取り、認証処理を行います。
     *
     * @param request  HttpServletRequestオブジェクト
     * @param response HttpServletResponseオブジェクト
     * @throws ServletException サーブレットエラー
     * @throws IOException 入出力エラー
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータの取得
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // ユーザーの認証
            UserDAO userDAO = new UserDAO();
            if (userDAO.validateUser(username, password)) {
                response.setContentType("text/html");
                response.getWriter().write("ログイン成功！");
            } else {
                response.setContentType("text/html");
                response.getWriter().write("無効なユーザー名またはパスワードです。");
            }
        } catch (SQLException e) {
            throw new ServletException("ユーザー認証エラー", e);
        }
    }
}
