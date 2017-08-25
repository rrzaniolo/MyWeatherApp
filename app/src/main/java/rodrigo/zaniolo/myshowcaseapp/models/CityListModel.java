package rodrigo.zaniolo.myshowcaseapp.models;

import android.graphics.drawable.Drawable;

import java.util.Locale;

import rodrigo.zaniolo.myshowcaseapp.utils.Constants;

@SuppressWarnings("unused")
public class CityListModel {

    /* Variables. */
    private String cityName;
    private String cityCountryCode;
    private float cityTemperature;
    private Drawable cityWeatherIcon;

    /* Constructor */
    public CityListModel(String cityName, String cityCountryCode, float cityTemperature, Drawable cityWeatherIcon) {
        this.cityName = cityName;
        this.cityCountryCode = cityCountryCode;
        this.cityTemperature = cityTemperature;
        this.cityWeatherIcon = cityWeatherIcon;
    }

    /* Getter and Setters. */
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCountryCode() {
        return cityCountryCode;
    }

    public void setCityCountryCode(String cityCountryCode) {
        this.cityCountryCode = cityCountryCode;
    }

    public String getCountryName(){
        return new Locale("", getCityCountryCode()).getDisplayCountry();
    }

    private float getCityTemperature() {
        return cityTemperature;
    }

    public void setCityTemperature(float cityTemperature) {
        this.cityTemperature = cityTemperature;
    }

    public Drawable getCityWeatherIcon() {
        return cityWeatherIcon;
    }

    public void setCityWeatherIcon(Drawable cityWeatherIcon) {
        this.cityWeatherIcon = cityWeatherIcon;
    }

    public String getFormattedCityTemperatureInCelsius(){

        return (String.format(Locale.getDefault(), "%.2f", getCityTemperature())) + " " + Constants.CELSIUS;
    }

    public String getFormattedCityTemperatureInFahrenheit(){

        return String.format(Locale.getDefault(), "%.2f", getCityTemperature()*1.8 + 32) + " " + Constants.FAHRENHEIT;
    }
}
