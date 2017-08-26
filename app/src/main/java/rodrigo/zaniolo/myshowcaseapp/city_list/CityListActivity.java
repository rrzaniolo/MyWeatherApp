package rodrigo.zaniolo.myshowcaseapp.city_list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.city_detail.CityDetailActivity;
import rodrigo.zaniolo.myshowcaseapp.databinding.ActivityCityListBinding;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;

public class CityListActivity extends BaseActivity implements CityListInterface.View {

    /* Variables. */
    private CityListInterface.Presenter myPresenter;
    ActivityCityListBinding activityCityListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPresenter = new CityListPresenter(CityListActivity.this, (OpenWeatherListModel) getIntent().getParcelableExtra("bundle"));
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
        activityCityListBinding = DataBindingUtil.setContentView(CityListActivity.this, R.layout.activity_city_list);
        activityCityListBinding.setPresenter(myPresenter);
    }

    /* Interface Methods. */
    @Override
    public Context getContext() {
        return CityListActivity.this;
    }

    @Override
    public BaseActivity getContextActivity() {
        return CityListActivity.this;
    }

    @Override
    public String getCityName() {
        return activityCityListBinding.myEditTextCityListCity.getText().toString();
    }

    @Override
    public String getCountryCode() {
        return activityCityListBinding.myEditTextCityListCountryCode.getText().toString();
    }

    @Override
    public void goToDetail(Parcelable params) {
        goToActivityWithParams(false, params, CityDetailActivity.class);
    }
}
