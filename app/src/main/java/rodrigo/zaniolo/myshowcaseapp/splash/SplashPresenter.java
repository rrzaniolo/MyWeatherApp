package rodrigo.zaniolo.myshowcaseapp.splash;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.zaniolo.myshowcaseapp.managers.request.RequestManager;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;

public class SplashPresenter implements SplashInterface.Presenter {

    /* Variables. */
    SplashInterface.View myView;
    RequestManager myManager;

    /* Constructor. */
    public SplashPresenter(SplashInterface.View myView) {
        this.myView = myView;
    }
    /* Public Methods. */

    /**
     * Open Weather App.
     * @return a ClickListener for Data Binding.
     */

    @Override
    public void loadData() {
        myManager = new RequestManager();

        if(myManager.hasInternetConnection(myView.getContext())){
            myManager.getInitialCitiesWeatherData(new Callback<OpenWeatherListModel>() {
                @Override
                public void onResponse(Call<OpenWeatherListModel> call, Response<OpenWeatherListModel> response) {
                    if (response.isSuccessful()) {
                        myView.goToMainWithParams(response.body());
                    }
                    else {
                        // TODO: 25/08/17 error
                    }
                }

                @Override
                public void onFailure(Call<OpenWeatherListModel> call, Throwable t) {
                    // TODO: 25/08/17 error
                }
            });
        }
        else{
            Toast.makeText(myView.getContext(), "No Internet Available =/", Toast.LENGTH_SHORT).show();
        }
    }
}
