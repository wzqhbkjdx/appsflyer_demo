package browser.iclick.com.main_frame.core;

import android.app.FragmentManager;

import java.util.ArrayList;

import browser.iclick.com.main_frame.BaseActivity;
import browser.iclick.com.main_frame.BaseBiz;

/**
 * Created by bym on 2018/3/9.
 */

public interface IBaseStructureManager {

    void attach(BaseStructureModel view);

    void detach(BaseStructureModel view);

    <B extends BaseBiz> B biz(Class<B> bizClazz);

    <B extends BaseBiz> boolean isExist(Class<B> bizClazz);

    <B extends BaseBiz> ArrayList<B> bizList(Class<B> service);

    <T> T createMainLooper(final Class<T> service, Object ui);

    <T> T createMainLooperNotIntf(final Class<T> service, Object ui);

    <T> BaseProxy createNotIntf(final Class superClazz, Object impl);

    <T> BaseProxy create(final Class<T> service, Object impl);

    <T> BaseProxy createDisplay(final Class<T> service, Object impl);

    <T> T createImpl(final Class<T> service, final Object impl);

    <U> U createNullService(final Class<U> service);

    boolean onKeyBack(int keyCode, FragmentManager fragmentManager, BaseActivity baseActivity);

    void printBackStackEntry(FragmentManager fragmentManager);

}











