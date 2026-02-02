package com.example.onlineexam.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.onlineexam.models.User;

public class SessionManager {
    private static final String PREF_NAME = "OnlineExamSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROLE = "role";
    private static final String KEY_FULL_NAME = "fullName";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createSession(User user) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_ROLE, user.getRole());
        editor.putString(KEY_FULL_NAME, user.getFullName());
        editor.apply();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public User getLoggedInUser() {
        if (!isLoggedIn()) {
            return null;
        }

        User user = new User();
        user.setId(preferences.getInt(KEY_USER_ID, -1));
        user.setUsername(preferences.getString(KEY_USERNAME, ""));
        user.setEmail(preferences.getString(KEY_EMAIL, ""));
        user.setRole(preferences.getString(KEY_ROLE, ""));
        user.setFullName(preferences.getString(KEY_FULL_NAME, ""));
        return user;
    }

    public int getUserId() {
        return preferences.getInt(KEY_USER_ID, -1);
    }

    public String getUserRole() {
        return preferences.getString(KEY_ROLE, "");
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }

    public boolean isStudent() {
        return "STUDENT".equals(getUserRole());
    }

    public boolean isAdmin() {
        return "ADMIN".equals(getUserRole());
    }
}
