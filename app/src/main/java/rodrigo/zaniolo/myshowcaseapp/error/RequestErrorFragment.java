package rodrigo.zaniolo.myshowcaseapp.error;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rodrigo.zaniolo.myshowcaseapp.R;
import rodrigo.zaniolo.myshowcaseapp.base.BaseActivity;
import rodrigo.zaniolo.myshowcaseapp.base.BaseBottomSheetDialogFragment;
import rodrigo.zaniolo.myshowcaseapp.databinding.FragmentRequestErrorBinding;

public class RequestErrorFragment extends BaseBottomSheetDialogFragment implements RequestErrorInterface.View{

    /* Variables. */
    private RequestErrorInterface.Presenter myPresenter = null;
    private FragmentRequestErrorBinding fragmentRequestErrorBinding = null;
    private String city;
    private String countryCode;
    private Boolean hasConnection;

    /* Constructor. */
    public static RequestErrorFragment newInstance(String city, String countryCode, Boolean hasConnection) {

        RequestErrorFragment requestErrorFragment = new RequestErrorFragment();
        requestErrorFragment.city = city;
        requestErrorFragment.countryCode = countryCode;
        requestErrorFragment.hasConnection = hasConnection;

        return requestErrorFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(myPresenter == null){
            myPresenter = new RequestErrorPresenter(RequestErrorFragment.this, hasConnection, city, countryCode);
        }

        fragmentRequestErrorBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_request_error, container, false);
        fragmentRequestErrorBinding.setPresenter(myPresenter);


        return fragmentRequestErrorBinding.getRoot();
    }

    /* Interface Methods. */
    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public BaseBottomSheetDialogFragment getDialogView() {
        return RequestErrorFragment.this;
    }

    @Override
    public void showErrorTitle(String errorTitle) {
        fragmentRequestErrorBinding.myTextViewRequestErrorTitle.setText(errorTitle);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        fragmentRequestErrorBinding.myTextViewRequestErrorMessage.setText(errorMessage);
    }

    @Override
    public void goToActivityWithParams(Parcelable params, Class target) {
        ((BaseActivity)getActivity()).goToActivityWithParams(false, params, target);
    }
}
