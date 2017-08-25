package rodrigo.zaniolo.myweatherapp.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myweatherapp.R;
import rodrigo.zaniolo.myweatherapp.base.BaseActivity;
import rodrigo.zaniolo.myweatherapp.city_list.CityListActivity;
import rodrigo.zaniolo.myweatherapp.databinding.ActivityMainBinding;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class MainActivity extends BaseActivity implements MainInterface.View{

    /* Variables. */
    private MainInterface.Presenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPresenter = new MainPresenter(MainActivity.this);
        setUpDataBinding();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /* private Methods. */
    private void setUpDataBinding(){
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        activityMainBinding.setPresenter(myPresenter);
    }

    /* Interface Methods. */
    @Override
    public void goToWeather() {
        goToActivity(false, CityListActivity.class);
    }
}
