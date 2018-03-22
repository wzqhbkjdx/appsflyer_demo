package browser.iclick.com.main_frame.core;

import android.app.Application;
import android.os.Looper;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by bym on 2018/3/9.
 */

public class BaseHelper {

    static BaseModuleManager moduleManager;

    public static Application getInstance() {
        return moduleManager.application;
    }


    static Retrofit httpAdapter() {
        return moduleManager.retrofit;
    }


    public static boolean isLogOpen() {
        return moduleManager.base.isLogOpen();
    }

    public static void httpCancel(Call call) {
        if(call == null) {
            return;
        }

        if(call.isExecuted()) {
            call.cancel();
        }
    }

    public static boolean isMainLooperThread() {
        return Looper.getMainLooper().getThread() != Thread.currentThread();
    }

    public static SynchronousExecutor mainLooper() {
        return moduleManager.synchronousExecutor;
    }

    public static BaseScreenManager screenHelper() {
        return moduleManager.baseScreenManager;
    }

    public static BaseStructureManager structureHelper() {
        return moduleManager.baseStructureManager;
    }

}











