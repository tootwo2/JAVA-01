package org.example.rpcfx.api;

public interface RpcfxResolver {

    Object resolve(String serviceClass);

    <T> T resolveByClass(Class<T> clazz);

}
