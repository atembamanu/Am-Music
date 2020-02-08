package com.blueman.ammusic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.blueman.ammusic.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;

    }
    //Arrays
    private int[] slide_images = {
            R.mipmap.beat,
            R.mipmap.voice,
            R.mipmap.read_lyrics

    };
    private String[] slide_headings = {
            "",
            "",
            ""


    };
    private String[] slide_descs_1 = {
            "PLAY MUSIC",
            "CONTROL PLAYLIST",
            "SOMA LYRICS"


    };
    private String[] slide_descs_2 = {
            "ON THE",
            "USING YOUR",
            "MUSIC IKI"


    };
    private String[] slide_descs_3 = {
            "GO",
            "VOICE",
            "BEAT"


    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.image_view);
        TextView slideHeadings = view.findViewById(R.id.heading);
        TextView slideDescription_1 = view.findViewById(R.id.text_1);
        TextView slideDescription_2 = view.findViewById(R.id.text_2);
        TextView slideDescription_3 = view.findViewById(R.id.text_3);


        slideImageView.setImageResource(slide_images[position]);
        slideHeadings.setText(slide_headings[position]);
        slideDescription_1.setText(slide_descs_1[position]);
        slideDescription_2.setText(slide_descs_2[position]);
        slideDescription_3.setText(slide_descs_3[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);

    }
}
