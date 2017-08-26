package rodrigo.zaniolo.myshowcaseapp.splash;

import android.content.Context;

import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;

public interface SplashInterface {

    interface View{
        Context getContext();
        void goToListWithParams(OpenWeatherListModel openWeatherListModel);
    }

    interface Presenter{
        void loadData();
    }
}
