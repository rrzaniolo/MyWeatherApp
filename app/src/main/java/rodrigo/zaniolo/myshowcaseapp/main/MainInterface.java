package rodrigo.zaniolo.myshowcaseapp.main;

public interface MainInterface {

    interface View{
        void goToWeather();
    }

    interface Presenter{
        android.view.View.OnClickListener onWeather();
    }
}
