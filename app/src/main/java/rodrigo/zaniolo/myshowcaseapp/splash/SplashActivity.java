package rodrigo.zaniolo.myshowcaseapp.splash;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.databinding.ActivitySplashBinding;
import rodrigo.zaniolo.myshowcaseapp.main.MainActivity;

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
