package browser.iclick.com.main_frame.core.plugins;

import java.lang.reflect.Method;

/**
 * Created by bym on 2018/4/13.
 */

public interface BaseStartInterceptor {

    <T> void interceptStart(String viewName, Class<T> service, Method method, int interceptor, Object[] objects);

}
