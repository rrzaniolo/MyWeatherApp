package rodrigo.zaniolo.myshowcaseapp.splash;

import android.content.Context;

import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;

public interface SplashInterface {

    interface View{
        Context getContext();
        void goToMainWithParams(OpenWeatherListModel openWeatherListModel);
    }

    interface Presenter{
        void loadData();
    }
}
