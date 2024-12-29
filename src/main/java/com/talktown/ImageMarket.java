package com.talktown;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ImageMarket {
    private int imageMarketId;
    private int userId;
    private int categoryId;
    private boolean isFree;
    private BigDecimal price;
    private Date expiredDate;
    private boolean isBuy;
    private String content;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String title;
    private int height;
    private int weight;
    private int age;
    private char sex;

    // ゲッターとセッターメソッド
    public int getImageMarketId() { return imageMarketId; }
    public void setImageMarketId(int imageMarketId) { this.imageMarketId = imageMarketId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public boolean getIsFree() { return isFree; }
    public void setIsFree(boolean isFree) { this.isFree = isFree; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Date getExpiredDate() { return expiredDate; }
    public void setExpiredDate(Date expiredDate) { this.expiredDate = expiredDate; }

    public boolean getIsBuy() { return isBuy; }
    public void setIsBuy(boolean isBuy) { this.isBuy = isBuy; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Timestamp getCreateAt() { return createAt; }
    public void setCreateAt(Timestamp createAt) { this.createAt = createAt; }

    public Timestamp getUpdateAt() { return updateAt; }
    public void setUpdateAt(Timestamp updateAt) { this.updateAt = updateAt; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public char getSex() { return sex; }
    public void setSex(char sex) { this.sex = sex; }
}
