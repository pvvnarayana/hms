package com.example.onlineexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineexam.admin.AdminLoginActivity;
import com.example.onlineexam.student.StudentLoginActivity;
import com.example.onlineexam.utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    private Button btnStudentPortal;
    private Button btnAdminPortal;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        // Check if user is already logged in
        if (sessionManager.isLoggedIn()) {
            redirectToAppropriateActivity();
            return;
        }

        btnStudentPortal = findViewById(R.id.btnStudentPortal);
        btnAdminPortal = findViewById(R.id.btnAdminPortal);

        btnStudentPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });

        btnAdminPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void redirectToAppropriateActivity() {
        Intent intent;
        if (sessionManager.isStudent()) {
            intent = new Intent(this, com.example.onlineexam.student.StudentDashboardActivity.class);
        } else {
            intent = new Intent(this, com.example.onlineexam.admin.AdminDashboardActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
