package com.blueman.ammusic.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blueman.ammusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private String user_BeatName;
    @BindView(R.id.user_name)
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        user_BeatName = getIntent().getStringExtra("user_name");

        user_name.setText("Welcome "+user_BeatName);

    }
}
