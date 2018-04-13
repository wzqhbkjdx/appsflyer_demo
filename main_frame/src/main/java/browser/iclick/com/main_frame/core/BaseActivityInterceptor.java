package browser.iclick.com.main_frame.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import browser.iclick.com.main_frame.BaseActivity;

/**
 * Created by bym on 2018/3/22.
 */

public interface BaseActivityInterceptor {

    void build(BaseActivity baseActivity, BaseBuilder initialBaseBuilder);

    void onCreate(BaseActivity baseActivity, Bundle bundle, Bundle savedInstanceState);

    void onPostCreate(BaseActivity baseActivity, Bundle savedInstanceState);

    void onStart(BaseActivity baseActivity);

    void onResume(BaseActivity baseActivity);

    void onPostResume(BaseActivity baseActivity);

    void onPause(BaseActivity baseActivity);

    void onStop(BaseActivity baseActivity);

    void onDestroy(BaseActivity baseActivity);

    void onRestart(BaseActivity baseActivity);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    void onShowLoading(BaseActivity baseActivity);

    void onCloseLoading(BaseActivity baseActivity);

    class AdapterInterceptor implements BaseActivityInterceptor {

        @Override
        public void build(BaseActivity baseActivity, BaseBuilder initialBaseBuilder) {

        }

        @Override
        public void onCreate(BaseActivity baseActivity, Bundle bundle, Bundle savedInstanceState) {

        }

        @Override
        public void onPostCreate(BaseActivity baseActivity, Bundle savedInstanceState) {

        }

        @Override
        public void onStart(BaseActivity baseActivity) {

        }

        @Override
        public void onResume(BaseActivity baseActivity) {

        }

        @Override
        public void onPostResume(BaseActivity baseActivity) {

        }

        @Override
        public void onPause(BaseActivity baseActivity) {

        }

        @Override
        public void onStop(BaseActivity baseActivity) {

        }

        @Override
        public void onDestroy(BaseActivity baseActivity) {

        }

        @Override
        public void onRestart(BaseActivity baseActivity) {

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        }

        @Override
        public void onShowLoading(BaseActivity baseActivity) {

        }

        @Override
        public void onCloseLoading(BaseActivity baseActivity) {

        }
    }

    BaseActivityInterceptor NONE = new BaseActivityInterceptor() {
        @Override
        public void build(BaseActivity baseActivity, BaseBuilder initialBaseBuilder) {

        }

        @Override
        public void onCreate(BaseActivity baseActivity, Bundle bundle, Bundle savedInstanceState) {

        }

        @Override
        public void onPostCreate(BaseActivity baseActivity, Bundle savedInstanceState) {

        }

        @Override
        public void onStart(BaseActivity baseActivity) {

        }

        @Override
        public void onResume(BaseActivity baseActivity) {

        }

        @Override
        public void onPostResume(BaseActivity baseActivity) {

        }

        @Override
        public void onPause(BaseActivity baseActivity) {

        }

        @Override
        public void onStop(BaseActivity baseActivity) {

        }

        @Override
        public void onDestroy(BaseActivity baseActivity) {

        }

        @Override
        public void onRestart(BaseActivity baseActivity) {

        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        }

        @Override
        public void onShowLoading(BaseActivity baseActivity) {

        }

        @Override
        public void onCloseLoading(BaseActivity baseActivity) {

        }
    };

    // TODO: 2018/3/22



}










