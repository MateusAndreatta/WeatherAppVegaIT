package br.com.mateusandreatta.weatherapp_vegait;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.mateusandreatta.weatherapp_vegait.Adapter.RecyclerViewAdapter;
import br.com.mateusandreatta.weatherapp_vegait.model.WeatherInfo;

public class MainActivity extends AppCompatActivity {

    private final String URL_ICON = "http://openweathermap.org/img/wn/";// Must be in .png
    private final String URL_API = "http://api.openweathermap.org/";
    private final String API_KEY = "782b76c610171ce0faaa54f77c93296a";
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        List<WeatherInfo> weatherInfos = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestData(requestQueue,"6322752",weatherInfos);
        requestData(requestQueue,"6173331",weatherInfos);
        requestData(requestQueue,"3451190",weatherInfos);

    }

    private void setupRecycler(List<WeatherInfo> weatherInfos) {
        // Set layoutManager to be a list.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(weatherInfos);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void requestData(RequestQueue requestQueue, String idCity, final List<WeatherInfo> weatherInfoList){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_API + "/data/2.5/weather?id=" + idCity + "&appid=" + API_KEY + "&units=metric",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Get the response from API, and create a WeatherInfo object
                        try {
                            JSONArray jsonArrayWeather = response.getJSONArray("weather");
                            JSONObject jsonObjMain = response.getJSONObject("main");
                            JSONObject jsonObjSys = response.getJSONObject("sys");
                            String cityName = response.getString("name");
                            String country = jsonObjSys.getString("country");
                            Date data = new Date(response.getLong("dt"));
                            String description = "", icon = "";

                            for (int i = 0; i < jsonArrayWeather.length(); i++) {
                                JSONObject jsonObject = jsonArrayWeather.getJSONObject(i);
                                description = jsonObject.getString("description");
                                icon = jsonObject.getString("icon");
                            }

                            Double temp    = jsonObjMain.getDouble("temp");
                            Double tempMax = jsonObjMain.getDouble("temp_max");
                            Double tempMin = jsonObjMain.getDouble("temp_min");

                            WeatherInfo weatherInfo  = new WeatherInfo(cityName, country, description, temp, tempMax, tempMin, URL_ICON + icon + ".png", data);
                            weatherInfoList.add(weatherInfo);
                            setupRecycler(weatherInfoList);
                            recyclerViewAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {


                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

}
