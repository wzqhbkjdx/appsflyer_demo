package browser.iclick.com.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by bym on 2018/3/8.
 */


@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface Impl {
    Class value();
}
