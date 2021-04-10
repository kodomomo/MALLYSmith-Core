package com.github.kodomo.maalysmith.core.global.bean;

import com.github.kodomo.maalysmith.core.global.stub.GrpcStub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BeanContainer {

    private final Map<Class<?>, Object> BEANS = new HashMap<>();

    public <T> T registerBean(Class<T> tClass, Object... params) {
        try {
            Constructor<T> constructor = tClass.getConstructor(
                    Arrays.stream(params)
                            .map(Object::getClass)
                            .toArray(Class[]::new)
            );
            constructor.setAccessible(true);
            T instance = constructor.newInstance(params);
            BEANS.put(tClass, instance);
            return instance;
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T getBean(Class<T> tClass) {
        return tClass.cast(BEANS.get(tClass));
    }

    public void test() {
        System.out.println(BEANS);
    }

    public void clearBeans() {
        System.out.println("Beans clearing...");
        BEANS.values()
                .forEach(o -> {
                    if (Arrays.stream(o.getClass().getInterfaces()).anyMatch(aClass -> aClass == GrpcStub.class)) {
                        ((GrpcStub) o).shutdownChannel();
                    }
                });
    }

}
