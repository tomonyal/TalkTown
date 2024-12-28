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
 * 管理者への要望や違反報告を処理するサーブレット
 */
@WebServlet("/submitRequest")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestType = request.getParameter("requestType");
        String requestContent = request.getParameter("requestContent");

        HttpSession session = request.getSession(false);
        int userId = (session != null && session.getAttribute("user_id") != null) ? (int) session.getAttribute("user_id") : 0;
        int targetId = 0; // 必要に応じて適切な target_id を設定してください
        String content = requestType + ": " + requestContent;

        try {
            ReportDAO reportDAO = new ReportDAO();
            reportDAO.insertReport(userId, targetId, content);
        } catch (SQLException e) {
            throw new ServletException("データベースエラー", e);
        }

        // 確認ページにリダイレクト
        response.sendRedirect("confirmation.jsp");
    }
}
