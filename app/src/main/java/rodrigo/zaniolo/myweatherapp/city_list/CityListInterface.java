package rodrigo.zaniolo.myweatherapp.city_list;

import android.content.Context;

import rodrigo.zaniolo.myweatherapp.custom.MyRecyclerViewConfiguration;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public interface CityListInterface {

    interface View{
        Context getContext();
    }

    interface Presenter{
        MyRecyclerViewConfiguration getRecyclerConfiguration();
    }
}
