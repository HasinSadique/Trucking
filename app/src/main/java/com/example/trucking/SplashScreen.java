package com.example.trucking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences=this.getSharedPreferences("Login",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        String Email=sharedPreferences.getString("Email", null);
        Boolean status = sharedPreferences.getBoolean("Login_Status",false);


        if(status!=true){
            //go to login page
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            //go to home page with user details
            Intent intent = new Intent(this,AppHomePageLayout.class);
            intent.putExtra("Email",Email);
            startActivity(intent);
        }
    }
}