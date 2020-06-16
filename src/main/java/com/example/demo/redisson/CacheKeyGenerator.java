package com.example.demo.redisson;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author xuchunpeng 2020/6/10
 */
public interface CacheKeyGenerator {

    String getLockKey(ProceedingJoinPoint pjp);
}
