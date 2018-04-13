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

    public BaseStructureModel(Object view, Bundle bundle) {
        //唯一标记
        key = view.hashCode();
        //视图
        this.view = view;
        //数据
        this.bundle = bundle;

        //业务初始化，解析View层的范型即业务类的Class
        service = BaseUtils.getClassGenericType(view.getClass(), 0);

        if(service == null) {
            return;
        }

        if(!service.isInterface()) { //不是接口的情况下，直接cglib代理
            //实例instance
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

            //如果是业务类
            if(impl instanceof BaseBiz) {
                ((BaseBiz)impl).initUI(this);
            }

            //生成一个代理，AOP编程，在拦截器中同时也执行实例instance的方法
            baseProxy = BaseHelper.structureHelper().createNotIntf(service, impl);

        } else {
            //实例instance
            impl = BaseUtils.getImplClass(service);

            supper = new Stack<>();
            Class tempClass = impl.getClass().getSuperclass();

            if(tempClass != null) {
                while(!tempClass.equals(BaseBiz.class)) {
                    if(tempClass.getInterfaces() != null) {
                        Class clazz = tempClass.getInterfaces()[0];
                        supper.add(clazz);
                    }
                    tempClass = tempClass.getSuperclass();
                }
            }

            //如果是业务类
            if(impl instanceof BaseBiz) {
                ((BaseBiz)impl).initUI(this);
            }

            baseProxy = BaseHelper.structureHelper().create(service, impl);
        }

    }

    public void initBizBundle() {
        if(impl instanceof BaseBiz) {
            ((BaseBiz)impl).initBundle();
        }
    }

    public Bundle getBundle() {
        return bundle;
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

    public Object getView() {
        return view;
    }

    public BaseProxy getProxy() {
        return baseProxy;
    }



}













