package com.example.demo.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/get")
    public R get() {
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.forEach(i ->{
        try {
            System.out.println(i);
            try {
                throw new Exception("异常1！");
            } catch (Exception e){
                throw new Exception("异常2！");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        });
        return R.ok("123");
    }

}
