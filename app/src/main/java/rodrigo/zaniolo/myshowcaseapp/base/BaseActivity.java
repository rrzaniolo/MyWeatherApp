package rodrigo.zaniolo.myshowcaseapp.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import rodrigo.zaniolo.myshowcaseapp.models.OpenWeatherListModel;

public class BaseActivity extends AppCompatActivity{

    /* Private Methods. */
    private void onFinish(boolean hasToFinish) {
        if (hasToFinish)
            finishAfterTransition();
    }

    /* Public Methods. */

    /**
     *
     * @param hasToFinish - If the activity needs to be finished.
     * @param targetActivity - Activity to Open.
     */
    public void goToActivity(boolean hasToFinish, Class targetActivity) {
        startActivity(new Intent(this, targetActivity));
        onFinish(hasToFinish);
    }

    /**
     *
     * @param hasToFinish - If the activity needs to be finished.
     * @param openWeatherListModel - Extras to set on activity
     * @param targetActivity - Activity to Open.
     */
    public void goToActivityWithParams(boolean hasToFinish, OpenWeatherListModel openWeatherListModel, Class targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        intent.putExtra("bundle", openWeatherListModel);
        startActivity(intent);
        onFinish(hasToFinish);
    }
}
