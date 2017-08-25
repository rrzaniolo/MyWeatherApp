package rodrigo.zaniolo.myshowcaseapp.city_list;

import android.databinding.ObservableBoolean;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.zaniolo.myshowcaseapp.BR;
import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.adapters.RecyclerBindingAdapter;
import rodrigo.zaniolo.myshowcaseapp.custom.MyRecyclerViewConfiguration;
import rodrigo.zaniolo.myshowcaseapp.managers.city.CityManager;
import rodrigo.zaniolo.myshowcaseapp.managers.request.RequestManager;
import rodrigo.zaniolo.myshowcaseapp.models.CityListModel;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

public class CityListPresenter implements CityListInterface.Presenter, RecyclerBindingAdapter.OnItemClickListener<CityListModel> {

    /* Variables. */
    private CityListInterface.View myView;
    private MyRecyclerViewConfiguration myRecyclerViewConfiguration;
    private RecyclerBindingAdapter<CityListModel> myRecyclerBindingAdapter;
    private ObservableBoolean running = new ObservableBoolean(false);

    /* Constructors. */
    public CityListPresenter(CityListInterface.View myView) {
        this.myView = myView;

        setRecycler();
    }


    /* Getters and Setter. */

    public ObservableBoolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running.set(running);
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

    private void onCitySelected(int position, CityListModel cityListModel){
        //TODO
    }

    /* Listeners. */
    @Override
    public void onItemClick(int position, View view, CityListModel cityListModel) {
        if(!getRunning().get()){
            onCitySelected(position, cityListModel);
        }
    }

    private View.OnClickListener onCheckWeatherLogic(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRunning(true);
                RequestManager requestManager = new RequestManager();

                if(requestManager.hasInternetConnection(myView.getContext())){
                    requestManager.getWeatherData(myView.getCityName(), myView.getCountryCode(),
                            new Callback<OpenWeatherModel>() {
                                @Override
                                public void onResponse(Call<OpenWeatherModel> call, Response<OpenWeatherModel> response) {
                                    if(response.isSuccessful()){
                                        //TODO
                                    }else{
                                        //TODO
                                    }
                                }

                                @Override
                                public void onFailure(Call<OpenWeatherModel> call, Throwable t) {
                                    //TODO
                                }
                            });
                }else{
                    //TODO
                }
            }
        };
    }

    /* Interface Methods. */
    @Override
    public MyRecyclerViewConfiguration getRecyclerConfiguration() {
        return myRecyclerViewConfiguration;
    }

    @Override
    public ObservableBoolean isRunning() {
        return getRunning();
    }

    @Override
    public View.OnClickListener onCheckWeather() {
        return onCheckWeatherLogic();
    }
}
