package browser.iclick.com.main_frame.core;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Looper;
import android.os.Trace;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import browser.iclick.com.main_frame.BaseActivity;
import browser.iclick.com.main_frame.BaseBiz;
import sky.cglib.proxy.Enhancer;
import sky.cglib.proxy.MethodInterceptor;

/**
 * Created by bym on 2018/3/9.
 */

public class BaseStructureManager implements IBaseStructureManager {

    private final ConcurrentHashMap<Class<?>, Stack<BaseStructureModel>> stackRepeatBiz;

    public BaseStructureManager() {
        stackRepeatBiz = new ConcurrentHashMap<>();
    }

    @Override
    public void attach(BaseStructureModel view) {
        synchronized (stackRepeatBiz) {
            if(view.getService() == null) {
                return;
            }

            Stack<BaseStructureModel> stack = stackRepeatBiz.get(view.getService());
            if(stack == null) {
                stack = new Stack<>();
            }
            stack.push(view);
            stackRepeatBiz.put(view.getService(), stack);
        }
    }

    @Override
    public void detach(BaseStructureModel view) {
        synchronized (stackRepeatBiz) {
            if(view.getService() == null) {
                return;
            }

            Stack<BaseStructureModel> stack = stackRepeatBiz.get(view.getService());

            if(stack == null) {
                return;
            }

            BaseStructureModel baseStructureModel = stack.pop();

            if(baseStructureModel == null) {
                L.d("SKYStructureManage.detach not find model(" + view.key + ")");
                return;
            }

            if(stack.size() < 1) {
                stackRepeatBiz.remove(view.getService());
            }

            if (BaseHelper.isLogOpen()) {
                L.d(view.getView().getClass().getSimpleName() + " -- stack:remove(" + view.key + ")");

                StringBuilder builder = new StringBuilder("\u21E0 ");
                builder.append("SKYStructureManage.Biz").append('(');
                if (stackRepeatBiz != null && stackRepeatBiz.size() > 0) {
                    for (Class clazz : stackRepeatBiz.keySet()) {
                        builder.append(clazz.getSimpleName());
                        builder.append(", ");
                    }
                    builder.deleteCharAt(builder.length() - 1);
                }

                builder.append(')');
                L.d(builder.toString());
            }

            if(baseStructureModel != null) {
                baseStructureModel.clearAll();
            }

        }
    }

    @Override
    public <B extends BaseBiz> B biz(Class<B> biz) {
        Stack<BaseStructureModel> stack = stackRepeatBiz.get(biz);
        if(stack == null) {
            return createNullService(biz);
        }
        BaseStructureModel baseStructureModel = stack.peek();

        if(baseStructureModel == null) {
            return createNullService(biz);
        }

        if(baseStructureModel.getProxy() == null || baseStructureModel.getProxy().proxy == null) {
            return createNullService(biz);
        }

        return (B) baseStructureModel.getProxy().proxy;
    }

    @Override
    public <B extends BaseBiz> boolean isExist(Class<B> bizClazz) {
        Stack<BaseStructureModel> stack = stackRepeatBiz.get(bizClazz);

        if(stack == null) {
            return false;
        }

        BaseStructureModel baseStructureModel = stack.peek();
        if(baseStructureModel == null) {
            return false;
        }

        if(baseStructureModel.getProxy() == null || baseStructureModel.getProxy().proxy == null) {
            return false;
        }

        return true;
    }

    @Override
    public <B extends BaseBiz> ArrayList<B> bizList(Class<B> service) {
        Stack<BaseStructureModel> stack = stackRepeatBiz.get(service);
        ArrayList list = new ArrayList();
        if(stack == null) {
            return list;
        }

        int count = stack.size();

        for(int i = 0; i < count; i++) {
            BaseStructureModel baseStructureModel = stack.get(i);
            if(baseStructureModel == null || baseStructureModel.getProxy() == null || baseStructureModel.getProxy().proxy == null) {
                list.add(0, createNullService(service));
            } else {
                list.add(0, baseStructureModel.getProxy().proxy);
            }
        }
        return list;
    }

    @Override
    public <T> T createMainLooper(Class<T> service, final Object ui) {
        BaseUtils.validateServiceInterface(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
                //如果有返回值，直接执行
                if(!method.getReturnType().equals(void.class)) {
                    if(ui == null) {
                        return null;
                    }
                    return method.invoke(ui, args);
                }

                //如果是主线程，直接执行
                if(BaseHelper.isMainLooperThread()) {
                    if(ui == null) {
                        return null;
                    }
                    return method.invoke(ui, args);
                }

                //如果不是在主线程，则创建一个Runnable，放到主线程中执行
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(ui == null) {
                                return;
                            }
                            method.invoke(ui, args);
                        } catch (Exception throwable) {
                            if(BaseHelper.isLogOpen()) {
                                throwable.printStackTrace();
                            }
                            return;
                        }


                    }
                };
                BaseHelper.mainLooper().execute(runnable);
                return null;
            }
        });
    }

    @Override
    public <T> T createMainLooperNotIntf(final Class<T> service, final Object ui) {
        Enhancer e = new Enhancer(BaseHelper.getInstance());
        e.setSuperclass(service);
        e.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(String name, Class[] argsType, final Object[] args) throws Exception {
                final Method method = service.getMethod(name, argsType);

                //如果有返回值，直接执行
                if(!method.getReturnType().equals(void.class)) {
                    if(ui == null) {
                        return null;
                    }
                    return method.invoke(ui, args);
                }

                //如果是主线程，直接执行
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(ui == null) {
                                return;
                            }
                            method.invoke(ui, args);
                        } catch (Exception throwable) {
                            if(BaseHelper.isLogOpen()) {
                                throwable.printStackTrace();
                            }
                        }
                    }
                };

                //不在主线程，则new一个Runnable扔到主线程中去执行
                BaseHelper.mainLooper().execute(runnable);
                return null;
            }
        });
        return (T) e.create();
    }

    @Override
    public <T> BaseProxy createNotIntf(final Class superClazz, Object impl) {
        final BaseProxy baseProxy = new BaseProxy();
        baseProxy.impl = impl;
        Enhancer e = new Enhancer(BaseHelper.getInstance());
        e.setSuperclass(superClazz);
        e.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(String name, Class[] argsType, Object[] args) throws Exception {
                Method method = superClazz.getMethod(name, argsType);

                //如果有返回值，直接执行
                if(!method.getReturnType().equals(void.class)) {
                    return method.invoke(baseProxy.impl, args);
                }

                BaseMethod baseMethod = loadBaseMethod(baseProxy, method, superClazz);

                if(BaseHelper.isLogOpen()) {
                    return baseMethod.invoke(baseProxy.impl, args);
                }

                enterMethod(method, args);
                long startNanos = System.nanoTime();

                Object result = baseMethod.invoke(baseProxy.impl, args);

                long stopNanos = System.nanoTime();
                long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

                exitMethod(method, result, lengthMillis);
                return result;
            }
        });
        baseProxy.proxy = e.create();
        return baseProxy;
    }

    @Override
    public <T> BaseProxy create(Class<T> service, Object impl) {
        return null;
    }

    @Override
    public <T> BaseProxy createDisplay(Class<T> service, Object impl) {
        return null;
    }

    @Override
    public <T> T createImpl(Class<T> service, Object impl) {
        return null;
    }

    @Override
    public <U> U createNullService(final Class<U> service) {
        if(!service.isInterface()) {
            return createNullServiceNotInf(service);
        }

        return (U) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (BaseHelper.isLogOpen()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UI被销毁,回调接口继续执行");
                    stringBuilder.append("方法[");
                    stringBuilder.append(method.getName());
                    stringBuilder.append("]");
                    L.tag(service.getSimpleName());
                    L.i(stringBuilder.toString());
                }

                if (method.getReturnType().equals(int.class) || method.getReturnType().equals(long.class) || method.getReturnType().equals(float.class) || method.getReturnType().equals(double.class)
                        || method.getReturnType().equals(short.class)) {
                    return 0;
                }

                if (method.getReturnType().equals(boolean.class)) {
                    return false;
                }

                if (method.getReturnType().equals(byte.class)) {
                    return Byte.parseByte(null);
                }

                if (method.getReturnType().equals(char.class)) {
                    return ' ';
                }

                return null;
            }
        });
    }

    public <U> U createNullServiceNotInf(final Class<U> service) {
        Enhancer e = new Enhancer(BaseHelper.getInstance());
        e.setSuperclass(service);
        e.setInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(String name, Class[] argsType, Object[] args) throws Exception {
                final Method method = service.getMethod(name, argsType);
                if (BaseHelper.isLogOpen()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("UI被销毁,回调接口继续执行");
                    stringBuilder.append("方法[");
                    stringBuilder.append(method.getName());
                    stringBuilder.append("]");
                    L.tag(service.getSimpleName());
                    L.i(stringBuilder.toString());
                }

                if (method.getReturnType().equals(int.class) || method.getReturnType().equals(long.class) || method.getReturnType().equals(float.class) || method.getReturnType().equals(double.class)
                        || method.getReturnType().equals(short.class)) {
                    return 0;
                }

                if (method.getReturnType().equals(boolean.class)) {
                    return false;
                }

                if (method.getReturnType().equals(byte.class)) {
                    return Byte.parseByte(null);
                }

                if (method.getReturnType().equals(char.class)) {
                    return ' ';
                }

                return null;
            }
        });

        return (U) e.create();
    }

    @Override
    public boolean onKeyBack(int keyCode, FragmentManager fragmentManager, BaseActivity baseActivity) {
        return false;
    }

    @Override
    public void printBackStackEntry(FragmentManager fragmentManager) {

    }

    private <T> BaseMethod loadBaseMethod(BaseProxy baseProxy, Method method, Class<T> service) {
        synchronized (baseProxy.methodCache) {
            String methodKey = getKey(method, method.getParameterTypes());
            BaseMethod baseMethod = baseProxy.methodCache.get(methodKey);
            if(baseMethod == null) {
                baseMethod = BaseMethod.createBizMethod(method, service);
            }
            return baseMethod;
        }
    }

    private String getKey(Method method, Class[] classes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(method.getName());
        stringBuilder.append("(");
        for (Class clazz : classes) {
            stringBuilder.append(clazz.getSimpleName());
            stringBuilder.append(",");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private void enterMethod(Method method, Object... args) {
        Class<?> cls = method.getDeclaringClass();
        String methodName = method.getName();
        Object[] parameterValues = args;
        StringBuilder builder = new StringBuilder("\u21E2 ");
        builder.append(methodName).append('(');
        if(parameterValues != null) {
            for(int i = 0; i < parameterValues.length; i++) {
                if(i > 0) {
                    builder.append(", ");
                }
                builder.append(Strings.toString(parameterValues[i]));
            }
        }

        builder.append(')');

        if(Looper.myLooper() != Looper.getMainLooper()) {
            builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
        }

        Log.v(cls.getSimpleName(), builder.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final String section = builder.toString().substring(2);
            Trace.beginSection(section);
        }

    }

    private void exitMethod(Method method, Object result, long lengthMillis) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }
        Class<?> cls = method.getDeclaringClass();
        String methodName = method.getName();
        boolean hasReturnType = method.getReturnType() != void.class;

        StringBuilder builder = new StringBuilder("\u21E0 ").append(methodName).append(" [").append(lengthMillis).append("ms]");

        if (hasReturnType) {
            builder.append(" = ");
            builder.append(Strings.toString(result));
        }
        Log.v(cls.getSimpleName(), builder.toString());
    }


}













