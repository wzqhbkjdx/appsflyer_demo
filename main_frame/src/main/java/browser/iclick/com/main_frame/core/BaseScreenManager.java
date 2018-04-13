package browser.iclick.com.main_frame.core;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import browser.iclick.com.main_frame.core.screen.BaseActivityTransporter;
import browser.iclick.com.main_frame.core.screen.BaseScreenHolder;
import browser.iclick.com.main_frame.core.screen.IScreenCallback;

/**
 * Created by bym on 2018/3/21.
 */

public class BaseScreenManager {

    /**
     * 保存Activity及其运行状态
     */
    private final ArrayList<BaseScreenHolder> activities;

    private BaseActivityTransporter nextStep;

    private Activity previousActivity;

    public BaseScreenManager() {
        activities = new ArrayList<>();
    }

    public boolean isApplicationRunning() {
        synchronized (activities) {
            for(int i = 0; i < activities.size(); i++) {
                if(activities.get(i).isRunning()) {
                    return true;
                }
            }
        }
        return false;
    }

    public <T extends FragmentActivity> T getCurrentIsRunningActivity() {
        if(activities.size() > 0) {
            synchronized (activities) {
                for(int i = activities.size() - 1; i > 0; i--) {
                    if(activities.get(i).isRunning()) {
                        return (T) activities.get(i).getActivity();
                    }
                }
            }
        }
        return null;
    }

    public <T extends FragmentActivity> T getCurrentActivity() {
        if(activities.size() > 0) {
            return (T) activities.get(activities.size() - 1).getActivity();
        }
        return null;
    }

    public void setNextStep(BaseActivityTransporter transporter) {
        nextStep = transporter;
    }

    public BaseActivityTransporter getNextStep() {
        return nextStep;
    }

    public void moveForward(boolean finishThis) {
        if(nextStep != null) {
            Activity current = getCurrentActivity();
            if(current != null) {
                Intent intent = new Intent(current, nextStep.toClazz());
                if(nextStep.getBundle() != null) {
                    intent.putExtras(nextStep.getBundle());
                }
                getCurrentActivity().startActivity(intent);
                if(finishThis) {
                    current.finish();
                }
            }
        }
    }

    public ArrayList<Intent> getIntent() {
        synchronized (activities) {
            ArrayList<Intent> intentList = new ArrayList<>();

            for(int i = 0; i < activities.size(); i++) {
                intentList.add((Intent) activities.get(i).getActivity().getIntent().clone());
            }
            
            return intentList;
        }
    }

    public void moveForward(IScreenCallback callback, boolean finishThis) {
        if(nextStep != null) {
            Activity current = getCurrentActivity();
            if(current != null) {
                if(callback != null) {
                    boolean isCallback = callback.callBack(nextStep);
                    if(isCallback) {
                        return;
                    }
                    moveForward(finishThis);
                }
            }
        }
    }

    public void onCreate(FragmentActivity activity) {
        onCreate(activity, false);
    }

    public void onCreate(FragmentActivity activity, boolean asLanding) {
        synchronized (activities) {
            activities.add(new BaseScreenHolder(activity, asLanding));
        }
    }


    public <A> A getPreviousActivity() {
        int count = activities.size();
        if(count < 2) {
            return null;
        }
        return (A) activities.get(count - 2).getActivity();
    }
}








