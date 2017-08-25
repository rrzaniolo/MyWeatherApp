package rodrigo.zaniolo.myshowcaseapp.error;

import android.content.Context;
import android.databinding.ObservableBoolean;

import rodrigo.zaniolo.myshowcaseapp.base.BaseBottomSheetDialogFragment;

public interface RequestErrorInterface {

    interface View{
        Context getViewContext();
        BaseBottomSheetDialogFragment getDialogView();
        void showErrorTitle(String errorTitle);
        void showErrorMessage(String errorMessage);
    }

    interface Presenter{

        void dismissErrorDialog();
        String getErrorTitle();
        String getErrorMessage();
        ObservableBoolean isRunning();
        android.view.View.OnClickListener tryAgain();
    }
}
