package com.talktown;

import java.sql.Timestamp;

public class Post {
    private int postId;
    private int userId;
    private String subject;
    private String content;
    private String prefectures;
    private String city;
    private int height;
    private int weight;
    private int age;
    private String email;
    private String sex;
    private boolean isPremium;
    private int likeCount;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String imagePath;
    private String s3Url;
    private String externalId;
    private String deletePassword;

    // コンストラクタ
    public Post(int userId, String subject, String content, String prefectures, String city, int height, int weight, int age, String email, String sex, boolean isPremium, int likeCount) {
        this.userId = userId;
        this.subject = subject;
        this.content = content;
        this.prefectures = prefectures;
        this.city = city;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.email = email;
        this.sex = sex;
        this.isPremium = isPremium;
        this.likeCount = likeCount;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }    

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrefectures() {
        return prefectures;
    }

    public void setPrefectures(String prefectures) {
        this.prefectures = prefectures;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setIsPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setDeletePassword(String deletePassword) {
        this.deletePassword = deletePassword;
    }

    public String getDeletePassword() {
        return deletePassword;
    }
}
