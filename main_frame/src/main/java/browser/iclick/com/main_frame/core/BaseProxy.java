package browser.iclick.com.main_frame.core;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bym on 2018/3/8.
 * 业务的代理类
 */

public class BaseProxy {

    //实现类
    public Object impl;

    //代理类
    public Object proxy;

    //方法缓存
    public ConcurrentHashMap<String, BaseMethod> methodCache = new ConcurrentHashMap<>();

    public void clearProxy() {
        impl = null;
        proxy = null;
        methodCache.clear();
        methodCache = null;
    }


}
