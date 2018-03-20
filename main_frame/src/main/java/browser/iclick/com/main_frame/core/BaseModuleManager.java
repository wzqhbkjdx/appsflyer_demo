package browser.iclick.com.main_frame.core;

import android.app.Application;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by bym on 2018/3/9.
 */

public class BaseModuleManager {

    @Inject public Application application;

    @Inject public Retrofit retrofit;

    @Inject public IBase base;




}
