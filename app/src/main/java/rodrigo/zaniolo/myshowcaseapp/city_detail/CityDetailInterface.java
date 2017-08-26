package rodrigo.zaniolo.myshowcaseapp.city_detail;

import android.content.Context;
import android.graphics.drawable.Drawable;

import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;

public interface CityDetailInterface {

    interface View{
        Context getContext();
        BaseActivity getContextActivity();
    }

    interface Presenter{
        Drawable getIcon();
        String getCity();
        String getCountry();
        String getCurrent();
        String getMax();
        String getMin();
        String getHumidity();
        String getPressure();
        String getWindSpeed();
        int getRotation();
    }
}

