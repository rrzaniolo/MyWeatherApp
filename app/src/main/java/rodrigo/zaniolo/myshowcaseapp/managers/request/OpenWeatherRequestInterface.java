package rodrigo.zaniolo.myshowcaseapp.managers.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public interface OpenWeatherRequestInterface {

    @GET("weather")
    Call<OpenWeatherModel> getCurrentWeather(@Query("q") String params, @Query("appid") String appId);
}