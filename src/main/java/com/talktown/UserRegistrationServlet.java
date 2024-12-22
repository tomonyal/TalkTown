package com.talktown;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ユーザー登録サーブレット
 * ユーザーの登録情報を受け取り、データベースに保存します。
 */
@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POSTリクエストを処理します。
     * ユーザー情報を受け取り、登録処理を行います。
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
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        Date birthDate = Date.valueOf(request.getParameter("birth_date"));
        String sex = request.getParameter("sex");
        String type = "nomal"; // デフォルトのユーザータイプを設定

        UserDAO userDAO;
        try {
            // ユーザー情報をデータベースに保存
            userDAO = new UserDAO();
            userDAO.registerUser(username, password, email, city, height, weight, birthDate, sex, type);

            // 登録完了画面へリダイレクト
            response.sendRedirect("completeRegistration.jsp");
        } catch (SQLException e) {
            throw new ServletException("ユーザー登録エラー", e);
        }
    }
}
