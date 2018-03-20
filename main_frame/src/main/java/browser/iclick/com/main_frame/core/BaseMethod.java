package browser.iclick.com.main_frame.core;

import java.lang.reflect.Method;

import browser.iclick.com.annotations.Background;
import browser.iclick.com.annotations.BackgroundType;
import browser.iclick.com.annotations.Interceptor;
import browser.iclick.com.annotations.Repeat;
import browser.iclick.com.main_frame.core.exception.BaseHttpException;
import browser.iclick.com.main_frame.core.exception.BaseNotUIPointerException;

/**
 * Created by bym on 2018/3/8.
 */

final class BaseMethod {

    //执行方法
    static final int TYPE_INVOKE_EXE = 0;

    static final int TYPE_DISPLAY_EXE = 4;

    //执行后台方法
    static final int TYPE_INVOKE_BACKGROUND_HTTP_EXE = 1;

    static final int TYPE_INVOKE_BACKGROUND_SINGLEWORK_EXE = 2;

    static final int TYPE_INVOKE_BACKGROUND_WORK_EXE = 3;

    static BaseMethod createBizMethod(Method method, Class service) {
        //是否重复
        boolean isRepeat = parseRepeat(method);
        //拦截方法标记
        int interceptor = parseInterceptor(method);
        //判断是否是子线程
        int type = parseBackground(method);

        return new BaseMethod(interceptor, method, type, isRepeat, service);
    }

    private static int parseBackground(Method method) {
        int type = TYPE_INVOKE_EXE;
        Background background = method.getAnnotation(Background.class);

        if(background != null) {
            BackgroundType backgroundType = background.value();
            switch (backgroundType) {
                case HTTP:
                    type = TYPE_INVOKE_BACKGROUND_HTTP_EXE;
                    break;

                case WORK:
                    type = TYPE_INVOKE_BACKGROUND_WORK_EXE;
                    break;

                case SINGLEWORK:
                    type = TYPE_INVOKE_BACKGROUND_SINGLEWORK_EXE;
                    break;
            }
        }

        return type;
    }

    private static boolean parseRepeat(Method method) {
        Repeat repeat = method.getAnnotation(Repeat.class);
        if(repeat != null && repeat.value()) {
            return true;
        } else {
            return false;
        }
    }

    private static int parseInterceptor(Method method) {
        Interceptor interceptorClass = method.getAnnotation(Interceptor.class);
        if(interceptorClass != null) {
            return interceptorClass.value();
        } else {
            return 0;
        }
    }

    private class MethodRunnable extends BaseRunnable {

        Object[] objects;

        public MethodRunnable() {
            super("MethodRunnable");
        }

        public void setArgs(Object[] objects) {
            this.objects = objects;
        }

        @Override
        protected void execute() {
            defaultMethod(objects);
        }
    }

    <T> T invoke(Object impl, Object[] args) {
        T result = null;
        if(!isRepeat) {
            if(isExe) { //存在的话，什么都不做
                L.tag("BaseMethod");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(impl.getClass().getSimpleName());
                stringBuilder.append(".");
                stringBuilder.append(method.getName());
                L.i("该方法正在执行 - %s", stringBuilder.toString());
                return result;
            }
            isExe = true;
        }

        this.impl = impl;
        this.implName = impl.getClass().getName();

        switch (type) {
            case TYPE_INVOKE_EXE:
                defaultMethod(args);
                break;
        }


        return result;
    }

    private void defaultMethod(Object[] objects) {
        // TODO: 2018/3/15
    }

    void exeError(Throwable throwable, String method) {
        throwable.printStackTrace();

        if(impl instanceof BaseInterceptor) {
            BaseInterceptor baseInterceptor = (BaseInterceptor) impl;

            if(throwable.getCause() instanceof BaseHttpException) {
                if(!baseInterceptor.interceptHttpError(method, (BaseHttpException) throwable.getCause())) {
                    //网络错误拦截器
                    // TODO: 2018/3/15
                }
            } else if(throwable.getCause() instanceof BaseNotUIPointerException) {

            } else {

            }

        }
    }



    int type;

    Object impl;

    String implName;

    boolean isRepeat;

    Method method;

    MethodRunnable methodRunnable;

    Class service;

    int interceptor;

    Object backgroundResult;

    boolean isExe;

    BaseMethod(int interceptor, Method method, int type, boolean isRepeat, Class service) {
        this.interceptor = interceptor;
        this.type = type;
        this.isRepeat = isRepeat;
        this.method = method;
        this.service = service;
        if(type == TYPE_INVOKE_BACKGROUND_HTTP_EXE || type == TYPE_INVOKE_BACKGROUND_SINGLEWORK_EXE
                || type == TYPE_INVOKE_BACKGROUND_WORK_EXE) {
            this.methodRunnable = new MethodRunnable();
        }
    }

}
