package com.blueman.ammusic.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_username)
    EditText userEmail;
    @BindView(R.id.register_password)
    EditText userPassword;
    @BindView(R.id.loginAcc)
    TextView loginAccount;
    @BindView(R.id.signup)
    Button signUpUser;

    String email, password;
    private static final String TAG = "RegisterActivity";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        signUpUser.setOnClickListener(v -> {
            validateUserInputAndSignUpUser();
        });
        loginAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private void validateUserInputAndSignUpUser() {

        email = userEmail.getText().toString();
        password =userPassword.getText().toString();

        if (TextUtils.isEmpty(email))
            userEmail.setError("Email is required");
        if(TextUtils.isEmpty(password))
            userPassword.setError("Password is required");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, UserSettingsActivity.class);
                            startActivity(intent);
                        } else {
                            if (mAuth.getCurrentUser() != null){
                                Toast.makeText(RegisterActivity.this, "You are registered with "+mAuth.getCurrentUser().getEmail() +"Logging in..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, UserSettingsActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });

    }
}
