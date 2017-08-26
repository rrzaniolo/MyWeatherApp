package rodrigo.zaniolo.myshowcaseapp.city_list;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.os.Parcelable;

import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.custom.MyRecyclerViewConfiguration;

public interface CityListInterface {

    interface View{
        Context getContext();
        BaseActivity getContextActivity();
        String getCityName();
        String getCountryCode();
        void goToDetail(Parcelable params);
    }

    interface Presenter{
        MyRecyclerViewConfiguration getRecyclerConfiguration();
        android.view.View.OnClickListener onCheckWeather();
        ObservableBoolean isRunning();
    }
}
