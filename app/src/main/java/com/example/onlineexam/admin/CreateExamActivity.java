package com.example.onlineexam.admin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineexam.R;
import com.example.onlineexam.utils.DatabaseHelper;

public class CreateExamActivity extends AppCompatActivity {
    private EditText etExamTitle;
    private EditText etExamDescription;
    private EditText etDuration;
    private EditText etTotalMarks;
    private EditText etPassingMarks;
    private Button btnCreateExam;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        dbHelper = new DatabaseHelper(this);

        etExamTitle = findViewById(R.id.etExamTitle);
        etExamDescription = findViewById(R.id.etExamDescription);
        etDuration = findViewById(R.id.etDuration);
        etTotalMarks = findViewById(R.id.etTotalMarks);
        etPassingMarks = findViewById(R.id.etPassingMarks);
        btnCreateExam = findViewById(R.id.btnCreateExam);

        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExam();
            }
        });
    }

    private void createExam() {
        String title = etExamTitle.getText().toString().trim();
        String description = etExamDescription.getText().toString().trim();
        String durationStr = etDuration.getText().toString().trim();
        String totalMarksStr = etTotalMarks.getText().toString().trim();
        String passingMarksStr = etPassingMarks.getText().toString().trim();

        if (title.isEmpty() || durationStr.isEmpty() || totalMarksStr.isEmpty() || passingMarksStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = Integer.parseInt(durationStr);
        int totalMarks = Integer.parseInt(totalMarksStr);
        int passingMarks = Integer.parseInt(passingMarksStr);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        values.put(DatabaseHelper.COLUMN_DURATION, duration);
        values.put(DatabaseHelper.COLUMN_TOTAL_MARKS, totalMarks);
        values.put(DatabaseHelper.COLUMN_PASSING_MARKS, passingMarks);
        values.put(DatabaseHelper.COLUMN_STATUS, "UPCOMING");
        values.put(DatabaseHelper.COLUMN_TOTAL_QUESTIONS, 0);

        long result = db.insert(DatabaseHelper.TABLE_EXAMS, null, values);
        db.close();

        if (result != -1) {
            Toast.makeText(this, R.string.success_exam_created, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
        }
    }
}
