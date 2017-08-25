package rodrigo.zaniolo.myweatherapp.main;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public interface MainInterface {

    interface View{
        void goToWeather();
    }

    interface Presenter{
        android.view.View.OnClickListener onWeather();
    }
}
