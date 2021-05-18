package com.example.demo.aop.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xuchunpeng 2021/4/2
 *
 * 更新openvpn用户缓存结构
 */
@Aspect
@Component
public class OpenvpnAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut(value = "execution(public * com.example.demo.aop.AopController.*(..)) || execution(public * com.example.demo.aop.AopController.*(..))")
    public void pointcutClass() {
    }

    @Pointcut( value = "!@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointcutAnnotation() {
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointcutClass() && pointcutAnnotation()")
    public void doAfterReturning(Object ret) {
        System.out.println("111111111111111");
    }


}
