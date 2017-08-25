package rodrigo.zaniolo.myshowcaseapp.models;

import android.graphics.drawable.Drawable;

import java.util.Locale;

import rodrigo.zaniolo.myshowcaseapp.utils.Constants;

/**
 * Created by rrzaniolo on 24/08/17.
 */

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

    public String getFormatedCityTemperatureInCelsius(){

        return ((getCityTemperature() - 32) / 1.8) + Constants.CELSIUS;
    }

    public String getFormatedCityTemperatureInFahrenheit(){

        return getCityTemperature() + Constants.FAHRENHEIT;
    }
}
