package rodrigo.zaniolo.myshowcaseapp.city_detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.databinding.ActivityCityDetailBinding;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;

public class CityDetailActivity extends BaseActivity implements CityDetailInterface.View{

    /* Variables. */
    private CityDetailInterface.Presenter myPresenter;
    private ActivityCityDetailBinding activityCityDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPresenter = new CityDetailPresenter(
                CityDetailActivity.this,
                (OpenWeatherModel)getIntent().getParcelableExtra("bundle")
        );
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
        activityCityDetailBinding = DataBindingUtil.setContentView(CityDetailActivity.this, R.layout.activity_city_detail);
        activityCityDetailBinding.setPresenter(myPresenter);
    }

    /* Interface Methods. */
    @Override
    public Context getContext() {
        return CityDetailActivity.this;
    }

    @Override
    public BaseActivity getContextActivity() {
        return CityDetailActivity.this;
    }

}
