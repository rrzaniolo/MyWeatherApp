package rodrigo.zaniolo.myshowcaseapp.city_list;

import android.databinding.ObservableBoolean;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import rodrigo.zaniolo.myshowcaseapp.error.RequestErrorFragment;
import rodrigo.zaniolo.myshowcaseapp.managers.city.CityManager;
import rodrigo.zaniolo.myshowcaseapp.managers.request.RequestManager;
import rodrigo.zaniolo.myshowcaseapp.models.CityListModel;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

public class CityListPresenter implements CityListInterface.Presenter, RecyclerBindingAdapter.OnItemClickListener<CityListModel> {

    /* Variables. */
    private CityListInterface.View myView;
    private MyRecyclerViewConfiguration myRecyclerViewConfiguration;
    private RecyclerBindingAdapter<CityListModel> myRecyclerBindingAdapter;
    private ObservableBoolean running = new ObservableBoolean(false);
    private OpenWeatherListModel openWeatherListModel;

    /* Constructors. */
    public CityListPresenter(CityListInterface.View myView, OpenWeatherListModel openWeatherListModel) {
        this.myView = myView;
        this.openWeatherListModel = openWeatherListModel;

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
        return new RecyclerBindingAdapter<>(R.layout.content_city_list_item, BR.city, parseOpenWeatherListModel());
    }

    private CityListModel cityParser(String city, String country, float temp, Drawable icon){
        return new CityListModel(city, country, temp, icon);
    }

    private ArrayList<CityListModel> parseOpenWeatherListModel(){
        ArrayList<CityListModel> cityListModels = new ArrayList<>();

        if(openWeatherListModel != null){
            for (OpenWeatherModel openWeatherModel : openWeatherListModel.getList()) {
                cityListModels.add(cityParser(
                        openWeatherModel.getCityName(),
                        openWeatherModel.getOpenWeatherSys().getCountryCode(),
                        openWeatherModel.getWeatherMainInfo().getCurrentTemp(),
                        CityManager.getInstance().getIconByCode(myView.getContext(), openWeatherModel.getWeatherList().get(0).getId())
                ));
            }
            return cityListModels;
        }else{
            return getCityList();
        }
    }

    /* *Mocked Data* */
    private ArrayList<CityListModel> getCityList(){
        ArrayList<CityListModel> cityListModels = new ArrayList<>();

        LinkedHashMap<String, List<String>> cityHash = CityManager.getInstance().getCityMap();

        for (Map.Entry<String, List<String>> entry : cityHash.entrySet()) {
            for (String city : entry.getValue()) {
                cityListModels.add(cityParser(
                        city,
                        entry.getKey(),
                        32f,
                        ContextCompat.getDrawable(myView.getContext(), R.drawable.ic_day)
                ));
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
        myView.goToDetail(openWeatherListModel.getList().get(position));
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

                final String city = myView.getCityName();
                final String countryCode = myView.getCountryCode();

                if(requestManager.hasInternetConnection(myView.getContext())){
                    requestManager.getWeatherData(city, countryCode,
                            new Callback<OpenWeatherModel>() {
                                @Override
                                public void onResponse(@NonNull Call<OpenWeatherModel> call, @NonNull Response<OpenWeatherModel> response) {
                                    setRunning(false);

                                    if(response.isSuccessful()){
                                        myView.goToDetail(response.body());
                                    }else{
                                        RequestErrorFragment.newInstance(city, countryCode, false)
                                                .show(
                                                        myView.getContextActivity().getSupportFragmentManager(),
                                                        myView.getContextActivity().getLocalClassName()
                                                );
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<OpenWeatherModel> call, @NonNull Throwable t) {
                                    setRunning(false);

                                    RequestErrorFragment.newInstance(city, countryCode, false)
                                            .show(
                                                    myView.getContextActivity().getSupportFragmentManager(),
                                                    myView.getContextActivity().getLocalClassName()
                                            );
                                }
                            });
                }else{
                    setRunning(false);

                    RequestErrorFragment.newInstance(city, countryCode, true)
                            .show(
                                myView.getContextActivity().getSupportFragmentManager(),
                                myView.getContextActivity().getLocalClassName()
                            );
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
