package com.example.demo.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.Result;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private IUserService userService;

    @PutMapping("/user")
    public R insert(@RequestBody User user){
        userService.save(user);
        return R.ok(user);
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

}
