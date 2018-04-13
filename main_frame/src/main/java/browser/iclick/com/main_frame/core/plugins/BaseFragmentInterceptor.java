package browser.iclick.com.main_frame.core.plugins;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import browser.iclick.com.main_frame.core.BaseBuilder;
import browser.iclick.com.main_frame.core.BaseFragment;

/**
 * Created by bym on 2018/4/13.
 */

public interface BaseFragmentInterceptor {

    void build(Fragment baseFragment, BaseBuilder initialBuilder);

    void onFragmentCreated(BaseFragment baseFragment, Bundle bundle, Bundle savedInstanceState);

    void buildAfter(BaseFragment baseFragment);

    void onFragmentStart(BaseFragment baseFragment);

    void onFragmentResume(BaseFragment baseFragment);

    void onFragmentPause(BaseFragment baseFragment);

    void onFragmentStop(BaseFragment baseFragment);

    void onFragmentDestroy(BaseFragment baseFragment);

    void onShowLoading(Fragment baseFragment);

    void onCloseLoading(Fragment baseFragment);

    class AdapterInterceptor implements BaseFragmentInterceptor {

        @Override
        public void build(Fragment baseFragment, BaseBuilder initialBuilder) {

        }

        @Override
        public void onFragmentCreated(BaseFragment baseFragment, Bundle bundle, Bundle savedInstanceState) {

        }

        @Override
        public void buildAfter(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentStart(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentResume(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentPause(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentStop(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentDestroy(BaseFragment baseFragment) {

        }

        @Override
        public void onShowLoading(Fragment baseFragment) {

        }

        @Override
        public void onCloseLoading(Fragment baseFragment) {

        }
    }

    BaseFragmentInterceptor NONE = new BaseFragmentInterceptor() {
        @Override
        public void build(Fragment baseFragment, BaseBuilder initialBuilder) {

        }

        @Override
        public void onFragmentCreated(BaseFragment baseFragment, Bundle bundle, Bundle savedInstanceState) {

        }

        @Override
        public void buildAfter(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentStart(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentResume(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentPause(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentStop(BaseFragment baseFragment) {

        }

        @Override
        public void onFragmentDestroy(BaseFragment baseFragment) {

        }

        @Override
        public void onShowLoading(Fragment baseFragment) {

        }

        @Override
        public void onCloseLoading(Fragment baseFragment) {

        }
    };

}












