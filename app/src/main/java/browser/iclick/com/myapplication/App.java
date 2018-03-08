package browser.iclick.com.myapplication;

import android.app.Application;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import java.util.Map;

/**
 * Created by bym on 2018/3/7.
 */

public class App extends Application {

    private static final String AF_DEV_KEY = "rfatWW6JF9v7F36Zg8PWgh";

    @Override
    public void onCreate() {
        super.onCreate();

        AppsFlyerConversionListener conversionDataListener = new AppsFlyerConversionListener() {
            @Override
            public void onInstallConversionDataLoaded(Map<String, String> map) {
                for (String attrName : map.keySet()) {
                    Log.d(AppsFlyerLib.LOG_TAG, "attribute: " + attrName + " = " + map.get(attrName));
                }
                AttributeTool.getInstance().setAttributeMap(map);
            }

            @Override
            public void onInstallConversionFailure(String s) {

            }

            @Override
            public void onAppOpenAttribution(Map<String, String> map) {

            }

            @Override
            public void onAttributionFailure(String s) {

            }
        };

        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionDataListener, getApplicationContext());
        AppsFlyerLib.getInstance().startTracking(this);

    }




}
