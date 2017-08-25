package rodrigo.zaniolo.myweatherapp.splash;

import android.content.Context;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public interface SplashInterface {

    interface View{
        Context getContext();
        void goToMain();
    }

    interface Presenter{
        void loadData();
    }
}
