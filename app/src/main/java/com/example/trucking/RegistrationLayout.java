package com.example.trucking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationLayout extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText fullName,email,mobile,password,confirmPass;
    Button register;

    String Fullname,Email,Mobile,Pass,Confirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_layout);

        mAuth = FirebaseAuth.getInstance();

        fullName = findViewById(R.id.EditText_FullName);
        email = findViewById(R.id.EditText_Email);
        mobile = findViewById(R.id.EditText_mobile);
        password = findViewById(R.id.EditText_pass);
        confirmPass = findViewById(R.id.EditText_ConfirmPass);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser!=null){
            //Homepage
        }else{
            //Login Page
        }
    }

    public void goLoginPage(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
//        Intent intent= new Intent(this, AppHomePageLayout.class);
//        startActivity(intent);
        Fullname = fullName.getText().toString();
        Email = email.getText().toString();
        Mobile = email.getText().toString();
        Pass = password.getText().toString();
        Confirmpass =confirmPass.getText().toString();
        RegisterUser(Fullname,Email,Mobile,Pass,Confirmpass);
    }

    private void RegisterUser(String fullname, String email, String mobile, String pass, String confirmpass) {
        if(pass.equals(confirmpass)){
            User user= new User(fullname,email,mobile,pass);

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Registered.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(this, "Passwords mismatch", Toast.LENGTH_SHORT).show();
        }
    }
}