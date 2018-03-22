package browser.iclick.com.main_frame.core;

import android.support.annotation.NonNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Nonnull;

import browser.iclick.com.annotations.Impl;
import browser.iclick.com.main_frame.core.exception.BaseNullPointerException;

/**
 * Created by bym on 2018/3/9.
 */

public class BaseUtils {

    static <T> void validateServiceInterface(Class<T> service) {
        if(!service.isInterface()) {
            throw new IllegalArgumentException("该类不是接口");
        }
    }


    public static Class getClassGenericType(final Class clazz, final int index) {
        Type type = clazz.getGenericSuperclass();

        if(!(type instanceof ParameterizedType)) {
            return null;
        }

        ParameterizedType pType = (ParameterizedType) type;

        Type[] tArgs = pType.getActualTypeArguments();

        if(tArgs.length < 1) {
            return null;
        }

        return (Class) tArgs[index];
    }

    //找到接口的实现类
    static <D> Object getImplClassNotInf(@Nonnull Class<D> service) {
        try {
            Constructor c = service.getDeclaredConstructor();
            c.setAccessible(true);
            checkNotNull(service, "业务类为空");
            return c.newInstance();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，没有找到构造方法！" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，访问权限异常！" + e.getMessage());
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，实例化异常！" + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，反射异常！" + e.getMessage());
        }
    }

    static <T> T checkNotNull(T reference, String errorMessageTemplate) {
        if(reference == null) {
            throw new BaseNullPointerException(errorMessageTemplate);
        }
        return reference;
    }


    public static <D> Object getImplClass(@NonNull Class<D> service) {
        validateServiceInterface(service);

        try {
            //获取注解
            Impl impl = service.getAnnotation(Impl.class);
            checkNotNull(impl, "该接口没有指定实现类");

            Class clazz = Class.forName(impl.value().getName());
            Constructor c = clazz.getDeclaredConstructor();
            c.setAccessible(true);
            checkNotNull(clazz, "业务类为空～");
            return c.newInstance();

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，没有找到业务类！" + e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，实例化异常！" + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，访问权限异常！" + e.getMessage());
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，没有找到构造方法！" + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(String.valueOf(service) + "，反射异常！" + e.getMessage());
        }


    }




}

















