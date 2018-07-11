package com.rajeefmk.androidanimdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class LayoutChangeTransitionActivity extends AppCompatActivity {

    Scene mAScene;
    Scene mAnotherScene;
    FrameLayout sceneRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_change_transition);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setupScenes();
            Button actionButton = findViewById(R.id.action_button);
            Transition transition = new Fade();
            actionButton.setOnClickListener(v -> {
                TransitionManager.go(mAnotherScene, transition);
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setupScenes() {
        sceneRoot = findViewById(R.id.scene_root);
        mAScene = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene, this);
        mAnotherScene = Scene.getSceneForLayout(sceneRoot, R.layout.another_scene, this);

    }
}
