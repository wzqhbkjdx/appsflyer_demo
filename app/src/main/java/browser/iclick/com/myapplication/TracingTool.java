package browser.iclick.com.myapplication;

import android.app.Activity;
import android.content.Context;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bym on 2018/3/7.
 */

public class TracingTool {

    public static void inAppEventTracing(Context context, int level, int score) {
        Map<String, Object> eventValue = new HashMap<>();
        eventValue.put(AFInAppEventParameterName.LEVEL, level);
        eventValue.put(AFInAppEventParameterName.SCORE, score);
        AppsFlyerLib.getInstance().trackEvent(context, AFInAppEventType.LEVEL_ACHIEVED,eventValue);
    }

    public static void appsFlayerEventTag(Context context, String eventName) {
        Map<String, Object> eventValue = new HashMap<>();
        AppsFlyerLib.getInstance().trackEvent(context.getApplicationContext(), eventName, eventValue);
    }

    public static void deepLinkTracing(Activity activity) {
        AppsFlyerLib.getInstance().sendDeepLinkData(activity);
    }

    public static void revenueEventTracing(Context context, int revenue, String contentType, String contentId, String currency) {
        Map<String, Object> eventValue = new HashMap<>();
        eventValue.put(AFInAppEventParameterName.REVENUE,revenue);
        eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, contentType);
        eventValue.put(AFInAppEventParameterName.CONTENT_ID, contentId);
        eventValue.put(AFInAppEventParameterName.CURRENCY, currency);
        AppsFlyerLib.getInstance().trackEvent(context,AFInAppEventType.PURCHASE,eventValue);
    }

}










