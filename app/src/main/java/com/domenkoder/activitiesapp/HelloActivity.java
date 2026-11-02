package com.domenkoder.activitiesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hello);

        View header = findViewById(R.id.textView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            if (header != null) {
                header.setPadding(header.getPaddingLeft(), systemBars.top, header.getPaddingRight(), header.getPaddingBottom());
            }
            return insets;
        });

        int count = getIntent().getIntExtra("count", 0);

        TextView textViewCount = findViewById(R.id.textViewCount);
        if (textViewCount != null) {
            textViewCount.setText(String.valueOf(count));
        }

        Button buttonGoToActivity1 = findViewById(R.id.button);
        if (buttonGoToActivity1 != null) {
            buttonGoToActivity1.setOnClickListener(v -> {
                Intent intent = new Intent(HelloActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }
    }
}