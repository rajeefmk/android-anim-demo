package com.rajeefmk.androidanimdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class RevealHideActivity extends AppCompatActivity {

    private int mShortAnimationDuration;
    private TextView revealTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_reveal_hide);
        setupRevealHideWithCrossFade();
        setupCardFlip(savedInstanceState);
        setupCircularReveal();
    }

    private void setupCircularReveal() {
        Switch circularRevealSwitch = findViewById(R.id.circle_reveal_switch);
        circularRevealSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (isChecked) {
                        revealTextView();
                    } else {
                        hideTextView();
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hideTextView() {
        //Getting the center of cliping circle
        int cx = revealTextView.getWidth() / 2;
        int cy = revealTextView.getHeight() / 2;

        //Initial Radius of clipping circle;
        float initialRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(revealTextView, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                revealTextView.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealTextView() {
        //Getting the center of cliping circle
        int cx = revealTextView.getWidth() / 2;
        int cy = revealTextView.getHeight() / 2;

        //Initial Radius of clipping circle;
        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(revealTextView, cx, cy, 0, finalRadius);
        revealTextView.setVisibility(View.VISIBLE);
        anim.start();
    }

    private void setupCardFlip(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }

        Switch cardFlipSwitch = findViewById(R.id.card_flip_switch);
        cardFlipSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showBackFragment();
            } else {
                showFrontFragment();
            }
        });

    }

    private void showFrontFragment() {
        getSupportFragmentManager().popBackStack();
    }

    private void showBackFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out
                ).replace(R.id.container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
    }

    private void setupRevealHideWithCrossFade() {
        Switch toggleSwitch = findViewById(R.id.toggle_switch);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        revealTextView = findViewById(R.id.reveal_textview);
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        toggleSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
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

    public static class CardFrontFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_front_layout, container, false);
        }
    }

    public static class CardBackFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.card_back_layout, container, false);
        }
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
