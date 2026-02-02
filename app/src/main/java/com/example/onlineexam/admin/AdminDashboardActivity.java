package com.example.onlineexam.admin;

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

public class AdminDashboardActivity extends AppCompatActivity {
    private TextView tvWelcome;
    private CardView cardManageExams;
    private CardView cardManageQuestions;
    private CardView cardViewResults;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        sessionManager = new SessionManager(this);

        tvWelcome = findViewById(R.id.tvWelcome);
        cardManageExams = findViewById(R.id.cardManageExams);
        cardManageQuestions = findViewById(R.id.cardManageQuestions);
        cardViewResults = findViewById(R.id.cardViewResults);

        String fullName = sessionManager.getLoggedInUser().getFullName();
        tvWelcome.setText("Welcome, " + fullName);

        cardManageExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ManageExamsActivity.class);
                startActivity(intent);
            }
        });

        cardManageQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ManageQuestionsActivity.class);
                startActivity(intent);
            }
        });

        cardViewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboardActivity.this, ViewStudentResultsActivity.class);
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
