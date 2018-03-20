package browser.iclick.com.main_frame.core;

import android.app.FragmentManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

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
        return null;
    }

    @Override
    public <T> T createMainLooper(Class<T> service, Object ui) {
        return null;
    }

    @Override
    public <T> T createMainLooperNotIntf(Class<T> service, Object ui) {

        return null;
    }

    @Override
    public <T> BaseProxy createNotIntf(Class superClazz, Object impl) {
        return null;
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


}













