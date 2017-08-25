package rodrigo.zaniolo.myshowcaseapp.splash;

import android.content.Context;

public interface SplashInterface {

    interface View{
        Context getContext();
        void goToMain();
    }

    interface Presenter{
        void loadData();
    }
}
