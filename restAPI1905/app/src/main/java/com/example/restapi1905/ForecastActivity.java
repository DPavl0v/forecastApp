package com.example.restapi1905;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ForecastActivity extends AppCompatActivity {

    String getCityIdUrl = "https://www.metaweather.com/api/location/search/?query=";
    String getForecastUrl = "https://www.metaweather.com/api/location/";
    ListView listView;
    String city;
    Button button;
    String idCity;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<ForecastService> arrayForecast = new ArrayList<>(6);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayForecast);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.btnBack);
        textView = findViewById(R.id.txtCity);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                city = null;
            } else {
                city = extras.getString("city");
            }
        } else {
            city = (String) savedInstanceState.getSerializable("city");
        }


        RequestQueue requestQueue = Volley.newRequestQueue(ForecastActivity.this);

        JsonArrayRequest getIdCityRequest = new JsonArrayRequest(Request.Method.GET, getCityIdUrl + city, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    if (response.toString().equals("[]")) {
                        String message = "Wrong city!";
                        Intent intent = new Intent(ForecastActivity.this, MainActivity.class);
                        intent.putExtra("message", message);
                        startActivity(intent);
                    }
                    JSONObject jsonObject = response.getJSONObject(0);
                    idCity = jsonObject.getString("woeid");
                    city = jsonObject.getString("title");

                    JsonObjectRequest getForecastRequest = new JsonObjectRequest(Request.Method.GET, getForecastUrl + idCity, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                JSONArray array = response.getJSONArray("consolidated_weather");

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject day = array.getJSONObject(i);
                                    arrayForecast.add(new ForecastService(day.getString("weather_state_name"),
                                            day.getString("the_temp"),
                                            day.getString("wind_speed"),
                                            day.getString("applicable_date")));

                                }

                                listView.setAdapter(arrayAdapter);
                                textView.append(city + " for a next 6 days");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, error -> Toast.makeText(ForecastActivity.this, "No respond from server", Toast.LENGTH_SHORT).show());
                    requestQueue.add(getForecastRequest);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> Toast.makeText(ForecastActivity.this, "No respond from server", Toast.LENGTH_SHORT).show());

        requestQueue.add(getIdCityRequest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}