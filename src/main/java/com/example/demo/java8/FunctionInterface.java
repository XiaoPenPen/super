package com.example.demo.java8;

import org.laziji.commons.rereg.exception.RegexpIllegalException;
import org.laziji.commons.rereg.exception.TypeNotMatchException;
import org.laziji.commons.rereg.exception.UninitializedException;
import org.laziji.commons.rereg.model.Node;
import org.laziji.commons.rereg.model.OrdinaryNode;

/**
 * @author xuchunpeng 2021/2/3
 */
@FunctionalInterface
public interface FunctionInterface<F,T> {

    T convert(F from);


    public static void main(String[] args) throws RegexpIllegalException, TypeNotMatchException, UninitializedException {
        FunctionInterface functionInterface = (from) -> String.valueOf(from);
        String str = random("[a-zA-Z]{5}_[0-9]{3,11}");
        System.out.println(str);
        System.out.println(str.length());
        String sre2 = "aaaaa2_Aaaa";
        System.out.println(sre2.matches("^(?!.*\\s)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W_]).{8,16}$"));

//        String aa = "1";
//
//        User user = new User();
//        user.setName("123");
//        aa = user.getName();
//        user.setName("456");
//        System.out.println(aa);
//        System.out.println(user.getName());





    }

    public static String random(String expression)
            throws RegexpIllegalException, TypeNotMatchException, UninitializedException {

        Node node = new OrdinaryNode(expression);
        return node.random();
    }
}
