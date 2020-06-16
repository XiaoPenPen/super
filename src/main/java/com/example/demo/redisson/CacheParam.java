package com.example.demo.redisson;

/**
 * @author xuchunpeng 2020/6/10
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    String name() default "";
}
