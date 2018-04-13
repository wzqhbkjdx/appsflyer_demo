package browser.iclick.com.main_frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import browser.iclick.com.main_frame.core.BaseBuilder;
import browser.iclick.com.main_frame.core.BaseHelper;
import browser.iclick.com.main_frame.core.BaseStructureModel;
import browser.iclick.com.main_frame.core.IBaseView;
import browser.iclick.com.main_frame.core.view.swipewindow.BaseSwipeWindowHelper;

/**
 * Created by bym on 2018/3/8.
 */

public abstract class BaseActivity<B extends BaseBiz> extends AppCompatActivity implements IBaseView {

    /**
     * 定制
     * @param initialBaseBuilder
     * @return
     */
    protected abstract BaseBuilder build(BaseBuilder initialBaseBuilder);

    /**
     * 编译
     */
    protected void buildBefore(Bundle bundle) {}

    /**
     * 初始化dagger
     */
    protected void initDagger() {

    }

    /**
     * 数据
     * @param savedInstanceState
     */
    protected void createData(Bundle savedInstanceState) {

    }

    protected abstract void initData(Bundle savedInstanceState);

    /**
     * View层编辑器
     */
    private BaseBuilder builder;

    private BaseStructureModel baseStructureModel;

    private SystemBarTintManager tintManager;

    private BaseSwipeWindowHelper mSwipeWindowHelper;

    private boolean isFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        buildBefore(savedInstanceState);
        super.onCreate(savedInstanceState);
        //初始化核心
        initCore();
        //初始化堆栈
        BaseHelper.screenHelper().onCreate(this);
        //Activity拦截器


    }

    protected void initCore() {
        baseStructureModel = new BaseStructureModel(this, getIntent() == null ? null : getIntent().getExtras());
        BaseHelper.structureHelper().attach(baseStructureModel);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showBizError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showHttpError() {

    }

    @Override
    public void loading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public int showState() {
        return 0;
    }

    @Override
    public void close() {

    }

    // TODO: 2018/3/15


}
