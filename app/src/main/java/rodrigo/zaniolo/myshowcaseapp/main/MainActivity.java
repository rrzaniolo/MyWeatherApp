package rodrigo.zaniolo.myshowcaseapp.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.city_list.CityListActivity;
import rodrigo.zaniolo.myshowcaseapp.databinding.ActivityMainBinding;

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
