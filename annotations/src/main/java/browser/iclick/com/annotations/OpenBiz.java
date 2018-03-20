package browser.iclick.com.annotations;

/**
 * Created by bym on 2018/3/8.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface OpenBiz {
    String name() default "undefined";
}
