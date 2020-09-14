package com.example.demo.springsecurity.controller;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuchunpeng 2020/7/28
 */
@RestController
public class SecurityController {

    @GetMapping("/404")
    public Result test404(){
        return Result.init("404");
    }
}
