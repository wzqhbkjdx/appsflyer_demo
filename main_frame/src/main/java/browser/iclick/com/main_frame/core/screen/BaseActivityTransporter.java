package browser.iclick.com.main_frame.core.screen;

import android.os.Bundle;

/**
 * Created by bym on 2018/3/21.
 */

public class BaseActivityTransporter {

    private Class<?> toClazz;

    private Bundle bundle;

    public BaseActivityTransporter(Class<?> toClazz) {
        this.toClazz = toClazz;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Class<?> toClazz() {
        return toClazz;
    }

    public Bundle getBundle() {
        return this.bundle;
    }


}
