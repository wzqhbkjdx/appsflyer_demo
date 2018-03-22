package browser.iclick.com.main_frame.core;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by bym on 2018/3/21.
 */

public class BaseDisplay implements IBaseDisplay {

    // TODO: 2018/3/21


    @Override
    public Context context() {
        return BaseHelper.screenHelper().getCurrentActivity();
    }

    @Override
    public <T extends FragmentActivity> T activity() {
        return null;
    }

    @Override
    public void start(@NonNull Class clazz) {

    }

    @Override
    public void intentFromFragment(@NonNull Class clazz, @NonNull Fragment fragment, int requestCode) {

    }

    @Override
    public void intentFromFragment(@NonNull Intent intent, @NonNull Fragment fragment, int requestCode) {

    }

    @Override
    public void onKeyHome() {

    }

    @Override
    public void popBackStack(@NonNull Class clazz) {

    }

    @Override
    public void popBackStack(@NonNull String clazzName) {

    }

    @Override
    public void popBackStackAll() {

    }

    @Override
    public void commitAdd(int layoutId, @NonNull Fragment fragment) {

    }

    @Override
    public void commitChildReplace(@NonNull Fragment srcFragment, int layoutId, @NonNull Fragment fragment) {

    }

    @Override
    public void commitReplace(int layoutId, @NonNull Fragment fragment) {

    }

    @Override
    public void commitBackStack(int layoutId, @NonNull Fragment fragment) {

    }

    @Override
    public void commitBackStack(int layoutId, @NonNull Fragment fragment, int animation) {

    }

    @Override
    public void intent(@NonNull Class clazz) {

    }

    @Override
    public void intent(@NonNull String clazzName) {

    }

    @Override
    public void intentNotAnimation(@NonNull Class clazz) {

    }

    @Override
    public void intent(@NonNull Class clazz, Bundle bundle) {

    }

    @Override
    public void intentNotAnimation(@NonNull Class clazz, @NonNull Bundle bundle) {

    }

    @Override
    public void intent(@NonNull Intent intent) {

    }

    @Override
    public void intent(@NonNull Intent intent, @NonNull Bundle options) {

    }

    @Override
    public void intentForResult(@NonNull Class clazz, int requestCode) {

    }

    @Override
    public void intentForResultFromFragment(@NonNull Class clazz, Bundle bundle, int requestCode, @NonNull Fragment fragment) {

    }

    @Override
    public void intentForResult(@NonNull Class clazz, @NonNull Bundle bundle, int requestCode) {

    }

    @Override
    public void intentForResult(@NonNull Intent intent, int requestCod) {

    }

    @Override
    public void intentForResult(@NonNull Intent intent, @NonNull Bundle options, int requestCode) {

    }

    @Override
    public void intentAnimation(@NonNull Class clazz, @NonNull View view, Bundle bundle) {

    }

    @Override
    public void intentAnimation(@NonNull Class clazz, int in, int out) {

    }

    @Override
    public void intentAnimation(@NonNull Class clazz, int in, int out, @NonNull Bundle bundle) {

    }

    @Override
    public void intentForResultAnimation(@NonNull Class clazz, @NonNull View view, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NonNull Class clazz, @NonNull View view, @NonNull Bundle bundle, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NonNull Class clazz, int in, int out, int requestCode) {

    }

    @Override
    public void intentForResultAnimation(@NonNull Class clazz, int in, int out, @NonNull Bundle bundle, int requestCode) {

    }

    @Override
    public void intentCustomAnimation(@NonNull Class clazz, int in, int out) {

    }

    @Override
    public void intentCustomAnimation(@NonNull Class clazz, int in, int out, @NonNull Bundle options) {

    }

    @Override
    public void intentScaleUpAnimation(@NonNull Class clazz, @NonNull View view, int startX, int startY, int startWidth, int startHeight) {

    }

    @Override
    public void intentScaleUpAnimation(@NonNull Class clazz, @NonNull View view, int startX, int startY, int startWidth, int startHeight, @NonNull Bundle options) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NonNull Class clazz, BaseDisplayModel... skyDisplayModel) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NonNull Class clazz, @NonNull Bundle options, BaseDisplayModel... skyDisplayModel) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NonNull Class clazz, View first, String second) {

    }

    @Override
    public void intentSceneTransitionAnimation(@NonNull Class clazz, View first, String second, @NonNull Bundle options) {

    }

    @Override
    public void intentClipRevealAnimation(@NonNull Class clazz, @NonNull View view, int startX, int startY, int width, int height) {

    }

    @Override
    public void intentClipRevealAnimation(@NonNull Class clazz, @NonNull View view, int startX, int startY, int width, int height, @NonNull Bundle options) {

    }

    @Override
    public void intentThumbnailScaleUpAnimation(@NonNull Class clazz, @NonNull View view, @NonNull Bitmap thumbnail, int startX, int startY) {

    }

    @Override
    public void intentThumbnailScaleUpAnimation(@NonNull Class clazz, @NonNull View view, @NonNull Bitmap thumbnail, int startX, int startY, @NonNull Bundle options) {

    }
}
