package com.example.onlineexam.admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineexam.R;
import com.example.onlineexam.models.Question;
import com.example.onlineexam.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ManageQuestionsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewQuestions;
    private DatabaseHelper dbHelper;
    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_questions);

        recyclerViewQuestions = findViewById(R.id.recyclerViewQuestions);
        recyclerViewQuestions.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        loadQuestions();
    }

    private void loadQuestions() {
        questionList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_QUESTIONS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
                question.setExamId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EXAM_ID)));
                question.setQuestionText(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_QUESTION_TEXT)));
                question.setOptionA(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPTION_A)));
                question.setOptionB(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPTION_B)));
                question.setOptionC(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPTION_C)));
                question.setOptionD(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPTION_D)));
                question.setCorrectAnswer(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CORRECT_ANSWER)));
                question.setMarks(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MARKS)));

                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }
}
