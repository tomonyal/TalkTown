package com.talktown;

import java.sql.Date;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String type;
    private int point;
    private String city;
    private int height;
    private int weight;
    private Date birthDate;
    private String sex;

    // コンストラクタ
    public User(int userId, String username, String password, String email, String type, int point, String city, int height, int weight, Date birthDate, String sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.point = point;
        this.city = city;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    // ゲッターとセッター
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        long ageInMillis = System.currentTimeMillis() - birthDate.getTime();
        return (int) (ageInMillis / 1000L / 60L / 60L / 24L / 365L);
    }
}
