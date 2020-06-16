package com.example.demo.thread;

import com.example.demo.common.Result;
import com.example.demo.thread.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchunpeng 2020/6/2
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/thread")
    public Result test(){
        for (int i = 0; i < 10; i++) {
            testService.test(i);
        }
        return Result.init();
    }
}
