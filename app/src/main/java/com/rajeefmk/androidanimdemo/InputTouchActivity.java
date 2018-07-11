package com.rajeefmk.androidanimdemo;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.rajeefmk.androidanimdemo.utils.OnSwipeTouchListener;

public class InputTouchActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String DEBUG_TAG = InputTouchActivity.class.getSimpleName();

    private GestureDetectorCompat gestureDetectorCompat;
    private VelocityTracker mVelocityTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_touch);
        gestureDetectorCompat = new GestureDetectorCompat(this, this);
        gestureDetectorCompat.setOnDoubleTapListener(this);
        setupDragButton();
        setupDropZone();

    }

    private void setupDropZone() {
        FrameLayout dropZoneLayout = findViewById(R.id.drop_zone);
        dropZoneLayout.setOnDragListener((v, event) -> {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundColor(Color.RED);
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    dropZoneLayout.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundColor(Color.TRANSPARENT);
                    break;
                default:
                    break;

            }
            return true;
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupDragButton() {
        Button testButton = findViewById(R.id.button);
        testButton.setOnTouchListener(new OnSwipeTouchListener(InputTouchActivity.this) {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (super.onTouch(view, motionEvent)) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                                view);
                        view.startDrag(data, shadowBuilder, view, 0);
                        view.setVisibility(View.INVISIBLE);
                    }
                    return super.onTouch(view, motionEvent);
                } else {
                    return false;
                }
            }

            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                Log.d(DEBUG_TAG, "OnSwipeBottom");
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                Log.d(DEBUG_TAG, "OnSwipeTop");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Log.d(DEBUG_TAG, "OnSwipeLeft");
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Log.d(DEBUG_TAG, "OnSwipeRight");
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // handleVelocity(event);
        return super.onTouchEvent(event);
    }

    private void handleVelocity(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(1000);
                Log.d("Velo", "X velocity: " +
                        mVelocityTracker.getXVelocity(pointerId));
                Log.d("Velo", "Y velocity: " +
                        mVelocityTracker.getYVelocity(pointerId));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mVelocityTracker.clear();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVelocityTracker != null)
            mVelocityTracker.recycle();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }
}
