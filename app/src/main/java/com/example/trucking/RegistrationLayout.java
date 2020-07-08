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

import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;

public class RegistrationLayout extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText fullName,email,mobile,password,confirmPass;
    Button register;

    DatabaseReference  databaseReference;

    String Fullname,Email,Mobile,Pass,Confirmpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_layout);

        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

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
//        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser, String key) {
        if (currentUser!=null){
            //LoginPage
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            intent.putExtra("key",key);
            startActivity(intent);
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

    private void RegisterUser(final String fullname, final String email, final String mobile, final String pass, String confirmpass) {
        if(pass.equals(confirmpass)){

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                //create user in real-time database
                                User newUser= new User(fullname,email,mobile,pass);
                                String key=databaseReference.push().getKey();
                                databaseReference.child(key).setValue(newUser);
//                                Toast.makeText(getApplicationContext(),"User Registered: ",Toast.LENGTH_LONG).show();

                                //Go to login ui
//                                Log.d("MyActivity", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Registered.>>"+user, Toast.LENGTH_SHORT).show();
                                updateUI(user,key);

                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w("My Activity failure", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.:>>"+task.getException(),
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null,null);
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(this, "Passwords mismatch", Toast.LENGTH_SHORT).show();
        }
    }
}