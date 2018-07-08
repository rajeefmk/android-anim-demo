package com.rajeefmk.androidanimdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class PropertyAnimationActivity extends AppCompatActivity {

    Button forwardButton;
    Button reverseButton;
    TextView animatedTextView;
    ValueAnimator valueAnimator;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        animatedTextView = findViewById(R.id.textview);
        forwardButton = findViewById(R.id.forward);
        reverseButton = findViewById(R.id.reverse);

        defineValueAnimator();
        defineObjectAnimator();

        forwardButton.setOnClickListener(v -> {
            if (!valueAnimator.isRunning())
                valueAnimator.start();
        });

        reverseButton.setOnClickListener(v -> {
            if (!objectAnimator.isRunning())
                objectAnimator.start();
        });


    }

    private void defineObjectAnimator() {
        objectAnimator = ObjectAnimator.ofFloat(animatedTextView, "translationX", 0f);
        objectAnimator.setDuration(1000);
    }

    private void defineValueAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            animatedTextView.setTranslationX(animatedValue);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
