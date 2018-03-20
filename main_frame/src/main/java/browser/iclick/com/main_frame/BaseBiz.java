package browser.iclick.com.main_frame;

import java.util.Vector;

import browser.iclick.com.main_frame.core.BaseHelper;
import browser.iclick.com.main_frame.core.BaseInterceptor;
import browser.iclick.com.main_frame.core.BaseStructureModel;
import browser.iclick.com.main_frame.core.exception.BaseHttpException;
import browser.iclick.com.main_frame.core.exception.BaseNotUIPointerException;
import retrofit2.Call;

/**
 * Created by bym on 2018/3/8.
 */

public abstract class BaseBiz<U> implements BaseInterceptor {

    private U u;

    private Class ui;

    private static BaseStructureModel baseStructureModel;

    private Vector<Call> callVector;

    @Override
    public boolean interceptHttpError(String method, BaseHttpException httpException) {
        return false;
    }

    @Override
    public boolean interceptUIError(String method, BaseNotUIPointerException notUIPointerException) {
        return false;
    }

    @Override
    public boolean interceptBizError(String method, Throwable throwable) {
        return false;
    }

    public void detach() {
        u = null;
        ui = null;
        baseStructureModel = null;
        httpCancel();
    }

    protected void httpCancel() {
        int count = callVector.size();
        if(count < 1) {
            return;
        }
        for(int i = 0; i < count; i++) {
            Call call = callVector.get(i);
            BaseHelper.httpCancel(call);
        }
        callVector.removeAllElements();
    }

}











