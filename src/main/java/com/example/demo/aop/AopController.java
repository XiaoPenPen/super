package com.example.demo.aop;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/test")
    public R test(){
        System.out.println("GET");
        return R.ok("ok");
    }

    @PostMapping("/test2")
    public R test2(){
        System.out.println("POST");
        return R.ok("ok");
    }
}
