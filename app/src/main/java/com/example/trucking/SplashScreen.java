package com.example.trucking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.gms.common.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {

//    SharedPreferences sharedPreferences=this.getSharedPreferences("Login",0);
    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        gifImageView=(GifImageView)findViewById(R.id.GIFImageView);
        try{
            InputStream inputStream=getAssets().open("giflogo.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }catch (IOException ex){

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                SplashScreen.this.finish();


            }


        },3000);

////        String Email=sharedPreferences.getString("Email", null);
////        Boolean status = sharedPreferences.getBoolean("Login_Status",false);
//
//
//        if(status!=true){
//            //go to login page
//            Intent intent = new Intent(this,LoginActivity.class);
//            startActivity(intent);
//        }else{
//            //go to home page with user details
//            Intent intent = new Intent(this,AppHomePageLayout.class);
//            intent.putExtra("Email",Email);
//            startActivity(intent);
//        }
    }
}