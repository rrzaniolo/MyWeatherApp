package rodrigo.zaniolo.myshowcaseapp.utils;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import rodrigo.zaniolo.myshowcaseapp.custom.MyRecyclerViewConfiguration;

public class BinderAdapterUtils {

    @BindingAdapter("font")
    public static void setFont(TextView textView, String fontName){
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), fontName));
    }

    @BindingAdapter({"setUpRecyclerViewConfiguration"})
    public static void configureRecyclerView(RecyclerView recyclerView, MyRecyclerViewConfiguration myRecyclerViewConfiguration) {
        recyclerView.setLayoutManager(myRecyclerViewConfiguration.getLayoutManager());
        recyclerView.setItemAnimator(myRecyclerViewConfiguration.getItemAnimator());
        recyclerView.setAdapter(myRecyclerViewConfiguration.getAdapter());
    }
}
