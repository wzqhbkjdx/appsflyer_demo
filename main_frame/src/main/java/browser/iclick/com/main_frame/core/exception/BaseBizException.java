package browser.iclick.com.main_frame.core.exception;

/**
 * Created by bym on 2018/3/8.
 */

public class BaseBizException extends RuntimeException {

    public BaseBizException() {}

    public BaseBizException(String detailMessage) {
        super(detailMessage);
    }

    public BaseBizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBizException(Throwable cause) {
        super((cause == null ? null : cause.toString()), cause);
    }

}
