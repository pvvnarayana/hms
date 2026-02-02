package com.example.onlineexam.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineexam.R;
import com.example.onlineexam.common.AuthManager;
import com.example.onlineexam.models.User;
import com.example.onlineexam.utils.Constants;
import com.example.onlineexam.utils.SessionManager;

public class StudentLoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private AuthManager authManager;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        authManager = new AuthManager(this);
        sessionManager = new SessionManager(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            etUsername.setError(getString(R.string.error_empty_username));
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError(getString(R.string.error_empty_password));
            return;
        }

        User user = authManager.login(username, password);

        if (user != null && Constants.ROLE_STUDENT.equals(user.getRole())) {
            sessionManager.createSession(user);
            Toast.makeText(this, R.string.success_login, Toast.LENGTH_SHORT).show();
            
            Intent intent = new Intent(StudentLoginActivity.this, StudentDashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, R.string.error_invalid_credentials, Toast.LENGTH_SHORT).show();
        }
    }
}
