package com.example.demo.redisson;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchunpeng 2020/6/10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    String prefix() default "";

    int expire() default 5;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimiter() default ":";

}
