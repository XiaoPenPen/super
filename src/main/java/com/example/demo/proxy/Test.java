package com.example.demo.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * @author xuchunpeng 2020/6/3
 */
public class Test {
    interface IPerson{

        String say();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        IPerson chinese = (IPerson) Proxy.newProxyInstance(IPerson.class.getClassLoader(), new Class[]{IPerson.class}, (proxy, method, args1) -> {
            if ("say".equals(method.getName())) {
                System.out.println("11");
            }
            return null;
        });
        chinese.say();
    }


}
