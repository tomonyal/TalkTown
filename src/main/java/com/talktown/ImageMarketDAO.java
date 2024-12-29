package com.talktown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageMarketDAO {
    private Connection connection;

    public ImageMarketDAO() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    // getAllImagesメソッドの追加
    public List<ImageMarket> getAllImages() throws SQLException {
        List<ImageMarket> imageList = new ArrayList<>();
        String query = "SELECT im.*, u.height, u.weight, u.sex, " +
                "DATE_PART('year', AGE(u.birth_date)) AS age " +
                "FROM ImageMarket im " +
                "JOIN users u ON im.user_id = u.user_id " +
                "ORDER BY im.create_at DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ImageMarket image = new ImageMarket();
                image.setImageMarketId(rs.getInt("image_market_id"));
                image.setUserId(rs.getInt("user_id"));
                image.setCategoryId(rs.getInt("category_id"));
                image.setIsFree(rs.getBoolean("is_free"));
                image.setPrice(rs.getBigDecimal("price"));
                image.setExpiredDate(rs.getDate("expired_date"));
                image.setIsBuy(rs.getBoolean("is_buy"));
                image.setContent(rs.getString("content"));
                image.setCreateAt(rs.getTimestamp("create_at"));
                image.setUpdateAt(rs.getTimestamp("update_at"));
                image.setTitle(rs.getString("title"));
                image.setHeight(rs.getInt("height"));
                image.setWeight(rs.getInt("weight"));
                image.setAge(rs.getInt("age"));
                image.setSex(rs.getString("sex").charAt(0));
                imageList.add(image);
            }
        }
        return imageList;
    }

    // getImageByIdメソッドの追加
    public ImageMarket getImageById(int imageMarketId) throws SQLException {
        String query = "SELECT im.*, u.height, u.weight, u.sex, " +
                "DATE_PART('year', AGE(u.birth_date)) AS age " +
                "FROM ImageMarket im " +
                "JOIN users u ON im.user_id = u.user_id " +
                "WHERE im.image_market_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, imageMarketId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ImageMarket image = new ImageMarket();
                    image.setImageMarketId(rs.getInt("image_market_id"));
                    image.setUserId(rs.getInt("user_id"));
                    image.setCategoryId(rs.getInt("category_id"));
                    image.setIsFree(rs.getBoolean("is_free"));
                    image.setPrice(rs.getBigDecimal("price"));
                    image.setExpiredDate(rs.getDate("expired_date"));
                    image.setIsBuy(rs.getBoolean("is_buy"));
                    image.setContent(rs.getString("content"));
                    image.setCreateAt(rs.getTimestamp("create_at"));
                    image.setUpdateAt(rs.getTimestamp("update_at"));
                    image.setTitle(rs.getString("title"));
                    image.setHeight(rs.getInt("height"));
                    image.setWeight(rs.getInt("weight"));
                    image.setAge(rs.getInt("age"));
                    image.setSex(rs.getString("sex").charAt(0));
                    return image;
                }
            }
        }
        return null;
    }
}
