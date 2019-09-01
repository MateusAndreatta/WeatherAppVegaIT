package br.com.mateusandreatta.weatherapp_vegait.model;

import java.util.Date;

public class WeatherInfo {

    private String city;
    private String country;
    private String description;
    private Double temperature;
    private Double temperatureMax;
    private Double temperatureMin;
    private String icon;
    private Date data;

    public WeatherInfo(String city, String country, String description, Double temperature, Double temperatureMax, Double temperatureMin, String icon, Date data) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.temperature = temperature;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.icon = icon;
        this.data = data;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public String getIcon() {
        return icon;
    }

    public Date getData() {
        return data;
    }
}
