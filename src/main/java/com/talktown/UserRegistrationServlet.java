package com.talktown;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserDAO userDAO;
        try {
            userDAO = new UserDAO();
            userDAO.registerUser(username, password, email);
            response.setContentType("text/html");
            response.getWriter().write("User registered successfully!");
        } catch (SQLException e) {
            throw new ServletException("Error registering user", e);
        }
    }
}
