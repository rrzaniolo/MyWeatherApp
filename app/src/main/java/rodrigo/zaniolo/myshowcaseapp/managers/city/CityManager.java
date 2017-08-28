package rodrigo.zaniolo.myshowcaseapp.managers.city;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.utils.Constants;

public class CityManager {

    /* Variables. */
    private static CityManager instance = null;
    private LinkedHashMap<String, List<String>> cityMap;

    /* Constructors. */
    private CityManager() {
        cityMap = new LinkedHashMap<>();
        populateCityList();
    }

    /**
     * Getting Singleton Manager.
     * @return
     */
    public static CityManager getInstance(){
        if(instance == null){
            instance = new CityManager();
        }

        return instance;
    }

    /* Getter. */
    public LinkedHashMap<String, List<String>> getCityMap() {
        return cityMap;
    }

    /* City Helper Methods. */
    public Drawable getIconByCode(Context context, int code){

        Drawable icon;

        if(code < 300){
            icon = ContextCompat.getDrawable(context, R.drawable.ic_thunder);
        }else if(code < 600){
            icon = ContextCompat.getDrawable(context, R.drawable.ic_rainy);
        }else if(code < 700){
            icon = ContextCompat.getDrawable(context, R.drawable.ic_snowy);
        }else if(code < 800){
            icon = null;
        }else if(code == 800){
            icon = ContextCompat.getDrawable(context, R.drawable.ic_day);
        }else if (code < 900){
            icon = ContextCompat.getDrawable(context, R.drawable.ic_cloudy);
        }else{
            icon = null;
        }

        return icon;
    }

    public String getFormattedCityTemperatureInCelsius(float temperature){

        return (String.format(Locale.getDefault(), "%.2f", temperature)) + " " + Constants.CELSIUS;
    }

    public String getFormattedCityTemperatureInFahrenheit(float temperature){

        return String.format(Locale.getDefault(), "%.2f", temperature*1.8 + 32) + " " + Constants.FAHRENHEIT;
    }

    public String getCountryName(String countryCode){
        return new Locale("", countryCode).getDisplayCountry();
    }

    /* Private Methods. */
    private void populateCityList(){
        add("br", "São Paulo");
        add("br", "Porto Alegre");
        add("sw", "Stockholm");
        add("us", "New York");
    }

    private void add(String country, String city){
        if(!cityMap.containsKey(country)){
            cityMap.put(country, new ArrayList<String>());
        }

        if(!cityMap.get(country).contains(city)) {
            cityMap.get(country).add(city);
        }
    }
}
