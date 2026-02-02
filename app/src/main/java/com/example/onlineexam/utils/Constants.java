package com.example.onlineexam.utils;

public class Constants {
    // API Base URL (Update with your backend URL)
    public static final String BASE_URL = "http://10.0.2.2:8080/api/";
    
    // API Endpoints
    public static final String ENDPOINT_LOGIN = "auth/login";
    public static final String ENDPOINT_REGISTER = "auth/register";
    public static final String ENDPOINT_EXAMS = "exams";
    public static final String ENDPOINT_QUESTIONS = "questions";
    public static final String ENDPOINT_RESULTS = "results";
    public static final String ENDPOINT_SUBMIT_EXAM = "exams/submit";
    
    // User Roles
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_ADMIN = "ADMIN";
    
    // Exam Status
    public static final String EXAM_STATUS_UPCOMING = "UPCOMING";
    public static final String EXAM_STATUS_ACTIVE = "ACTIVE";
    public static final String EXAM_STATUS_COMPLETED = "COMPLETED";
    
    // Result Status
    public static final String RESULT_STATUS_PASS = "PASS";
    public static final String RESULT_STATUS_FAIL = "FAIL";
    
    // Request Codes
    public static final int REQUEST_CODE_LOGIN = 100;
    public static final int REQUEST_CODE_CREATE_EXAM = 101;
    public static final int REQUEST_CODE_EDIT_EXAM = 102;
    public static final int REQUEST_CODE_ADD_QUESTION = 103;
    
    // Intent Extra Keys
    public static final String EXTRA_EXAM_ID = "exam_id";
    public static final String EXTRA_EXAM_TITLE = "exam_title";
    public static final String EXTRA_RESULT_ID = "result_id";
    public static final String EXTRA_USER_ROLE = "user_role";
    
    // Shared Preferences Keys
    public static final String PREF_THEME = "theme";
    public static final String PREF_NOTIFICATION = "notification_enabled";
    
    // Time constants
    public static final int EXAM_WARNING_TIME = 5; // minutes
    public static final int AUTO_SAVE_INTERVAL = 30; // seconds
}
