package com.example.demo.user.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.Result;
import com.example.demo.user.config.ApplicationObj;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private IUserService userService;
    public static String test = "1";

    @RequestMapping("/user")
    public R insert(@RequestParam String s){
        test = s;
        return R.ok(test);
    }

    @RequestMapping("/asd")
    public R awsed(){
        System.out.println(ApplicationObj.class);
        return R.ok(ApplicationObj.class);
    }

    @DeleteMapping("/user/{id}")
    public Result delete(@PathVariable Integer id){
        userService.removeById(id);
        return Result.init();
    }

    @GetMapping("/user/{id}")
    public Result update(@PathVariable Integer id){
        User user = userService.getById(id);
        try {
            Thread.sleep(10000);
            user.setName("iiiiiiiii");
            userService.updateById(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Result.init();
    }

    @GetMapping("/user2/{id}")
    public Result update2(@PathVariable Integer id){
        User user = userService.getById(id);
        try {
            user.setName("xxxxxxxxxxxxx");
            userService.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.init();
    }
    @GetMapping("/log")
    public Result log(@RequestParam String name, @RequestParam String sex){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.set("name", name);
        jsonObject2.set("sex", sex);
        jsonObject.set("indexName", "xcp-test-3");
        jsonObject.set("data", jsonObject2);
        log.info(JSONUtil.toJsonStr(jsonObject));
        return Result.init();
    }

    @PostMapping("/log2")
    public Result log2(@RequestBody JSONObject jsonObject){
        log.info(JSONUtil.toJsonStr(jsonObject));
        return Result.init();
    }

    @PostMapping("/log3")
    public Result log3(){
        log.info("{\"data\":{\"body\":\"{\\\"name\\\":\\\"3333333333\\\",\\\"description\\\":\\\"\\\",\\\"quotaTypeId\\\":\\\"2\\\",\\\"trainId\\\":0}\",\"ip\":\"172.18.0.92\",\"type\":1,\"uri\":\"/project/import/async\",\"userCompany\":\"abcdefghijklmnopqrstuvwxyz\",\"userKey\":\"bcfd224770a14554a8df53a69ba31c99\",\"userRole\":\"leader\",\"userType\":\"designer\"},\"indexName\":\"gateway-log\"}");
        return Result.init();
    }

}
