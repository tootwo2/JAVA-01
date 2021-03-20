package org.example.rpcfx.client;


import com.alibaba.fastjson.parser.ParserConfig;
import net.sf.cglib.proxy.Enhancer;
import org.example.rpcfx.api.Filter;
import org.example.rpcfx.api.LoadBalancer;
import org.example.rpcfx.api.Router;

import java.util.ArrayList;
import java.util.List;

public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("org.example");
    }

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) create(serviceClass, url, filter);

    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> AOP
//        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));

        Enhancer enhancer = new Enhancer();

        //设置目标类的字节码文件
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new CglibMethodInterceptor(serviceClass, url, filters));

        return (T) enhancer.create();
    }

}
