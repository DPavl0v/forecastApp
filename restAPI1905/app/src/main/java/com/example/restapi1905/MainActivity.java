package com.example.restapi1905;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.ErrorListener;

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