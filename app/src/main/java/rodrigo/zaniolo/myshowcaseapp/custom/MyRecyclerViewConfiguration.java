package rodrigo.zaniolo.myshowcaseapp.custom;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import rodrigo.zaniolo.myshowcaseapp.BR;

/**
 * Created by rrzaniolo on 24/08/17.
 */

public class MyRecyclerViewConfiguration extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
