package rodrigo.zaniolo.myshowcaseapp.managers.request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rodrigo.zaniolo.myshowcaseapp.BuildConfig;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;
import rodrigo.zaniolo.myshowcaseapp.utils.Constants;
import rodrigo.zaniolo.myshowcaseapp.utils.UrlConstants;

public class RequestManager {

    /* Variables. */
    private static volatile Retrofit retrofit = null;
    private static volatile OkHttpClient okHttpClient = null;
    private String WEATHER_API_KEY = "669f1ec6bf9d3d5a7e4caf628a81b59d";

    /* Constructor. */
    public RequestManager() { }

    /* Private Methods. */
    private static OkHttpClient getOkHttpClient(){
        if(okHttpClient == null) {
            synchronized (RequestManager.class) {
                if (okHttpClient == null) {
                    if (BuildConfig.DEBUG) {
                        okHttpClient = new OkHttpClient.Builder()
                                .addNetworkInterceptor(new LoggingInterceptor())
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .build();
                    } else {
                        okHttpClient = new OkHttpClient.Builder()
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .build();
                    }
                }
            }
        }
        return okHttpClient;
    }

    private Retrofit getRetrofit(){
        if(retrofit == null) {
            synchronized (RequestManager.class){
                if(retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(UrlConstants.OPEN_WEATHER_BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                }
            }
        }

        return retrofit;
    }

    /* Public Method. */

    /**
     * Check for internet availability.
     * @param context - Context.
     * @return if the user has or no internet.
     */
    public boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * Method to get the current weather for a given city in a given country. The request is returned
     * in the given callback.
     * @param city - City to search for.
     * @param country - Country of the city.
     * @param callback - Request Callback.
     */
    public void getWeatherData(String city, String country, Callback<OpenWeatherModel> callback){

        OpenWeatherRequestInterface openWeatherRequestInterface = getRetrofit().create(OpenWeatherRequestInterface.class);


        String params = city + "," + country;
        Call<OpenWeatherModel> openWeatherModel = openWeatherRequestInterface.getCurrentWeather(params, WEATHER_API_KEY);

        openWeatherModel.enqueue(callback);
    }

    /**
     * Method ti get the current weather the five initial cities defined
     * @param callback - Request Callback.
     */
    public void getInitialCitiesWeatherData(Callback<OpenWeatherListModel> callback){

        OpenWeatherRequestInterface openWeatherRequestInterface = getRetrofit().create(OpenWeatherRequestInterface.class);

        Call<OpenWeatherListModel> openWeatherModel = openWeatherRequestInterface.getInitialCitiesWeather(Constants.INITIAL_CITIES_IDS, WEATHER_API_KEY);

        openWeatherModel.enqueue(callback);
    }

    /* Inner Class. */
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.d("OkHttp request", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            okhttp3.Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }
}
