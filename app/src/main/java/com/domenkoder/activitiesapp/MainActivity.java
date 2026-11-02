package com.domenkoder.activitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView textViewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
        }

        View header = findViewById(R.id.textView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            if (header != null) {
                header.setPadding(header.getPaddingLeft(), systemBars.top, header.getPaddingRight(), header.getPaddingBottom());
            }
            return insets;
        });

        textViewCount = findViewById(R.id.textViewCount);
        Button countButton = findViewById(R.id.button);

        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        textViewCount.setText(String.valueOf(count));

        if (countButton != null) {
            countButton.setOnClickListener(v -> {
                count++;
                textViewCount.setText(String.valueOf(count));
            });
        }

        if (button2 != null) {
            button2.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                intent.putExtra("count", count);
                startActivity(intent);
            });
        }

        if (button3 != null) {
            button3.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("count", count);
                startActivity(intent);
            });
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }
}