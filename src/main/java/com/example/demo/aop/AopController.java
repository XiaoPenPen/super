package com.example.demo.aop;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/test")
    public R test(Integer id, @RequestParam String name){
        System.out.println(1);
        return R.ok("ok");
    }
}
