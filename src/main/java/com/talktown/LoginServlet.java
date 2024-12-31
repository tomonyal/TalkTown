package com.talktown;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ログインサーブレット
 * ユーザーの認証情報を受け取り、ログイン処理を行います。
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

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
        String username = SecurityUtils.sanitize(request.getParameter("username"));
        String password = SecurityUtils.sanitize(request.getParameter("password"));

        try {
            // ユーザーの認証
            UserDAO userDAO = new UserDAO();
            User user = userDAO.authenticate(username, password);

            if (user != null && !user.isLocked()) {
                SecurityUtils.loginSucceeded(username);

                // セッションの作成
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                // クッキーにユーザー情報を保存
                Cookie userCookie = new Cookie("user", user.getUsername());
                userCookie.setMaxAge(60 * 60); // 1時間
                userCookie.setSecure(true); // セキュア属性を追加
                userCookie.setHttpOnly(true); // HttpOnly属性を追加
                response.addCookie(userCookie);

                // メインページにリダイレクト
                response.sendRedirect("mainPage.jsp");
            } else if (user != null && user.isLocked()) {
                response.setContentType("text/html");
                response.getWriter().write("アカウントがロックされています。");
            } else {
                SecurityUtils.loginFailed(username);
                logger.warning("ログインに失敗しました。残り試行回数: " + (SecurityUtils.MAX_ATTEMPT - SecurityUtils.getAttempts(username)));
                response.setContentType("text/html");
                response.getWriter().write("無効なユーザー名またはパスワードです。");
            }
        } catch (SQLException e) {
            throw new ServletException("ユーザー認証エラー", e);
        }
    }
}
