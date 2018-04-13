package browser.iclick.com.main_frame.core.view.swipewindow;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import browser.iclick.com.main_frame.core.BaseHelper;

/**
 * Created by bym on 2018/4/11.
 */

public class BaseSwipeWindowHelper extends Handler {

    private static final String TAG = "SwipeWindowHelper";

    private static final String CURRENT_POINT_X = "currentPointX"; //点击事件

    private static final int MSG_ACTION_DOWN = 1; //点击事件

    private static final int MSG_ACTION_MOVE = 2; //滑动事件

    private static final int MSG_ACTION_UP = 3; //点击结束

    private static final int MSG_SLIDE_CANCEL = 4; //开始滑动，不返回前一个页面

    private static final int MSG_SLIDE_CANCELED = 5; //结束滑动，不返回前一个页面

    private static final int MSG_SLIDE_PROCEED = 6; //开始滑动，返回前一个页面

    private static final int MSG_SLIDE_FINISHED = 7; //滑动结束，返回前一个页面

    private static final int SHADOW_WIDTH = 50; //px 阴影宽度

    private static final int EDGE_SIZE = 20; //dp 默认拦截手势区间

    private int mEdgeSize; //px 拦截手势区间

    private boolean mIsSliding; //是否正在滑动

    private boolean mIsSlideAnimPlaying; //滑动动画展示过程

    private float mDistanceX; //px 当前滑动距离（正数或0）

    private boolean mIsSupportSlideBack;

    private Window mCurrentWindow;

    private ViewManager mViewManager;

    private final FrameLayout mCurrentContentView;

    public BaseSwipeWindowHelper(@NonNull Window window) {
        this(window, true);
    }

    public BaseSwipeWindowHelper(@NonNull Window window, boolean isSupportSlideBack) {
        mCurrentWindow = window;
        mIsSupportSlideBack = isSupportSlideBack;
        mCurrentContentView = getContentView(window);
        mViewManager = new ViewManager();


    }

    private final FrameLayout getContentView(Window window) {
        if(window == null) return null;
        return window.findViewById(Window.ID_ANDROID_CONTENT);
    }

    @Override
    public void publish(LogRecord record) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

    class ViewManager {
        private Activity mPreviousActivity;

        private View mPreviousContentView;

        private View mShadowView;

        private boolean addViewFromPreviousActivity() {
            if(mCurrentContentView.getChildCount() == 0) {
                mPreviousActivity = null;
                mPreviousContentView = null;
                return false;
            }

            mPreviousActivity = BaseHelper.screenHelper().getPreviousActivity();
            if(mPreviousActivity == null) {
                mPreviousActivity = null;
                mPreviousContentView = null;
                return false;
            }

            //if(mPreviousActivity instanceof BaseActivity && )
            // TODO: 2018/4/13

            return false;
        }


    }
}












