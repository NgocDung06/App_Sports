package edu.itc.appsports_androidnangcao.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.itc.appsports_androidnangcao.R;

public class Accoun_Activity extends AppCompatActivity {
    AppCompatButton btnLogin, btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accoun);

        btnsignup = findViewById(R.id.btGetstart);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accoun_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.btLoginn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Accoun_Activity.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}