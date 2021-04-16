package com.github.kodomo.maalysmith.core.global.bean;

import com.github.kodomo.maalysmith.core.global.stub.GrpcStub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GRPCContainer {

    private final Map<Class<?>, GrpcStub> STUBS = new HashMap<>();

    public <T extends GrpcStub> void registerStub(Class<T> tClass, Object... params) {
        try {
            Constructor<T> constructor = tClass.getConstructor(
                    Arrays.stream(params)
                            .map(Object::getClass)
                            .toArray(Class[]::new)
            );
            constructor.setAccessible(true);
            T instance = constructor.newInstance(params);
            STUBS.put(tClass, instance);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public <T extends GrpcStub> T getBean(Class<T> tClass) {
        return tClass.cast(STUBS.get(tClass));
    }

}
