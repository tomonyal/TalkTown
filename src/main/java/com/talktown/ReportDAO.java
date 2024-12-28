package com.talktown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportDAO {

    public void insertReport(int userId, int targetId, String content) throws SQLException {
        String sql = "INSERT INTO reports (user_id, target_id, content, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, targetId);
            preparedStatement.setString(3, content);
            preparedStatement.executeUpdate();
        }
    }
}
