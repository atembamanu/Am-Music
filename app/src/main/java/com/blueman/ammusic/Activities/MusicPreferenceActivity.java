package com.blueman.ammusic.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blueman.ammusic.R;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.card_mGospel) TextView mGospel;
    @BindView(R.id.card_mReggae) TextView mReggae;
    @BindView(R.id.card_mRumba) TextView mRumba;
    @BindView(R.id.card_mRnB) TextView mRnB;

    @BindView(R.id.card_mTechno) TextView mTechno;
    @BindView(R.id.card_mSoul) TextView mSoul;
    @BindView(R.id.card_mCountry) TextView mCountry;
    @BindView(R.id.card_mHiphop) TextView mHipHop;

    @BindView(R.id.card_mJazz) TextView mJazz;
    @BindView(R.id.card_mRock) TextView mRock;
    @BindView(R.id.card_mInstrumental) TextView mInstrumental;
    @BindView(R.id.card_mPop) TextView mPop;


    @BindView(R.id.mcontinue) TextView mContinue;

    boolean clicked = false;
    private final String LOG_TAG = MusicPreferenceActivity.class.getSimpleName();

    //private TextView[] musicPreferences = {mGospel, mReggae, mRumba, mRnB, mTechno, mSoul, mCountry, mHipHop, mJazz, mRock, mInstrumental,mPop };
   // private int[] ids = {R.id.card_mGospel, R.id.card_mReggae, R.id.card_mRumba, R.id.card_mRnB, R.id.card_mTechno,R.id.card_mSoul,R.id.card_mCountry,R.id.card_mHiphop,R.id.card_mJazz,R.id.card_mRock,R.id.card_mInstrumental,R.id.card_mPop};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_preference);
        ButterKnife.bind(this);
        mGospel.setOnClickListener(this);
        mReggae.setOnClickListener(this);
        mRumba.setOnClickListener(this);
        mRnB.setOnClickListener(this);

        mTechno.setOnClickListener(this);
        mSoul.setOnClickListener(this);
        mCountry.setOnClickListener(this);
        mHipHop.setOnClickListener(this);


        mJazz.setOnClickListener(this);
        mRock.setOnClickListener(this);
        mInstrumental.setOnClickListener(this);
        mPop.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.card_mGospel:
                    mGospel.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mGospel.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;

                case R.id.card_mReggae:
                    mReggae.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mReggae.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mRumba:
                    mRumba.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRumba.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mRnB:
                    mRnB.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRnB.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mTechno:
                    mTechno.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mTechno.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;

                case R.id.card_mSoul:
                    mSoul.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mSoul.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mCountry:
                    mCountry.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mCountry.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mHiphop:
                    mHipHop.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mHipHop.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mJazz:
                    mJazz.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mJazz.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;

                case R.id.card_mRock:
                    mRock.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mRock.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mInstrumental:
                    mInstrumental.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mInstrumental.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                case R.id.card_mPop:
                    mPop.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    mPop.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mContinue.setVisibility(View.VISIBLE);
                    clicked = true;
                    break;
                    default:

                }
            }
    }
