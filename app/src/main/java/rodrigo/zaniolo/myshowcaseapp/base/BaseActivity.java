package rodrigo.zaniolo.myshowcaseapp.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

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
}
