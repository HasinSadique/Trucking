package com.example.trucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void register(View view) {
        Intent intent=  new Intent(this, RegistrationLayout.class);
        startActivity(intent);
    }

    public void Login(View view) {
        Intent intent=  new Intent(this, AppHomePageLayout.class);
        startActivity(intent);
    }
}