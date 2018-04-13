package browser.iclick.com.main_frame.core.plugins;

import android.os.Bundle;

import java.lang.reflect.Method;

/**
 * Created by bym on 2018/4/13.
 * 方法开始拦截器
 */

public interface DisplayStartInterceptor {

    <T> boolean interceptStart(String viewName, Class<T> service, Method method, int interceptor, String intent, Bundle bundle);

}
