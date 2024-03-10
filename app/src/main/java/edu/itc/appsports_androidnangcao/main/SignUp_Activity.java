package edu.itc.appsports_androidnangcao.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.itc.appsports_androidnangcao.R;

public class SignUp_Activity extends AppCompatActivity {
    AppCompatButton btnSigup;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSigup = findViewById(R.id.btSignup);
        btnSigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this,Login_Activity.class);
                startActivity(intent);
            }
        });
        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}