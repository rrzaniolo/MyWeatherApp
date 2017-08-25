package rodrigo.zaniolo.myweatherapp.models;

import java.util.Locale;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class CityListModel {

    /* Variables. */
    private String cityName;
    private String cityCountryCode;

    /* Constructor */
    public CityListModel(String cityName, String cityCountryCode) {
        this.cityName = cityName;
        this.cityCountryCode = cityCountryCode;
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
}
