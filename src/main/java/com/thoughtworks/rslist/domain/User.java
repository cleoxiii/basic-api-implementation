package com.thoughtworks.rslist.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class User {
    @NotNull
    @Size(max = 8)
    @JsonProperty("user_name")
    @JsonAlias("userName")
    private String userName;

    @NotNull
    @JsonProperty("user_gender")
    @JsonAlias("gender")
    private String gender;

    @NotNull
    @Min(18)
    @Max(100)
    @JsonProperty("user_age")
    @JsonAlias("age")
    private int age;

    @Email
    @JsonProperty("user_email")
    @JsonAlias("email")
    private String email;

    @NotNull
    @Pattern(regexp = "1\\d{10}")
    @JsonProperty("user_phone")
    @JsonAlias("phone")
    private String phone;

    public User(String userName, String gender, int age, String email, String phone) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
