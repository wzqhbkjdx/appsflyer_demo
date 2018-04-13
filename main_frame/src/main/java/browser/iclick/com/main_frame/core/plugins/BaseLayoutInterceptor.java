package browser.iclick.com.main_frame.core.plugins;

import browser.iclick.com.main_frame.BaseActivity;
import browser.iclick.com.main_frame.core.BaseFragment;

/**
 * Created by bym on 2018/4/13.
 */

public interface BaseLayoutInterceptor {

    void showContent(BaseActivity baseActivity);

    void showEmpty(BaseActivity baseActivity);

    void showBizError(BaseActivity baseActivity);

    void showLoading(BaseActivity baseActivity);

    void showHttpError(BaseActivity baseActivity);

    void showContent(BaseFragment baseFragment);

    void showEmpty(BaseFragment baseFragment);

    void showBizError(BaseFragment baseFragment);

    void showLoading(BaseFragment baseFragment);

    void showHttpError(BaseFragment baseFragment);

    class AdapterInterceptor implements BaseLayoutInterceptor {

        @Override
        public void showContent(BaseActivity baseActivity) {

        }

        @Override
        public void showEmpty(BaseActivity baseActivity) {

        }

        @Override
        public void showBizError(BaseActivity baseActivity) {

        }

        @Override
        public void showLoading(BaseActivity baseActivity) {

        }

        @Override
        public void showHttpError(BaseActivity baseActivity) {

        }

        @Override
        public void showContent(BaseFragment baseFragment) {

        }

        @Override
        public void showEmpty(BaseFragment baseFragment) {

        }

        @Override
        public void showBizError(BaseFragment baseFragment) {

        }

        @Override
        public void showLoading(BaseFragment baseFragment) {

        }

        @Override
        public void showHttpError(BaseFragment baseFragment) {

        }
    }

    BaseLayoutInterceptor NONE = new BaseLayoutInterceptor() {
        @Override
        public void showContent(BaseActivity baseActivity) {

        }

        @Override
        public void showEmpty(BaseActivity baseActivity) {

        }

        @Override
        public void showBizError(BaseActivity baseActivity) {

        }

        @Override
        public void showLoading(BaseActivity baseActivity) {

        }

        @Override
        public void showHttpError(BaseActivity baseActivity) {

        }

        @Override
        public void showContent(BaseFragment baseFragment) {

        }

        @Override
        public void showEmpty(BaseFragment baseFragment) {

        }

        @Override
        public void showBizError(BaseFragment baseFragment) {

        }

        @Override
        public void showLoading(BaseFragment baseFragment) {

        }

        @Override
        public void showHttpError(BaseFragment baseFragment) {

        }
    };

}
