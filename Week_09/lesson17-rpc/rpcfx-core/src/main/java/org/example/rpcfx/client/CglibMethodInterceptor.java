package org.example.rpcfx.client;

import com.alibaba.fastjson.JSON;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.example.rpcfx.api.Filter;
import org.example.rpcfx.api.RpcfxRequest;
import org.example.rpcfx.api.RpcfxResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @Author tootwo2
 * @Date 2021/3/20 11:58 下午
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private final Class<?> serviceClass;
    private final String url;
    private final Filter[] filters;

    public <T> CglibMethodInterceptor(Class<T> serviceClass, String url, Filter... filters) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.filters = filters;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("基于字节码加强");

        RpcfxRequest request = new RpcfxRequest();
        request.setServiceClass(this.serviceClass.getName());
        request.setMethod(method.getName());
        request.setParams(args);

        RpcfxResponse response = post(request, url);

        return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
