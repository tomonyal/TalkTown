package com.talktown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAO();
            if (userDAO.validateUser(username, password)) {
                response.setContentType("text/html");
                response.getWriter().write("Login successful!");
            } else {
                response.setContentType("text/html");
                response.getWriter().write("Invalid username or password.");
            }
        } catch (SQLException e) {
            throw new ServletException("Error validating user", e);
        }
    }
}
