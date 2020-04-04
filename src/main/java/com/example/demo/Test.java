package com.example.demo;

import com.example.demo.entity.User;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class Test {
    public static void main(String[] args) {
        User user = new User() ;
        ReferenceQueue<User> ref =  new ReferenceQueue<User>();
        SoftReference softReference = new SoftReference(user,ref);

    }
}
