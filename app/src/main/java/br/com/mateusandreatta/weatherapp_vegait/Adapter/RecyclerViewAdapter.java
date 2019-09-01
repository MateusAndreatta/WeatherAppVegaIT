package br.com.mateusandreatta.weatherapp_vegait.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.mateusandreatta.weatherapp_vegait.R;
import br.com.mateusandreatta.weatherapp_vegait.model.WeatherInfo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WeatherViewHolder> {


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_weather, viewGroup, false);
        WeatherViewHolder wvh = new WeatherViewHolder(v);
        return wvh;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder ViewHolder, int i) {
        ViewHolder.cityName.setText(weatherInfoList.get(i).getCity() + ", " + weatherInfoList.get(i).getCountry());
        ViewHolder.description.setText(weatherInfoList.get(i).getData().toString().substring(0,10) + ", " + capitalizeStr(weatherInfoList.get(i).getDescription()));
        ViewHolder.temperature.setText(formatTemperature(weatherInfoList.get(i).getTemperature()));
        ViewHolder.temperatureMax.setText("Max: " + formatTemperature(weatherInfoList.get(i).getTemperatureMax()) + " °C");
        ViewHolder.temperatureMin.setText("Min: " + formatTemperature(weatherInfoList.get(i).getTemperatureMin()) + " °C");
        setImageBitmap(weatherInfoList.get(i).getIcon(), ViewHolder.icon);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cityName,description,temperature,temperatureMax,temperatureMin;
        ImageView icon;

        WeatherViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            cityName = (TextView) itemView.findViewById(R.id.txtCityName);
            description = (TextView) itemView.findViewById(R.id.txtDescription);
            temperature = (TextView) itemView.findViewById(R.id.txtTemperature);
            temperatureMax = (TextView) itemView.findViewById(R.id.txtTemperatureMax);
            temperatureMin = (TextView) itemView.findViewById(R.id.txtTemperatureMin);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }

    }

    private List<WeatherInfo> weatherInfoList;

    public RecyclerViewAdapter(List<WeatherInfo> weatherInfos){
        weatherInfoList = new ArrayList<>();
        this.weatherInfoList = weatherInfos;
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.size();
    }

    private String formatTemperature(Double temp){
        int i = temp.intValue();
        return String.valueOf(i);
    }

    private String capitalizeStr(String str){
        String upper_case_line = "";
        Scanner lineScan = new Scanner(str);
        while(lineScan.hasNext()) {
            String word = lineScan.next();
            upper_case_line += Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
        }
        return upper_case_line;
    }

    private void setImageBitmap(String url, ImageView imageView){
        Picasso.get().load(url).into(imageView);
    }


}
