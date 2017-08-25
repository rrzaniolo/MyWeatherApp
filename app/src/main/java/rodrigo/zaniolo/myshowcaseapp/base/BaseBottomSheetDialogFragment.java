package rodrigo.zaniolo.myshowcaseapp.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import rodrigo.zaniolo.myshowcaseapp.R;

public class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getView() != null) {
            ((View) getView().getParent()).setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent));

            setupBottomSheetBehavior((View) getView().getParent());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

            setupOnShowListener();

        return bottomSheetDialog;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager.findFragmentByTag(tag) == null)
            try {
                super.show(manager, tag);
            } catch (IllegalStateException e) {
                manager.beginTransaction().add(this, tag).commitAllowingStateLoss();
            }
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    private void setupOnShowListener() {
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout frameLayout = (FrameLayout)((BottomSheetDialog) dialog).findViewById(android.support.design.R.id.design_bottom_sheet);

                if (frameLayout != null) {
                    setupBottomSheetBehavior(frameLayout);

                    bottomSheetBehavior.setSkipCollapsed(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }

    private void setupBottomSheetBehavior(View view) {
        bottomSheetBehavior = BottomSheetBehavior.from(view);

        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {
                        case BottomSheetBehavior.STATE_HIDDEN:
                            dismiss();
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            }

                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
            });
        }
    }
}
