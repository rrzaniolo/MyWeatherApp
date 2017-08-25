package rodrigo.zaniolo.myweatherapp.city_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myweatherapp.R;
import rodrigo.zaniolo.myweatherapp.base.BaseActivity;
import rodrigo.zaniolo.myweatherapp.databinding.ActivityCityListBinding;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class CityListActivity extends BaseActivity implements CityListInterface.View {

    /* Variables. */
    private CityListInterface.Presenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPresenter = new CityListPresenter(CityListActivity.this);
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
        ActivityCityListBinding activityCityListBinding = DataBindingUtil.setContentView(CityListActivity.this, R.layout.activity_city_list);
        activityCityListBinding.setPresenter(myPresenter);
    }

    /* Interface Methods. */

    @Override
    public Context getContext() {
        return CityListActivity.this;
    }
}
