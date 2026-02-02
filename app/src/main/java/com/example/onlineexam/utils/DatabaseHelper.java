package com.example.onlineexam.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "online_exam.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_EXAMS = "exams";
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_ANSWERS = "answers";
    public static final String TABLE_RESULTS = "exam_results";

    // Common columns
    public static final String COLUMN_ID = "id";

    // User table columns
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_FULL_NAME = "full_name";

    // Exam table columns
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_TOTAL_MARKS = "total_marks";
    public static final String COLUMN_PASSING_MARKS = "passing_marks";
    public static final String COLUMN_START_TIME = "start_time";
    public static final String COLUMN_END_TIME = "end_time";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_TOTAL_QUESTIONS = "total_questions";

    // Question table columns
    public static final String COLUMN_EXAM_ID = "exam_id";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_OPTION_A = "option_a";
    public static final String COLUMN_OPTION_B = "option_b";
    public static final String COLUMN_OPTION_C = "option_c";
    public static final String COLUMN_OPTION_D = "option_d";
    public static final String COLUMN_CORRECT_ANSWER = "correct_answer";
    public static final String COLUMN_MARKS = "marks";

    // Answer table columns
    public static final String COLUMN_QUESTION_ID = "question_id";
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_SELECTED_ANSWER = "selected_answer";
    public static final String COLUMN_IS_CORRECT = "is_correct";

    // Result table columns
    public static final String COLUMN_OBTAINED_MARKS = "obtained_marks";
    public static final String COLUMN_PERCENTAGE = "percentage";
    public static final String COLUMN_SUBMITTED_AT = "submitted_at";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT NOT NULL,"
                + COLUMN_ROLE + " TEXT NOT NULL,"
                + COLUMN_FULL_NAME + " TEXT"
                + ")";

        // Create Exams table
        String CREATE_EXAMS_TABLE = "CREATE TABLE " + TABLE_EXAMS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT NOT NULL,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DURATION + " INTEGER,"
                + COLUMN_TOTAL_MARKS + " INTEGER,"
                + COLUMN_PASSING_MARKS + " INTEGER,"
                + COLUMN_START_TIME + " TEXT,"
                + COLUMN_END_TIME + " TEXT,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_TOTAL_QUESTIONS + " INTEGER"
                + ")";

        // Create Questions table
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EXAM_ID + " INTEGER,"
                + COLUMN_QUESTION_TEXT + " TEXT NOT NULL,"
                + COLUMN_OPTION_A + " TEXT,"
                + COLUMN_OPTION_B + " TEXT,"
                + COLUMN_OPTION_C + " TEXT,"
                + COLUMN_OPTION_D + " TEXT,"
                + COLUMN_CORRECT_ANSWER + " TEXT,"
                + COLUMN_MARKS + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_EXAM_ID + ") REFERENCES " + TABLE_EXAMS + "(" + COLUMN_ID + ")"
                + ")";

        // Create Answers table
        String CREATE_ANSWERS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_QUESTION_ID + " INTEGER,"
                + COLUMN_EXAM_ID + " INTEGER,"
                + COLUMN_STUDENT_ID + " INTEGER,"
                + COLUMN_SELECTED_ANSWER + " TEXT,"
                + COLUMN_IS_CORRECT + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_QUESTION_ID + ") REFERENCES " + TABLE_QUESTIONS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_EXAM_ID + ") REFERENCES " + TABLE_EXAMS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_STUDENT_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")"
                + ")";

        // Create Results table
        String CREATE_RESULTS_TABLE = "CREATE TABLE " + TABLE_RESULTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_EXAM_ID + " INTEGER,"
                + COLUMN_STUDENT_ID + " INTEGER,"
                + COLUMN_OBTAINED_MARKS + " INTEGER,"
                + COLUMN_TOTAL_MARKS + " INTEGER,"
                + COLUMN_PERCENTAGE + " REAL,"
                + COLUMN_STATUS + " TEXT,"
                + COLUMN_SUBMITTED_AT + " TEXT,"
                + "FOREIGN KEY(" + COLUMN_EXAM_ID + ") REFERENCES " + TABLE_EXAMS + "(" + COLUMN_ID + "),"
                + "FOREIGN KEY(" + COLUMN_STUDENT_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_ID + ")"
                + ")";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_EXAMS_TABLE);
        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_ANSWERS_TABLE);
        db.execSQL(CREATE_RESULTS_TABLE);

        // Insert default admin user
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_USERNAME + ", " 
                + COLUMN_PASSWORD + ", " + COLUMN_ROLE + ", " + COLUMN_FULL_NAME + ") "
                + "VALUES ('admin', 'admin123', 'ADMIN', 'System Administrator')");

        // Insert default student user
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_USERNAME + ", " 
                + COLUMN_PASSWORD + ", " + COLUMN_ROLE + ", " + COLUMN_FULL_NAME + ") "
                + "VALUES ('student', 'student123', 'STUDENT', 'Demo Student')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
}
