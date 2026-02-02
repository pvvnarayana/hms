package com.example.onlineexam.student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineexam.R;

public class TakeExamActivity extends AppCompatActivity {
    private TextView tvQuestionNumber;
    private TextView tvQuestionText;
    private RadioGroup radioGroupOptions;
    private RadioButton rbOptionA;
    private RadioButton rbOptionB;
    private RadioButton rbOptionC;
    private RadioButton rbOptionD;
    private Button btnPrevious;
    private Button btnNext;
    private Button btnSubmit;
    private TextView tvTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_exam);

        initializeViews();
        loadQuestion();
    }

    private void initializeViews() {
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuestionText = findViewById(R.id.tvQuestionText);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        rbOptionA = findViewById(R.id.rbOptionA);
        rbOptionB = findViewById(R.id.rbOptionB);
        rbOptionC = findViewById(R.id.rbOptionC);
        rbOptionD = findViewById(R.id.rbOptionD);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvTimer = findViewById(R.id.tvTimer);
    }

    private void loadQuestion() {
        // Implementation for loading questions
    }
}
