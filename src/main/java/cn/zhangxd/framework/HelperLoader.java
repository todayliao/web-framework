package cn.zhangxd.framework;

import cn.zhangxd.framework.helper.*;
import cn.zhangxd.framework.util.ClassUtil;

/**
 * 加载响应的Helper类
 *
 * @author zhangxd
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}
