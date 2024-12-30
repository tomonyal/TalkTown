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
     * ユーザー情報を更新します
     *
     * @param userId ユーザーID
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
    public void updateUser(int userId, String username, String password, String email, int height, int weight, Date birthDate, String sex) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, height = ?, weight = ?, birth_date = ?, sex = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, height);
            preparedStatement.setInt(5, weight);
            preparedStatement.setDate(6, birthDate);
            preparedStatement.setString(7, sex);
            preparedStatement.setInt(8, userId);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * ユーザーIDによってユーザー情報を取得します
     *
     * @param userId ユーザーID
     * @return ユーザー情報
     * @throws SQLException データベース操作エラー
     */
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT user_id, username, password, email, type, point, city, height, weight, birth_date, sex FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("type"),
                        resultSet.getInt("point"),
                        resultSet.getString("city"),
                        resultSet.getInt("height"),
                        resultSet.getInt("weight"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("sex")
                    );
                    return user;
                }
            }
        }
        return null;
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
        String query = "SELECT user_id, username, password, email, type, point, city, height, weight, birth_date, sex, is_locked FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    if (SecurityUtils.checkPassword(password, hashedPassword)) { // パスワードの検証
                        User user = new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("type"),
                            resultSet.getInt("point"),
                            resultSet.getString("city"),
                            resultSet.getInt("height"),
                            resultSet.getInt("weight"),
                            resultSet.getDate("birth_date"),
                            resultSet.getString("sex")
                        );
                        user.setLocked(resultSet.getBoolean("is_locked"));
                        return user;
                    }
                }
            }
        }
        return null;
    } 

    public void lockUser(String username) throws SQLException {
        String query = "UPDATE users SET is_locked = TRUE WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }
    
    public User findUserByUsername(String username) throws SQLException {
        String query = "SELECT user_id, username, password, email, type, point, city, height, weight, birth_date, sex, is_locked FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("type"),
                        resultSet.getInt("point"),
                        resultSet.getString("city"),
                        resultSet.getInt("height"),
                        resultSet.getInt("weight"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("sex")
                    );
                    user.setLocked(resultSet.getBoolean("is_locked"));
                    return user;
                }
            }
        }
        return null;
    }

    public void registerUser(String username, String password, String email, String city, int height, int weight, Date birthDate, String sex, String type) throws SQLException {
        String query = "INSERT INTO users (username, password, email, city, height, weight, birth_date, sex, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, SecurityUtils.hashPassword(password)); // ハッシュ化されたパスワードを保存
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
    // ユーザー名の重複チェックメソッド
    public boolean isUsernameTaken(String username) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
