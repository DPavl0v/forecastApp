package com.example.restapi1905;

public class ForecastService {
    private final String weather_state_name;
    private final String the_temp;
    private final String wind_speed;
    private final String applicable_date;


    public ForecastService(String weather_state_name, String the_temp, String wind_speed, String applicable_date) {
        this.weather_state_name = weather_state_name;
        this.the_temp = String.valueOf(Math.round(Float.parseFloat(the_temp)));
        this.wind_speed = String.valueOf(Math.round(Float.parseFloat(wind_speed)));
        this.applicable_date = applicable_date;
    }

    @Override
    public String toString() {
        return applicable_date + '\n' +
                "State : " + weather_state_name + '\n' +
                "Temperature : " + the_temp + "°C" + '\n' +
                "Wind speed : " + wind_speed + "mph";
    }
}
