package com.talktown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 投稿に関連するデータベース操作を行うDAOクラス
 */
public class PostDAO {
    private Connection connection;

    public PostDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * 全ての投稿を取得します。
     *
     * @return 投稿のリスト
     * @throws SQLException データベース操作エラー
     */
    public List<Post> getAllPosts() throws SQLException {
        String query = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Post post = new Post(
                    resultSet.getInt("user_id"),
                    resultSet.getString("subject"),
                    resultSet.getString("content"),
                    resultSet.getString("prefectures"),
                    resultSet.getString("city"),
                    resultSet.getInt("height"),
                    resultSet.getInt("weight"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getString("sex"),
                    resultSet.getBoolean("is_premium"),
                    resultSet.getInt("like_count")
                );
                post.setPostId(resultSet.getInt("post_id"));
                post.setCreateAt(resultSet.getDate("created_at"));
                post.setUpdateAt(resultSet.getDate("updated_at"));
                posts.add(post);
            }
        }
        return posts;
    }

    /**
     * 市区町村で投稿を絞り込みます。
     *
     * @param city 市区町村
     * @return 投稿のリスト
     * @throws SQLException データベース操作エラー
     */
    public List<Post> getPostsByCity(String city) throws SQLException {
        String query = "SELECT * FROM posts WHERE city = ?";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post(
                        resultSet.getInt("user_id"),
                        resultSet.getString("subject"),
                        resultSet.getString("content"),
                        resultSet.getString("prefectures"),
                        resultSet.getString("city"),
                        resultSet.getInt("height"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("sex"),
                        resultSet.getBoolean("is_premium"),
                        resultSet.getInt("like_count")
                    );
                    post.setPostId(resultSet.getInt("post_id"));
                    post.setCreateAt(resultSet.getDate("created_at"));
                    post.setUpdateAt(resultSet.getDate("updated_at"));
                    posts.add(post);
                }
            }
        }
        return posts;
    }

    /**
     * 都道府県で投稿を絞り込みます。
     *
     * @param prefectures 都道府県
     * @return 投稿のリスト
     * @throws SQLException データベース操作エラー
     */
    public List<Post> getPostsByPrefectures(String prefectures) throws SQLException {
        String query = "SELECT * FROM posts WHERE prefectures = ?";
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, prefectures);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post(
                        resultSet.getInt("user_id"),
                        resultSet.getString("subject"),
                        resultSet.getString("content"),
                        resultSet.getString("prefectures"),
                        resultSet.getString("city"),
                        resultSet.getInt("height"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("sex"),
                        resultSet.getBoolean("is_premium"),
                        resultSet.getInt("like_count")
                    );
                    post.setPostId(resultSet.getInt("post_id"));
                    post.setCreateAt(resultSet.getDate("created_at"));
                    post.setUpdateAt(resultSet.getDate("updated_at"));
                    posts.add(post);
                }
            }
        }
        return posts;
    }

    /**
     * 新規投稿を追加します。
     *
     * @param post 新規投稿
     * @throws SQLException データベース操作エラー
     */
    public void addPost(Post post) throws SQLException {
        String query = "INSERT INTO posts (user_id, subject, content, prefectures, city, height, weight, age, email, sex, is_premium, like_count, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, post.getUserId());
            preparedStatement.setString(2, post.getSubject());
            preparedStatement.setString(3, post.getContent());
            preparedStatement.setString(4, post.getPrefectures());
            preparedStatement.setString(5, post.getCity());
            preparedStatement.setInt(6, post.getHeight());
            preparedStatement.setInt(7, post.getWeight());
            preparedStatement.setInt(8, post.getAge());
            preparedStatement.setString(9, post.getEmail());
            preparedStatement.setString(10, post.getSex());
            preparedStatement.setBoolean(11, post.isPremium());
            preparedStatement.setInt(12, post.getLikeCount());
            preparedStatement.executeUpdate();
        }
    }
}
