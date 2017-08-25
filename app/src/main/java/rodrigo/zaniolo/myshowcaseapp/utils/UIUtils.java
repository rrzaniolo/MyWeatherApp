package rodrigo.zaniolo.myshowcaseapp.utils;

import rodrigo.zaniolo.myshowcaseapp.base.BaseBottomSheetDialogFragment;

public class UIUtils {

    public static boolean shouldDismissDialog(BaseBottomSheetDialogFragment dialogFragment) {
        return dialogFragment != null && dialogFragment.getDialog() != null && dialogFragment.getDialog().isShowing();
    }
}
