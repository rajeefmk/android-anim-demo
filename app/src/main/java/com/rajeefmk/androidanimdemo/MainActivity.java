package com.rajeefmk.androidanimdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAlertDialog();
        setupBasicAlphaAnim();
        setupCrossFadeTextView();
        setupCrossFadeTwoTextView();

        Button customViewButton = findViewById(R.id.custom_view);
        customViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
            }
        });

        Button propertyAnimationButton = findViewById(R.id.property_anim_button);
        propertyAnimationButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PropertyAnimationActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                startActivity(intent);
            }
        });
        Button drawableAnimaton = findViewById(R.id.drawable_animation);
        drawableAnimaton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DrawableAnimationActivity.class));
        });
        Button revealHideAnimation = findViewById(R.id.reveal_hide_button);
        revealHideAnimation.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RevealHideActivity.class));
        });
        Button inputTouchButton = findViewById(R.id.input_touch_button);
        inputTouchButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InputTouchActivity.class));
        });

        Button zoomImageButton = findViewById(R.id.zoom_image);
        zoomImageButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ZoomImageActivity.class));

        });

        Button layoutChangeTransitionButton = findViewById(R.id.layout_change_transition);
        layoutChangeTransitionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LayoutChangeTransitionActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            } else {
                startActivity(intent);
            }
        });
    }


    private void setupBasicAlphaAnim() {
        Switch textToggleSwitch = findViewById(R.id.toggle_switch_basic);
        TextView toggleText = findViewById(R.id.toggle_textview);

        textToggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(200);
            alphaAnimation.setRepeatCount(1);
            alphaAnimation.setRepeatMode(Animation.REVERSE);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (isChecked)
                        toggleText.setText("Check Text");
                    else
                        toggleText.setText("Unchecked Text");
                }
            });
            toggleText.startAnimation(alphaAnimation);

        });

    }

    private void setupAlertDialog() {
        Button showAlertButton = findViewById(R.id.show_alert_button);
        showAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("My Dialog");

        builder.setMessage("Check out the transition!");

        dialog = builder.create();

        if (dialog.getWindow() != null)
            dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.show();

    }

    private void setupCrossFadeTextView() {
        Switch textToggleSwitch = findViewById(R.id.toggle_switch_two);
        TextSwitcher toggleTextSwitcher = findViewById(R.id.toggle_text_switcher);

        toggleTextSwitcher.setFactory(() -> new TextView(MainActivity.this));

        Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        inAnim.setDuration(200);
        outAnim.setDuration(200);

        toggleTextSwitcher.setInAnimation(inAnim);
        toggleTextSwitcher.setOutAnimation(outAnim);

        textToggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                toggleTextSwitcher.setText("Text 1");
            else
                toggleTextSwitcher.setText("Text 2");
        });

    }

    private void setupCrossFadeTwoTextView() {
        Switch textToggleSwitch = findViewById(R.id.toggle_switch_three);
        TextSwitcher toggleTextSwitcher = findViewById(R.id.cross_fade_two_switcher);

        toggleTextSwitcher.setFactory(() -> new TextView(MainActivity.this));

        Animation inAnim = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation outAnim = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        inAnim.setDuration(200);
        outAnim.setDuration(200);

        toggleTextSwitcher.setInAnimation(inAnim);
        toggleTextSwitcher.setOutAnimation(outAnim);

        textToggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                toggleTextSwitcher.setText("Slide In");
            else
                toggleTextSwitcher.setText("Slide Out");
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
