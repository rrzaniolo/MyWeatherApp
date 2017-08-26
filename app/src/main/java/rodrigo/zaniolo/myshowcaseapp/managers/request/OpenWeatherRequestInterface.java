package rodrigo.zaniolo.myshowcaseapp.managers.request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

public interface OpenWeatherRequestInterface {

    @GET("weather?units=metric")
    Call<OpenWeatherModel> getCurrentWeather(@Query("q") String params, @Query("appid") String appId);

    @GET("group?units=metric")
    Call<OpenWeatherListModel> getInitialCitiesWeather(@Query("id") String params, @Query("appid") String appId);
}
