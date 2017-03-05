package cn.zhangxd.framework.ds.impl;

import cn.zhangxd.framework.ds.DataSourceFactory;
import cn.zhangxd.framework.helper.ConfigHelper;

import javax.sql.DataSource;

/**
 * 抽象数据源工厂
 *
 * @author zhangxd
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {

    protected final String driver = ConfigHelper.getJdbcDriver();
    protected final String url = ConfigHelper.getJdbcUrl();
    protected final String username = ConfigHelper.getJdbcUsername();
    protected final String password = ConfigHelper.getJdbcPassword();

    public final T getDataSource() {
        // 创建数据源对象
        T ds = createDataSource();
        // 设置基础属性
        setDriver(ds, driver);
        setUrl(ds, url);
        setUsername(ds, username);
        setPassword(ds, password);
        // 设置高级属性
        setAdvancedConfig(ds);
        return ds;
    }

    public abstract T createDataSource();

    public abstract void setDriver(T ds, String driver);

    public abstract void setUrl(T ds, String url);

    public abstract void setUsername(T ds, String username);

    public abstract void setPassword(T ds, String password);

    public abstract void setAdvancedConfig(T ds);
}
