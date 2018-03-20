package browser.iclick.com.main_frame.core;

import android.os.Bundle;

import java.util.Stack;

import browser.iclick.com.main_frame.BaseBiz;

/**
 * Created by bym on 2018/3/8.
 */

public class BaseStructureModel {

    final int key;

    BaseProxy baseProxy;

    private Object view;

    private Bundle bundle;

    private Class service;

    private Stack<Class> supper;

    private Object impl;

    private Object biz;

    BaseStructureModel(Object view, Bundle bundle) {
        //唯一标记
        key = view.hashCode();
        //视图
        this.view = view;
        //数据
        this.bundle = bundle;

        //业务初始化，解析View层的范型即业务类
        service = BaseUtils.getClassGenericType(view.getClass(), 0);

        if(service == null) {
            return;
        }

        if(!service.isInterface()) {
            impl = BaseUtils.getImplClassNotInf(service);

            //获取父类
            supper = new Stack<>();
            Class tempClass = impl.getClass().getSuperclass();

            if(tempClass != null) {
                while (!tempClass.equals(BaseBiz.class)) {
                    if(tempClass.getSuperclass() != null) {
                        Class clazz = tempClass.getSuperclass();
                        supper.add(clazz);
                    }
                    tempClass = tempClass.getSuperclass();
                }
            }

            // TODO: 2018/3/15

        } else {

        }

    }

    void clearAll() {
        this.bundle = null;
        this.view = null;
        service = null;
        if(this.impl != null) {
            ((BaseBiz)impl).detach();
            impl = null;
        }
        if(this.biz != null) {
            ((BaseBiz)biz).detach();
            biz = null;
        }
        if(baseProxy != null) {
            baseProxy.clearProxy();
            baseProxy = null;
        }
        if(supper != null) {
            supper.clear();
            supper = null;
        }
    }

    Class getService() {
        return service;
    }

    Object getView() {
        return view;
    }

    public BaseProxy getProxy() {
        return baseProxy;
    }



}













