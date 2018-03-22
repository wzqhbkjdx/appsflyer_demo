package browser.iclick.com.main_frame;

import android.os.Bundle;

import java.util.Vector;

import browser.iclick.com.main_frame.core.BaseHelper;
import browser.iclick.com.main_frame.core.BaseInterceptor;
import browser.iclick.com.main_frame.core.BaseStructureModel;
import browser.iclick.com.main_frame.core.BaseUtils;
import browser.iclick.com.main_frame.core.exception.BaseHttpException;
import browser.iclick.com.main_frame.core.exception.BaseNotUIPointerException;
import browser.iclick.com.main_frame.core.exception.BaseNullPointerException;
import retrofit2.Call;

/**
 * Created by bym on 2018/3/8.
 */

public abstract class BaseBiz<U> implements BaseInterceptor {

    private U u;

    private Class ui;

    private static BaseStructureModel baseStructureModel;

    private Vector<Call> callVector;

    public void initUI(BaseStructureModel baseStructureModel) {
        this.baseStructureModel = baseStructureModel;
        //获取自身的范型，得到表示View层的对象
        ui = BaseUtils.getClassGenericType(this.getClass(), 0);
        if(ui == null) {
            throw new BaseNullPointerException("view或biz没有指定范型");
        }

        //创建UI线程的View对象的代理
        if(!ui.isInterface()) {
            u = (U) BaseHelper.structureHelper().createMainLooperNotIntf(ui, this.baseStructureModel.getView());
        } else {
            u = (U) BaseHelper.structureHelper().createMainLooper(ui, this.baseStructureModel.getView());
        }
        callVector = new Vector<>();
    }

    public void initBundle() {
        initBiz(this.baseStructureModel.getBundle());
    }

    /** 子业务类如果有需要，由子类来具体实现bundle的解析 **/
    protected void initBiz(Bundle bundle) {

    }


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











