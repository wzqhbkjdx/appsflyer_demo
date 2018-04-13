package browser.iclick.com.main_frame.core;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import browser.iclick.com.main_frame.BaseActivity;

/**
 * Created by bym on 2018/4/11.
 */

public abstract class BaseRVAdapter<T, V extends BaseHolder> extends RecyclerView.Adapter<V> {

    private static final int VIEW_ITEM = 0;

    private static final int VIEW_PROG = 9999;

    private static final int VIEW_TOP = 10000;

    public abstract V newViewHolder(ViewGroup viewGroup, int type);

    public V newLoadMoreHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    public V newTopHolder(ViewGroup viewGroup, int type) {
        return null;
    }

    private BaseRVAdapter() {

    }

    /**
     * 数据
     */
    private List mItems;

    private BaseView baseView;

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BaseRVAdapter(BaseActivity baseActivity) {
        BaseUtils.checkNotNull(baseActivity, "View层不存在");
        // TODO: 2018/4/11
    }



}







