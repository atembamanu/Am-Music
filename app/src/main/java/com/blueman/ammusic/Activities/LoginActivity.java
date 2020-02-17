package com.blueman.ammusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blueman.ammusic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_username)
    EditText userEmail;
    @BindView(R.id.login_password)
    EditText userPassword;
    @BindView(R.id.createAcc)
    TextView createAccount;
    @BindView(R.id.login)
    Button loginUser;

    String email, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_login);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        loginUser.setOnClickListener(v -> {
            validateUserInputAndSignUserIn();
        });
        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
    private void validateUserInputAndSignUserIn() {
        email = userEmail.getText().toString();
        password =userPassword.getText().toString();

       if (TextUtils.isEmpty(email)) {
           userEmail.setError("Email is required");
       }
       else if(TextUtils.isEmpty(password)) {
           userPassword.setError("Password is required");
       }else {
           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Log.d(TAG, "SIGNIN SUCCESS");
                               Toast.makeText(LoginActivity.this, "Sucess..", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(LoginActivity.this, UserSettingsActivity.class);
                               startActivity(intent);
                           } else {
                               Log.w(TAG, "signInWithEmail:failure", task.getException());
                               Toast.makeText(LoginActivity.this, "Authentication failed. Try again",
                                       Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
       }
    }




}
