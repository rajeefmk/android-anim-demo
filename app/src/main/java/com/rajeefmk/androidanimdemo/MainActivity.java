package com.rajeefmk.androidanimdemo;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupAlertDialog();
        setupCrossFadeTextView();
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
        Switch textToggleSwitch = findViewById(R.id.toggle_switch);
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
                        toggleText.setText("Check text");
                    else
                        toggleText.setText("UnChecked text");
                }
            });
            toggleText.startAnimation(alphaAnimation);

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
