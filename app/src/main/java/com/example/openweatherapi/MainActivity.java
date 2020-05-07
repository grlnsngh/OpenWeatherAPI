package com.example.openweatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.tv);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=43.728402&lon=-79.607440&appid=91c0b02fe6caf490726de810d51c39bf&units=metric";
//String url = "https://www.google.com";
//        String url = "http://my-json-feed";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        textView.setText("Response: " + response.toString());

                        try {
//                            JSONObject main = response.getJSONObject("main");

//                            Double temprature = main.getDouble("temp");
//                            Double feelsLike = main.getDouble("feels_like");
//                            Double pressure = main.getDouble("pressure");
//                            textView.setText("Temp: " + temprature + "\nFeels like: " + feelsLike+ "\nPressure: "+pressure);
//                            JSONArray weather = response.getJSONArray("weather");
                            JSONArray list = response.getJSONArray("list");
                            double[] temp = new double[list.length()];

                            for(int i=0; i<list.length(); i++) {
                                JSONObject object = list.getJSONObject(i);
                                JSONObject main = object.getJSONObject("main");
                                Double temperature = main.getDouble("temp");
                                temp[i] = temperature;
                                textView.setText(textView.getText()+"\nTemp: "+temperature);
                                Log.d("<>", String.valueOf(temp[i]));
                            }



//                            for(int i=0; i<weather.length(); i++){
//                                JSONObject object = weather.getJSONObject(i);
//                                String description = object.getString("description");
//                                textView.setText(textView.getText()+"\nDescription: "+description );
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest);
    }
}
