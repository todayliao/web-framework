package cn.zhangxd.framework;

import cn.zhangxd.framework.helper.BeanHelper;
import cn.zhangxd.framework.helper.ClassHelper;
import cn.zhangxd.framework.helper.ControllerHelper;
import cn.zhangxd.framework.helper.IocHelper;
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
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }

}
