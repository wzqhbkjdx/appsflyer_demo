package browser.iclick.com.main_frame.core;

import retrofit2.Retrofit;

/**
 * Created by bym on 2018/3/9.
 */

public interface IBase {

    boolean isLogOpen();

    Retrofit.Builder httpAdapter(Retrofit.Builder builder);

    IBase IBASE = new IBase() {
        @Override
        public boolean isLogOpen() {
            return true;
        }

        @Override
        public Retrofit.Builder httpAdapter(Retrofit.Builder builder) {
            return null;
        }
    };


}
