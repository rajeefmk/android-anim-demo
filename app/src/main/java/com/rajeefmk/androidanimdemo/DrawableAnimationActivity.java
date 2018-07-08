package com.rajeefmk.androidanimdemo;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class DrawableAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_animation);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupAnimationListDrawable();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupAnimatedVectorDrawable();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupAnimatedVectorDrawable() {
        ImageView animatedVectorImageView= findViewById(R.id.vector_animation_imageview);
        animatedVectorImageView.setBackgroundResource(R.drawable.sample_animated_vector_drawable);
        AnimatedVectorDrawable animatedVectorDrawable = (AnimatedVectorDrawable) animatedVectorImageView.getBackground();
        animatedVectorImageView.setOnClickListener(v -> animatedVectorDrawable.start());

    }

    private void setupAnimationListDrawable() {
        ImageView animationListImageView = findViewById(R.id.animation_list_imageview);
        animationListImageView.setBackgroundResource(R.drawable.sample_animation_list_drawable);
        AnimationDrawable animationDrawable = (AnimationDrawable) animationListImageView.getBackground();

        animationListImageView.setOnClickListener(v -> animationDrawable.start());
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
