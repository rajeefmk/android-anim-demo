package com.rajeefmk.androidanimdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class RevealHideActivity extends AppCompatActivity {

    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_hide);

        Switch toggleSwitch = findViewById(R.id.toggle_switch);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView revealTextView = findViewById(R.id.reveal_textview);
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        toggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                revealTextView.setVisibility(View.VISIBLE);
                revealTextView.setAlpha(0f);
                revealTextView.animate()
                        .alpha(1f)
                        .setDuration(mShortAnimationDuration)
                        .setListener(null);

                progressBar
                        .animate()
                        .alpha(0f)
                        .setDuration(mShortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            } else {
                revealTextView.animate()
                        .alpha(0f)
                        .setDuration(mShortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                revealTextView.setVisibility(View.GONE);
                            }
                        });

                progressBar
                        .animate()
                        .alpha(1f)
                        .setDuration(mShortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });


    }
}
