package com.blueman.ammusic.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blueman.ammusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicPreferenceActivity extends AppCompatActivity{



    @BindView(R.id.mcontinue) TextView mContinue;
    @BindView(R.id.beatName) EditText  nBeat;
    private  String user_mBeatName;

    private final String LOG_TAG = MusicPreferenceActivity.class.getSimpleName();
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_preference);
        ButterKnife.bind(this);


        goToHome();

        nBeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateUserBeatName();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void validateUserBeatName() {
        user_mBeatName = nBeat.getText().toString();
        if (!TextUtils.isEmpty(user_mBeatName)) {
            if(user_mBeatName.length() > 1){
                mContinue.setVisibility(View.VISIBLE);
            }


        }else{

            Toast.makeText(this, "Name is good for Networking", Toast.LENGTH_SHORT).show();
        }


    }

    private void goToHome() {
        validateUserBeatName();
        mContinue.setOnClickListener(v -> {
            Intent intent = new Intent(MusicPreferenceActivity.this, HomeActivity.class);
            intent.putExtra("user_name", user_mBeatName);
            startActivity(intent);

        });
    }

    }
