package com.example.onlineexam.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineexam.R;
import com.example.onlineexam.models.Exam;
import com.example.onlineexam.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ExamListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewExams;
    private DatabaseHelper dbHelper;
    private List<Exam> examList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);

        recyclerViewExams = findViewById(R.id.recyclerViewExams);
        recyclerViewExams.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        loadExams();
    }

    private void loadExams() {
        examList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_EXAMS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Exam exam = new Exam();
                exam.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
                exam.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE)));
                exam.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DESCRIPTION)));
                exam.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DURATION)));
                exam.setTotalMarks(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TOTAL_MARKS)));
                exam.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS)));
                exam.setTotalQuestions(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TOTAL_QUESTIONS)));

                examList.add(exam);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        if (examList.isEmpty()) {
            Toast.makeText(this, "No exams available", Toast.LENGTH_SHORT).show();
        }
    }
}
