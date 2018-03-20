package browser.iclick.com.main_frame.core;

import android.app.Application;

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

}
