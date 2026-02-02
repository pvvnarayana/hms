package com.example.onlineexam.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.onlineexam.MainActivity;
import com.example.onlineexam.R;
import com.example.onlineexam.utils.SessionManager;

public class StudentDashboardActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private CardView cardAvailableExams;
    private CardView cardMyResults;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        sessionManager = new SessionManager(this);

        tvWelcome = findViewById(R.id.tvWelcome);
        cardAvailableExams = findViewById(R.id.cardAvailableExams);
        cardMyResults = findViewById(R.id.cardMyResults);

        String fullName = sessionManager.getLoggedInUser().getFullName();
        tvWelcome.setText("Welcome, " + fullName);

        cardAvailableExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboardActivity.this, ExamListActivity.class);
                startActivity(intent);
            }
        });

        cardMyResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboardActivity.this, ViewResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            sessionManager.logout();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
