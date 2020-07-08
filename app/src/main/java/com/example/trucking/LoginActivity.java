package com.example.trucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    String Email, Pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.EditText_Username_Email);
        pass=findViewById(R.id.EditText_password);
        mAuth = FirebaseAuth.getInstance();

//        key = getIntent().getExtras().getString("key");
    }

    public void register(View view) {
        Intent intent=  new Intent(this, RegistrationLayout.class);
        startActivity(intent);
    }

    public void Login(View view) {
//        Intent intent=  new Intent(this, AppHomePageLayout.class);
//        startActivity(intent);
        Email=email.getText().toString();
        Pass=pass.getText().toString();
        mAuth.signInWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //save info
                            SharedPreferences sharedPreferences=getSharedPreferences("Login", 0);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("Email",Email );
                            editor.putBoolean("Login_Status",true);
                            editor.commit();
                            //go-to HomeUI
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,Email);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed--->>"+task.getException(),Toast.LENGTH_LONG).show();
//                            updateUI(null);
                        }

                        // ...
                    }

                    private void updateUI(FirebaseUser user, String email) {
                        Intent intent=new Intent(LoginActivity.this,AppHomePageLayout.class);
//                        intent.putExtra("Email",email);
                        startActivity(intent);

                    }
                });
    }
}