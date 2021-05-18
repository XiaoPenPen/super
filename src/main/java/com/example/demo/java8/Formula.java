package com.example.demo.java8;

/**
 * @author xuchunpeng 2021/2/3
 */
public interface Formula {

    int sum(int a);

    default int sum2(int a){
        return a+3;
    }

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public int sum(int a) {
                return a;
            }
        };

        System.out.println(formula.sum(1));
        System.out.println(formula.sum2(2));
    }


}
