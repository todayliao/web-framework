package cn.zhangxd.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * @author zhangxd
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     */
    Class<? extends Annotation> value();

}
