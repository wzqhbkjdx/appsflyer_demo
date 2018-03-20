package browser.iclick.com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by bym on 2018/3/8.
 */


@Target({ElementType.TYPE, ElementType.METHOD})
public @interface OpenDisplay {
    String name() default "undefined";
}
