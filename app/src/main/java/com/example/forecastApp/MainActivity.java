package com.example.forecastApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    EditText editText;
    String url;
    String message;

    private void init() {
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString("message");
            textView.setText(message);
        }

        button.setOnClickListener(v -> {
            url = editText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
            intent.putExtra("city", url);
            startActivity(intent);


        });


    }
}