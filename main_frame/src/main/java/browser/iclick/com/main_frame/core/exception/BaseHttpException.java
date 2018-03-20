package browser.iclick.com.main_frame.core.exception;

/**
 * Created by bym on 2018/3/8.
 */

public class BaseHttpException extends RuntimeException {

    public BaseHttpException() {

    }

    public BaseHttpException(String detailMessage) {
        super(detailMessage);
    }

    public BaseHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseHttpException(Throwable cause) {
        super((cause == null ? null : cause.toString()), cause);
    }

}
