package browser.iclick.com.main_frame.core;

/**
 * Created by bym on 2018/4/11.
 */

public interface IBaseViewCommon {

    /**
     * 进度布局
     * @return
     */
    int layoutLoading();

    /**
     * 空布局
     * @return
     */
    int layoutEmpty();

    /**
     * 网络业务错误
     * @return
     */
    int layoutBizError();

    /**
     * 网络错误
     */
    int layoutHttpError();

    IBaseViewCommon BASE_VIEW_COMMON = new IBaseViewCommon() {
        @Override
        public int layoutLoading() {
            return 0;
        }

        @Override
        public int layoutEmpty() {
            return 0;
        }

        @Override
        public int layoutBizError() {
            return 0;
        }

        @Override
        public int layoutHttpError() {
            return 0;
        }
    };

}
