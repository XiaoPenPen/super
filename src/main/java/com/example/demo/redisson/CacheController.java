package com.example.demo.redisson;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchunpeng 2020/6/10
 */
@RestController
public class CacheController {

    @CacheLock(prefix = "test", expire = 10)
    @GetMapping("lock")
    public Result test(@CacheParam(name = "token") @RequestParam String token){
        return Result.init();
    }
}
