package rodrigo.zaniolo.myweatherapp.splash;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.zaniolo.myweatherapp.managers.request.RequestManager;
import rodrigo.zaniolo.myweatherapp.models.OpenWeatherModel;

/**
 * Created by rrzaniolo on 24/08/17.
 */

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
            myManager.getWeatherData("Porto Alegre", "BR", new Callback<OpenWeatherModel>() {
                @Override
                public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                    response.code();
                }

                @Override
                public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                    t.getLocalizedMessage();
                }
            });
        }
        else{
            Toast.makeText(myView.getContext(), "No Internet Available =/", Toast.LENGTH_SHORT).show();
        }
    }
}
