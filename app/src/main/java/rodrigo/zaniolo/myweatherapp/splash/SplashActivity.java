package rodrigo.zaniolo.myweatherapp.splash;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myweatherapp.R;
import rodrigo.zaniolo.myweatherapp.base.BaseActivity;
import rodrigo.zaniolo.myweatherapp.databinding.ActivitySplashBinding;
import rodrigo.zaniolo.myweatherapp.main.MainActivity;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class SplashActivity extends BaseActivity implements SplashInterface.View {

    /* Variables. */
    private SplashInterface.Presenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myPresenter = new SplashPresenter(SplashActivity.this);
        setUpDataBinding();
        fetchWeatherData();
    }

    /* private Methods. */
    private void setUpDataBinding(){
        ActivitySplashBinding activitySplashBinding = DataBindingUtil.setContentView(SplashActivity.this, R.layout.activity_splash);
        activitySplashBinding.setPresenter(myPresenter);
    }

    private void fetchWeatherData(){
        myPresenter.loadData();
    }

    /* Interface Methods. */
    @Override
    public Context getContext() {
        return SplashActivity.this;
    }

    @Override
    public void goToMain() {
        goToActivity(true, MainActivity.class);
    }
}
