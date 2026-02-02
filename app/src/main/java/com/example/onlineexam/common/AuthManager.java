package com.example.onlineexam.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.onlineexam.models.User;
import com.example.onlineexam.utils.DatabaseHelper;

public class AuthManager {
    private DatabaseHelper dbHelper;
    private Context context;

    public AuthManager(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public User login(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USERS 
                + " WHERE " + DatabaseHelper.COLUMN_USERNAME + " = ? "
                + " AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)));
            user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROLE)));
            user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FULL_NAME)));
        }
        
        cursor.close();
        db.close();
        
        return user;
    }

    public boolean register(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseHelper.COLUMN_EMAIL, user.getEmail());
        values.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(DatabaseHelper.COLUMN_ROLE, user.getRole());
        values.put(DatabaseHelper.COLUMN_FULL_NAME, user.getFullName());
        
        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        
        return result != -1;
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USERS 
                + " WHERE " + DatabaseHelper.COLUMN_USERNAME + " = ?";
        
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        
        cursor.close();
        db.close();
        
        return exists;
    }

    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PASSWORD, newPassword);
        
        int result = db.update(DatabaseHelper.TABLE_USERS, values,
                DatabaseHelper.COLUMN_ID + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?",
                new String[]{String.valueOf(userId), oldPassword});
        
        db.close();
        
        return result > 0;
    }
}
