package rodrigo.zaniolo.myshowcaseapp.city_detail;

import android.graphics.drawable.Drawable;

import rodrigo.zaniolo.myshowcaseapp.managers.city.CityManager;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

public class CityDetailPresenter implements CityDetailInterface.Presenter{

    /* Variables. */
    private CityDetailInterface.View myView;
    private OpenWeatherModel openWeatherModel;

    /* Constructors. */
    public CityDetailPresenter() { }

    public CityDetailPresenter(CityDetailInterface.View myView, OpenWeatherModel openWeatherModel) {
        this.myView = myView;
        this.openWeatherModel = openWeatherModel;
    }

    /* Getters and Setters. */
    public OpenWeatherModel getOpenWeatherModel() {
        return openWeatherModel;
    }

    public void setOpenWeatherModel(OpenWeatherModel openWeatherModel) {
        this.openWeatherModel = openWeatherModel;
    }

    /* Interface Methods. */
    @Override
    public Drawable getIcon() {
        return CityManager.getInstance().getIconByCode(myView.getContext(), openWeatherModel.getWeatherList().get(0).getId());
    }

    @Override
    public String getCity() {
        return getOpenWeatherModel().getCityName();
    }

    @Override
    public String getCountry() {
        return CityManager.getInstance().getCountryName(getOpenWeatherModel().getOpenWeatherSys().getCountryCode());
    }

    @Override
    public String getCurrent() {
        return CityManager.getInstance().getFormattedCityTemperatureInCelsius(getOpenWeatherModel().getWeatherMainInfo().getCurrentTemp());
    }

    @Override
    public String getMax() {
        return CityManager.getInstance().getFormattedCityTemperatureInCelsius(getOpenWeatherModel().getWeatherMainInfo().getMaxTemp());
    }

    @Override
    public String getMin() {
        return CityManager.getInstance().getFormattedCityTemperatureInCelsius(getOpenWeatherModel().getWeatherMainInfo().getMinTemp());
    }

    @Override
    public String getHumidity() {
        return getOpenWeatherModel().getWeatherMainInfo().getHumidity() + "mm";
    }

    @Override
    public String getPressure() {
        return getOpenWeatherModel().getWeatherMainInfo().getAirPressure() + " Pa";
    }

    @Override
    public String getWindSpeed() {
        return getOpenWeatherModel().getWind().getSpeed() + " m/s";
    }

    @Override
    public int getRotation() {
        return (int)(getOpenWeatherModel().getWind().getDeg() -60);
    }
}
