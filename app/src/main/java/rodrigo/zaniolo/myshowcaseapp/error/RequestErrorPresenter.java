package rodrigo.zaniolo.myshowcaseapp.error;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.city_detail.CityDetailActivity;
import rodrigo.zaniolo.myshowcaseapp.managers.request.RequestManager;
import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherModel;
import rodrigo.zaniolo.myshowcaseapp.utils.UIUtils;

@SuppressWarnings("unused")
class RequestErrorPresenter implements RequestErrorInterface.Presenter{

    /* Variables. */
    private RequestErrorInterface.View myView = null;
    private ObservableBoolean running = new ObservableBoolean(false);
    private Boolean noConnection;
    private String city;
    private String countryCode;

    /* Constructors. */
    RequestErrorPresenter(RequestErrorInterface.View myView, Boolean noConnection, String city, String countryCode) {
        this.myView = myView;
        this.noConnection = noConnection;
        this.city = city;
        this.countryCode = countryCode;
    }

    /* Getters and Setters. */
    private ObservableBoolean getRunning() {
        return running;
    }

    private void setRunning(boolean running) {
        this.running.set(running);
    }

    private Boolean getNoConnection() {
        return noConnection;
    }

    public void setNoConnection(Boolean noConnection) {
        this.noConnection = noConnection;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /* Private Methods. */
    private View.OnClickListener onTryAgain(){
        return createCallBackLogic();
    }

    private View.OnClickListener createCallBackLogic(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRunning(true);

                RequestManager requestManager = new RequestManager();

                if(requestManager.hasInternetConnection(myView.getViewContext())){
                    setNoConnection(false);
                    requestManager.getWeatherData(getCity(), getCountryCode(),
                            new Callback<OpenWeatherModel>() {
                                @Override
                                public void onResponse(@NonNull Call<OpenWeatherModel> call, @NonNull Response<OpenWeatherModel> response) {
                                    if(response.isSuccessful()){
                                        setRunning(false);
                                        checkAndDismiss();
                                        myView.goToActivityWithParams(response.body(), CityDetailActivity.class);
                                    }else{
                                        callbackError();
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<OpenWeatherModel> call, @NonNull Throwable t) {
                                    callbackError();
                                }
                            });

                }else{
                    setNoConnection(true);
                    callbackError();
                }
            }
        };
    }

    private void callbackError(){
        setRunning(false);
        myView.showErrorTitle(getErrorTitle());
        myView.showErrorMessage(getErrorMessage());
    }

    private void checkAndDismiss(){
        if(UIUtils.shouldDismissDialog(myView.getDialogView())){
            myView.getDialogView().dismiss();
        }
    }

    private View.OnClickListener onDismissErrorDialog(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndDismiss();
            }
        };
    }

    /* Interface Methods. */
    @Override
    public View.OnClickListener dismissErrorDialog() {
        return onDismissErrorDialog();
    }

    @Override
    public String getErrorTitle() {
        return myView.getViewContext().getString(R.string.request_error_tittle);
    }

    @Override
    public String getErrorMessage() {
        return getNoConnection() ? myView.getViewContext().getString(R.string.request_error_no_connection)
                : myView.getViewContext().getString(R.string.request_error_generic_error);
    }

    @Override
    public ObservableBoolean isRunning() {
        return getRunning();
    }

    @Override
    public View.OnClickListener tryAgain() {
        return onTryAgain();
    }
}
