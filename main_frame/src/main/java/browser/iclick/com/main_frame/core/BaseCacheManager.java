package browser.iclick.com.main_frame.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by bym on 2018/3/9.
 */

final class BaseCacheManager {

    //网络
    private final static int TYPE_HTTP = 1;

    //跳转调度
    private final static int TYPE_DISPLAY = 2;

    //业务
    private final static int TYPE_BIZ = 3;

    private final LoadingCache<Class<?>, Object> cache;

    private final ConcurrentHashMap<Class<?>, Integer> keyType = new ConcurrentHashMap<>();

    BaseCacheManager() {
        cache = CacheBuilder.newBuilder()
                .concurrencyLevel(10)
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .maximumSize(100)
                .initialCapacity(10)
                .recordStats()
                .build(new CacheLoader<Class<?>, Object>() {
                    @Override
                    public Object load(Class<?> key) throws Exception {
                        int type = keyType.get(key);
                        switch (type) {
                            case TYPE_HTTP:
                                BaseUtils.validateServiceInterface(key);
                                Object http = BaseHelper.httpAdapter().create(key);
                                if(BaseHelper.isLogOpen()) {
                                    L.tag("BaseCacheManager");
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("Http加载成功:");
                                    stringBuilder.append(key.getName());
                                    L.i(stringBuilder.toString());
                                }
                                return http;

                            case TYPE_DISPLAY:
                                BaseUtils.validateServiceInterface(key);

                                break;

                            case TYPE_BIZ:

                                break;

                        }


                        return null;
                    }
                });

    }



}













