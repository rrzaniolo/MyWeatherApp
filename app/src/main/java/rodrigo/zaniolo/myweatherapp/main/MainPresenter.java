package rodrigo.zaniolo.myweatherapp.main;

import android.view.View;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class MainPresenter implements MainInterface.Presenter {

    /* Variables. */
    MainInterface.View myView;

    /* Constructor. */
    public MainPresenter(MainInterface.View myView) {
        this.myView = myView;
    }
    /* Public Methods. */

    /**
     * Open Weather App.
     * @return a ClickListener for Data Binding.
     */
    @Override
    public View.OnClickListener onWeather(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.goToWeather();
            }
        };
    }
}
