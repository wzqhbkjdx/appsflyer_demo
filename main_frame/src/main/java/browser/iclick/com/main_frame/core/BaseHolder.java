package browser.iclick.com.main_frame.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by bym on 2018/4/11.
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    
    private BaseRVAdapter adapter;
    
    public BaseHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    
    public abstract void bindData(T t, int position);
    
    public void setAdapter(BaseRVAdapter adapter) {
        this.adapter = adapter;
    }
    
    public <T extends BaseRVAdapter> T getAdapter() {
        return (T) adapter;
    }

    // TODO: 2018/4/11  
    
    
    
    
}
