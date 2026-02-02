package com.example.onlineexam.admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineexam.R;
import com.example.onlineexam.models.Exam;
import com.example.onlineexam.utils.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ManageExamsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewExams;
    private FloatingActionButton fabAddExam;
    private DatabaseHelper dbHelper;
    private List<Exam> examList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_exams);

        recyclerViewExams = findViewById(R.id.recyclerViewExams);
        fabAddExam = findViewById(R.id.fabAddExam);

        recyclerViewExams.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);

        fabAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageExamsActivity.this, CreateExamActivity.class);
                startActivity(intent);
            }
        });

        loadExams();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                exam.setPassingMarks(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSING_MARKS)));
                exam.setTotalQuestions(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TOTAL_QUESTIONS)));

                examList.add(exam);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }
}
