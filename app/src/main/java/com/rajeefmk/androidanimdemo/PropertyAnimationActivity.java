package com.rajeefmk.androidanimdemo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class PropertyAnimationActivity extends AppCompatActivity {

    ValueAnimator valueAnimator;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupBasicPropertyAnimatorsDemo();
        setupViewPropertyAnimatorsDemo();
    }

    private void setupBasicPropertyAnimatorsDemo() {
        Button forwardButton = findViewById(R.id.forward);
        Button reverseButton = findViewById(R.id.reverse);
        TextView animatedTextView = findViewById(R.id.textview);

        defineValueAnimator(animatedTextView);
        defineObjectAnimator(animatedTextView);

        forwardButton.setOnClickListener(v -> {
            if (!valueAnimator.isRunning())
                valueAnimator.start();
        });

        reverseButton.setOnClickListener(v -> {
            if (!objectAnimator.isRunning())
                objectAnimator.start();
        });
    }

    private void defineObjectAnimator(TextView animatedTextView) {
        objectAnimator = ObjectAnimator.ofFloat(animatedTextView, "translationX", 0f);
        objectAnimator.setDuration(1000);
    }

    private void defineValueAnimator(TextView animatedTextView) {
        valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            animatedTextView.setTranslationX(animatedValue);
        });
    }

    private void setupViewPropertyAnimatorsDemo() {
        TextView viewPropTextView = findViewById(R.id.view_prop_textview);
        Button vpForward = findViewById(R.id.view_prop_forward);
        Button vpReverse = findViewById(R.id.view_prop_reverse);

        vpForward.setOnClickListener(v -> viewPropTextView.animate().translationX(100f));
        vpReverse.setOnClickListener(v -> viewPropTextView.animate().translationX(0f));

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
