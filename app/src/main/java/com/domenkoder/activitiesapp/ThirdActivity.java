package com.domenkoder.activitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    private int count = 0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        View header = findViewById(R.id.textView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            if (header != null) {
                header.setPadding(header.getPaddingLeft(), systemBars.top, header.getPaddingRight(), header.getPaddingBottom());
            }
            return insets;
        });

        count = getIntent().getIntExtra("count", 0);

        TextView textViewCount = findViewById(R.id.textViewCount);
        if (textViewCount != null) {
            textViewCount.setText(String.valueOf(count));
        }

        Button buttonGoToActivity1 = findViewById(R.id.button);
        if (buttonGoToActivity1 != null) {
            buttonGoToActivity1.setOnClickListener(v -> goToMainActivity());
        }

        Button buttonGoToActivity2 = findViewById(R.id.button2);
        if (buttonGoToActivity2 != null) {
            buttonGoToActivity2.setOnClickListener(v -> goToHelloActivity());
        }

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                if (e1 == null) return false;
                float diffX = e2.getX() - e1.getX();
                float diffY = e2.getY() - e1.getY();

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX < 0) {
                            goToMainActivity();
                        } else {
                            goToHelloActivity();
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            mainView.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.performClick();
                }
                return gestureDetector.onTouchEvent(event);
            });
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null && gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    private void goToHelloActivity() {
        Intent intent = new Intent(ThirdActivity.this, HelloActivity.class);
        intent.putExtra("count", count);
        startActivity(intent);
        finish();
    }
}