package com.capitalistlepton.xange.model;

import android.support.annotation.Nullable;

import com.capitalistlepton.xange.util.GetRequest;

import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String username;
    private String password;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Nullable
    public static User login(String username, String password) {
        try {
            GetRequest req = new GetRequest();
//            JSONObject res = req.execute("/users" + URLEncoder.encode(username)).get();

            Map<String, String> json = new HashMap<>();
            json.put("username", "bla");
            json.put("password", BCrypt.hashpw("password", BCrypt.gensalt()));
            JSONObject res = new JSONObject(json);

            if (res == null || res.has("message")
                    || !BCrypt.checkpw(password, res.getString("password"))) {
                return null;
            } else {
                return new User(res.getString("username"), res.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
