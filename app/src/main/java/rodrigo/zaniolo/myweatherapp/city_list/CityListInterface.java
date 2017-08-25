package rodrigo.zaniolo.myweatherapp.city_list;

import android.content.Context;
import android.databinding.ObservableBoolean;

import rodrigo.zaniolo.myweatherapp.custom.MyRecyclerViewConfiguration;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public interface CityListInterface {

    interface View{
        Context getContext();
        String getCityName();
        String getCountryCode();
    }

    interface Presenter{
        MyRecyclerViewConfiguration getRecyclerConfiguration();
        android.view.View.OnClickListener onCheckWeather();
        ObservableBoolean isRunning();
    }
}
