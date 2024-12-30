package com.talktown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 投稿に関連するデータベース操作を行うDAOクラス
 */
public class PostDAO {
    private Connection connection;

    public PostDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    private static final Logger logger = Logger.getLogger(PostDAO.class.getName());

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
                post.setCreateAt(resultSet.getTimestamp("created_at"));
                post.setUpdateAt(resultSet.getTimestamp("updated_at"));
                post.setS3Url(resultSet.getString("s3_url"));
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
                    post.setCreateAt(resultSet.getTimestamp("created_at"));
                    post.setUpdateAt(resultSet.getTimestamp("updated_at"));
                    post.setS3Url(resultSet.getString("s3_url"));
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
        String query = "SELECT * FROM posts WHERE prefectures = ? ORDER BY created_at DESC";
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
                    post.setCreateAt(resultSet.getTimestamp("created_at"));
                    post.setUpdateAt(resultSet.getTimestamp("updated_at"));
                    post.setS3Url(resultSet.getString("s3_url"));
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
        String query = "INSERT INTO posts (user_id, subject, content, prefectures, city, height, weight, age, email, sex, is_premium, like_count, external_id, created_at, updated_at, s3_url, delete_password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW(), ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, post.getUserId());
            pstmt.setString(2, post.getSubject());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getPrefectures());
            pstmt.setString(5, post.getCity());
            pstmt.setInt(6, post.getHeight());
            pstmt.setInt(7, post.getWeight());
            pstmt.setInt(8, post.getAge());
            pstmt.setString(9, post.getEmail());
            pstmt.setString(10, post.getSex());
            pstmt.setBoolean(11, post.isPremium());
            pstmt.setInt(12, post.getLikeCount());
            pstmt.setString(13, post.getExternalId());
            pstmt.setString(14, post.getS3Url());
            pstmt.setString(15, post.getDeletePassword());
            pstmt.executeUpdate();
        }
    }

    public Post getPostById(int postId) throws SQLException {
        String query = "SELECT * FROM posts WHERE post_id = ?";
        Post post = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, postId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    post = new Post(
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
                    post.setCreateAt(resultSet.getTimestamp("created_at"));
                    post.setUpdateAt(resultSet.getTimestamp("updated_at"));
                    post.setS3Url(resultSet.getString("s3_url"));
                    post.setExternalId(resultSet.getString("external_id"));
                    post.setDeletePassword(resultSet.getString("delete_password"));
                }
            }
        }
        return post;
    }
    // 投稿の削除
    public void deletePost(int postId) throws SQLException {
        String query = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        }
    }
}
