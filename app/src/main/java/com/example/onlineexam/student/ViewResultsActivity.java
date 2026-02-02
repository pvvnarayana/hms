package com.example.onlineexam.student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineexam.R;
import com.example.onlineexam.models.ExamResult;
import com.example.onlineexam.utils.DatabaseHelper;
import com.example.onlineexam.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class ViewResultsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewResults;
    private DatabaseHelper dbHelper;
    private SessionManager sessionManager;
    private List<ExamResult> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);

        recyclerViewResults = findViewById(R.id.recyclerViewResults);
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        loadResults();
    }

    private void loadResults() {
        resultList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        int studentId = sessionManager.getUserId();

        String query = "SELECT r.*, e." + DatabaseHelper.COLUMN_TITLE + " as exam_title FROM " 
                + DatabaseHelper.TABLE_RESULTS + " r "
                + "INNER JOIN " + DatabaseHelper.TABLE_EXAMS + " e ON r." 
                + DatabaseHelper.COLUMN_EXAM_ID + " = e." + DatabaseHelper.COLUMN_ID
                + " WHERE r." + DatabaseHelper.COLUMN_STUDENT_ID + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(studentId)});

        if (cursor.moveToFirst()) {
            do {
                ExamResult result = new ExamResult();
                result.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
                result.setExamId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXAM_ID)));
                result.setStudentId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_ID)));
                result.setObtainedMarks(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OBTAINED_MARKS)));
                result.setTotalMarks(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TOTAL_MARKS)));
                result.setPercentage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PERCENTAGE)));
                result.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STATUS)));
                result.setExamTitle(cursor.getString(cursor.getColumnIndexOrThrow("exam_title")));

                resultList.add(result);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        if (resultList.isEmpty()) {
            Toast.makeText(this, "No results available", Toast.LENGTH_SHORT).show();
        }
    }
}
