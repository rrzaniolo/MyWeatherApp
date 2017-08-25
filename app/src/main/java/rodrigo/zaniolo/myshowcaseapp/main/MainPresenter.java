package rodrigo.zaniolo.myshowcaseapp.main;

import android.view.View;

public class MainPresenter implements MainInterface.Presenter {

    /* Variables. */
    private MainInterface.View myView;

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
