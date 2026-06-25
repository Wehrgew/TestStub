package org.example;

import java.sql.Date;

public class User {

    private String login;
    private String password;
    private Date createdDate;
    private String email;

    public User() {
    }

    public User(String login, String password,
                Date createdDate, String email) {
        this.login = login;
        this.password = password;
        this.createdDate = createdDate;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", email='" + email + '\'' +
                '}';
    }
}