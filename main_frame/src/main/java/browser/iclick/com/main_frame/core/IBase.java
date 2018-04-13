package browser.iclick.com.main_frame.core;


import retrofit2.Retrofit;

/**
 * Created by bym on 2018/3/9.
 */

public interface IBase {

    /**
     * 日志是否打印
     * @return
     */
    boolean isLogOpen();

    /**
     * 获取网络适配器
     * @param builder
     * @return
     */
    Retrofit.Builder httpAdapter(Retrofit.Builder builder);



    // TODO: 2018/3/22

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
