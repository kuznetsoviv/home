package ru.kuznetsoviv.generics;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericExample {

    public static void main(String[] args) throws Exception {
        Class<?> cl = GenericSimple.class;
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            System.out.println("method: " + method.getName());
            Class<?>[] pts = method.getParameterTypes();
            for (Class clazz : pts) {
                System.out.println(clazz.getCanonicalName());
            }
        }
        System.out.println("------------------------------------");
        Method method = cl.getMethod("findSomething", Map.class);
        System.out.println(method.getName());
        Type[] pts = method.getGenericParameterTypes();
        for (Type clazz : pts) {
            System.out.println(clazz.getTypeName());
            if (clazz instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) clazz;
                System.out.println("Parameterized");
                System.out.println(parameterizedType.getActualTypeArguments()[0]);
                System.out.println(parameterizedType.getActualTypeArguments()[1]);
            }
        }
    }

}
