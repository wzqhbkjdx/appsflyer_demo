package browser.iclick.com.main_frame.core.screen;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import browser.iclick.com.main_frame.core.BaseHelper;

/**
 * Created by bym on 2018/3/21.
 */

public class BaseScreenHolder {

    private FragmentActivity activity;

    private boolean isLanding = false;

    private boolean isRunning = true;

    private String activityName;

    public BaseScreenHolder(FragmentActivity activity, boolean isLanding) {
        this.activity = activity;
        this.activityName = activity.getClass().getSimpleName();
        this.isLanding = isLanding;
        //创建
    }

    public void pause() {
        this.isRunning = false;
        //暂停
    }

    public void resume() {
        this.isRunning = true;
        //运行
    }

    public void result() {
        this.isRunning = true;
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public boolean isLanding() {
        return isLanding;
    }

    public void setLanding(boolean isLanding) {
        this.isLanding = isLanding;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void finish() {
        activity.finish();
    }

    public void remove() {

    }

    private void log(String message) {
        if (BaseHelper.isLogOpen()) {
            Log.i("SKYActivityManager", activityName + message);
        }
    }

}









