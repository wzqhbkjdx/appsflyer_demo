package browser.iclick.com.main_frame.core;

/**
 * Created by bym on 2018/3/22.
 */

public interface IBaseView {

    int STATE_CONTENT = 1;

    int STATE_LOADING = 2;

    int STATE_EMPTY = 3;

    int STATE_BIZ_ERROR = 4;

    int STATE_HEEP_ERROR = 5;

    void showContent();

    void showEmpty();

    void showBizError();

    void showLoading();

    void showHttpError();

    void loading();

    void closeLoading();

    int showState();

    void close();

    // TODO: 2018/3/22



}
