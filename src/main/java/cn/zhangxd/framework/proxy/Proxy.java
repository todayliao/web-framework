package cn.zhangxd.framework.proxy;

/**
 * 代理接口
 *
 * @author zhangxd
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}
