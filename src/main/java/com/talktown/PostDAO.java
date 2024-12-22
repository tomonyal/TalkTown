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
                Post post = new Post();
                post.setPostId(resultSet.getInt("post_id"));
                post.setUserId(resultSet.getInt("user_id"));
                post.setCategoryId(resultSet.getInt("category_id"));
                post.setSubject(resultSet.getString("subject"));
                post.setContent(resultSet.getString("content"));
                post.setPrefectures(resultSet.getString("prefectures"));
                post.setCity(resultSet.getString("city"));
                post.setHeight(resultSet.getInt("height"));
                post.setWeight(resultSet.getInt("weight"));
                post.setAge(resultSet.getInt("age"));
                post.setEmail(resultSet.getString("email"));
                post.setSex(resultSet.getString("sex"));
                post.setIsPremium(resultSet.getBoolean("is_premium"));
                post.setLikeCount(resultSet.getInt("like_count"));
                post.setCreateAt(resultSet.getDate("create_at"));
                post.setUpdateAt(resultSet.getDate("update_at"));
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
                    Post post = new Post();
                    post.setPostId(resultSet.getInt("post_id"));
                    post.setUserId(resultSet.getInt("user_id"));
                    post.setCategoryId(resultSet.getInt("category_id"));
                    post.setSubject(resultSet.getString("subject"));
                    post.setContent(resultSet.getString("content"));
                    post.setPrefectures(resultSet.getString("prefectures"));
                    post.setCity(resultSet.getString("city"));
                    post.setHeight(resultSet.getInt("height"));
                    post.setWeight(resultSet.getInt("weight"));
                    post.setAge(resultSet.getInt("age"));
                    post.setEmail(resultSet.getString("email"));
                    post.setSex(resultSet.getString("sex"));
                    post.setIsPremium(resultSet.getBoolean("is_premium"));
                    post.setLikeCount(resultSet.getInt("like_count"));
                    post.setCreateAt(resultSet.getDate("create_at"));
                    post.setUpdateAt(resultSet.getDate("update_at"));
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
                    Post post = new Post();
                    post.setPostId(resultSet.getInt("post_id"));
                    post.setUserId(resultSet.getInt("user_id"));
                    post.setCategoryId(resultSet.getInt("category_id"));
                    post.setSubject(resultSet.getString("subject"));
                    post.setContent(resultSet.getString("content"));
                    post.setPrefectures(resultSet.getString("prefectures"));
                    post.setCity(resultSet.getString("city"));
                    post.setHeight(resultSet.getInt("height"));
                    post.setWeight(resultSet.getInt("weight"));
                    post.setAge(resultSet.getInt("age"));
                    post.setEmail(resultSet.getString("email"));
                    post.setSex(resultSet.getString("sex"));
                    post.setIsPremium(resultSet.getBoolean("is_premium"));
                    post.setLikeCount(resultSet.getInt("like_count"));
                    post.setCreateAt(resultSet.getDate("create_at"));
                    post.setUpdateAt(resultSet.getDate("update_at"));
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
        String query = "INSERT INTO posts (userId, categoryId, subject, content, prefectures, city, height, weight, age, email, sex, isPremium, likeCount, createAt, updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, post.getUserId());
            preparedStatement.setInt(2, post.getCategoryId());
            preparedStatement.setString(3, post.getSubject());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.setString(5, post.getPrefectures());
            preparedStatement.setString(6, post.getCity());
            preparedStatement.setInt(7, post.getHeight());
            preparedStatement.setInt(8, post.getWeight());
            preparedStatement.setInt(9, post.getAge());
            preparedStatement.setString(10, post.getEmail());
            preparedStatement.setString(11, post.getSex());
            preparedStatement.setBoolean(12, post.isPremium());
            preparedStatement.setInt(13, post.getLikeCount());
            preparedStatement.executeUpdate();
        }
    }
}
