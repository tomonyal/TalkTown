package com.talktown;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/imageMarket")
public class ImageMarketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ImageMarketDAO imageMarketDAO = new ImageMarketDAO();
            List<ImageMarket> imageList = imageMarketDAO.getAllImages();
            request.setAttribute("imageList", imageList);
            request.getRequestDispatcher("imageMarket.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("画像市場一覧取得エラー", e);
        }
    }
}
