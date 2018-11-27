package com.capitalistlepton.xange.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private String username;
    private String password;

    private User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User verifyLogin(String username, String password, String hash) {
        User user = null;
        if (BCrypt.checkpw(password, hash)) {
            user = new User(username, password);
        }
        return user;
    }
}
