package com.talktown;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ユーザーに関連するデータベース操作を行うDAOクラス
 */
public class UserDAO {
    private Connection connection;

    /**
     * コンストラクタ
     * データベース接続を初期化します。
     *
     * @throws SQLException データベース接続エラー
     */
    public UserDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * ユーザーをデータベースに登録します。
     *
     * @param username ユーザー名
     * @param password パスワード
     * @param email メールアドレス
     * @param city 市
     * @param height 身長
     * @param weight 体重
     * @param birthDate 生年月日
     * @param sex 性別
     * @param type ユーザータイプ
     * @throws SQLException データベース操作エラー
     */
    public void registerUser(String username, String password, String email, String city, int height, int weight, Date birthDate, String sex, String type) throws SQLException {
        String query = "INSERT INTO users (username, password, email, city, height, weight, birth_date, sex, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, city);
            preparedStatement.setInt(5, height);
            preparedStatement.setInt(6, weight);
            preparedStatement.setDate(7, birthDate);
            preparedStatement.setString(8, sex);
            preparedStatement.setString(9, type);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * ユーザーの認証を行います。
     *
     * @param username ユーザー名
     * @param password パスワード
     * @return 認証成功の場合はtrue、それ以外はfalse
     * @throws SQLException データベース操作エラー
     */
    public boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    /**
     * ユーザーを認証します
     *
     * @param username ユーザー名
     * @param password パスワード
     * @return 認証されたユーザー
     * @throws SQLException データベース操作エラー
     */
    public User authenticate(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password")); // Passwordの設定
                    user.setEmail(resultSet.getString("email"));
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * ユーザーIDによってユーザー情報を取得します
     *
     * @param userId ユーザーID
     * @return ユーザー情報
     * @throws SQLException データベース操作エラー
     */
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    return user;
                }
            }
        }
        return null;
    }
}
