package browser.iclick.com.main_frame;

import android.app.Application;

import browser.iclick.com.main_frame.core.IBase;
import browser.iclick.com.main_frame.core.IBaseViewCommon;
import retrofit2.Retrofit;

/**
 * Created by bym on 2018/3/22.
 */

public class BaseApplication extends Application implements IBase, IBaseViewCommon {

    // TODO: 2018/3/22


    @Override
    public boolean isLogOpen() {
        return false;
    }

    @Override
    public Retrofit.Builder httpAdapter(Retrofit.Builder builder) {
        return null;
    }

    @Override
    public int layoutLoading() {
        return 0;
    }

    @Override
    public int layoutEmpty() {
        return 0;
    }

    @Override
    public int layoutBizError() {
        return 0;
    }

    @Override
    public int layoutHttpError() {
        return 0;
    }
}
