package rodrigo.zaniolo.myweatherapp.city_list;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.zaniolo.myweatherapp.BR;
import rodrigo.zaniolo.myweatherapp.R;
import rodrigo.zaniolo.myweatherapp.adapters.RecyclerBindingAdapter;
import rodrigo.zaniolo.myweatherapp.custom.MyRecyclerViewConfiguration;
import rodrigo.zaniolo.myweatherapp.managers.city.CityManager;
import rodrigo.zaniolo.myweatherapp.managers.request.RequestManager;
import rodrigo.zaniolo.myweatherapp.models.CityListModel;
import rodrigo.zaniolo.myweatherapp.models.OpenWeatherModel;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class CityListPresenter implements CityListInterface.Presenter, RecyclerBindingAdapter.OnItemClickListener<CityListModel> {

    /* Variables. */
    private CityListInterface.View myView;
    private MyRecyclerViewConfiguration myRecyclerViewConfiguration;
    private RecyclerBindingAdapter<CityListModel> myRecyclerBindingAdapter;

    /* Constructors. */

    public CityListPresenter(CityListInterface.View myView) {
        this.myView = myView;

        setRecycler();
    }

    /* Private Methods. */
    private RecyclerBindingAdapter<CityListModel> getAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.content_city_list_item, BR.city, getCityList());
    }

    private CityListModel cityParser(String city, String country){
        return new CityListModel(city, country, 22f, null);
    }
    private ArrayList<CityListModel> getCityList(){
        ArrayList<CityListModel> cityListModels = new ArrayList<>();

        LinkedHashMap<String, List<String>> cityHash = CityManager.getInstance().getCityMap();

        for (Map.Entry<String, List<String>> entry : cityHash.entrySet()) {
            for (String city : entry.getValue()) {
                cityListModels.add(cityParser(city, entry.getKey()));
            }
        }

        return cityListModels;
    }

    private void setRecycler() {
        this.myRecyclerBindingAdapter = getAdapter();
        this.myRecyclerBindingAdapter.setOnItemClickListener(CityListPresenter.this);

        this.myRecyclerViewConfiguration = new MyRecyclerViewConfiguration();
        this.myRecyclerViewConfiguration.setLayoutManager(new LinearLayoutManager(myView.getContext(), LinearLayoutManager.VERTICAL, false));
        this.myRecyclerViewConfiguration.setItemAnimator(new DefaultItemAnimator());
        this.myRecyclerViewConfiguration.setAdapter(myRecyclerBindingAdapter);
    }

    private void onCitySelected(CityListModel cityListModel){
        RequestManager requestManager = new RequestManager();

        if(requestManager.hasInternetConnection(myView.getContext())){
            requestManager.getWeatherData(cityListModel.getCityName(), cityListModel.getCityCountryCode(), new Callback<OpenWeatherModel>() {
                @Override
                public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(myView.getContext(), response.body().getCityName(), Toast.LENGTH_LONG).show();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("weatherData", response.body());
                    }else{
                        //TODO
                    }
                }

                @Override
                public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                    Toast.makeText(myView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            //TODO
        }
    }

    /* Listeners. */
    @Override
    public void onItemClick(int position, View view, CityListModel cityListModel) {
        onCitySelected(cityListModel);
    }

    /* Interface Methods. */
    @Override
    public MyRecyclerViewConfiguration getRecyclerConfiguration() {
        return myRecyclerViewConfiguration;
    }
}
