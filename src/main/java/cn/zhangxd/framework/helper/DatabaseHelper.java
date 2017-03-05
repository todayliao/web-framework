package cn.zhangxd.framework.helper;

import cn.zhangxd.framework.InstanceFactory;
import cn.zhangxd.framework.ds.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库操作助手类
 *
 * @author zhangxd
 */
public final class DatabaseHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 定义一个局部线程变量
     */
    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    private static final DataSourceFactory dataSourceFactory = InstanceFactory.getDataSourceFactory();



    public static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }

    public static Connection getConnection() {
        Connection conn;
        try {
            conn = connContainer.get();
            if (conn == null) {
                conn = getDataSource().getConnection();
                if (conn != null) {
                    connContainer.set(conn);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("获取数据库连接出错", e);
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                LOGGER.error("begin transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.set(conn);
            }
        }
    }

    public static void commitTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }

    public static void rollbackTransaction() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("rollback transaction failure", e);
                throw new RuntimeException(e);
            } finally {
                connContainer.remove();
            }
        }
    }

}
