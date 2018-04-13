package browser.iclick.com.main_frame.core;

import android.content.Context;

import browser.iclick.com.main_frame.BaseActivity;

/**
 * Created by bym on 2018/3/22.
 */

public class BaseView {

    /**
     * 常量
     */
    static final int STATE_ACTIVITY = 99999;

    static final int STATE_FRAGMENT = 88888;

    static final int STATE_DIALOGFRAGMENT = 77777;

    static final int STATE_NOTVIEW = 66666;


    /**
     * 类型
     */
    private int state;

    private BaseActivity mBaseActivity;

    private Context context;

    // TODO: 2018/3/22



}
