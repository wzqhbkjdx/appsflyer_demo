package browser.iclick.com.main_frame.core;

import browser.iclick.com.main_frame.core.exception.BaseHttpException;
import browser.iclick.com.main_frame.core.exception.BaseNotUIPointerException;

/**
 * Created by bym on 2018/3/8.
 */

public interface BaseInterceptor {

    boolean interceptHttpError(String method, BaseHttpException httpException);

    boolean interceptUIError(String method, BaseNotUIPointerException notUIPointerException);

    boolean interceptBizError(String method, Throwable throwable);

}
