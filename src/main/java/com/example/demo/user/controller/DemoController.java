package com.example.demo.user.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.Result;
import com.example.demo.user.config.ApplicationObj;
import com.example.demo.user.entity.City;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.IUserService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/DEMO")
public class DemoController {
    @Autowired
    private IUserService userService;
    public static String test = "1";

    @GetMapping("/test")
    public Map test(){
        String data = "[\n" +
                "    {\n" +
                "        \"team_name\":\"测试队伍1\",\n" +
                "        \"user_name\":\"user1\",\n" +
                "        \"type\":\"attack\",\n" +
                "        \"create_time\": "+ DateUtil.current() +",\n" +
                "        \"step_name\":\"启用网络防火墙配置NAT和端口阻断\",\n" +
                "        \"operate_ip\":\"192.168.11.12\",\n" +
                "        \"dest_ip\":\"192.168.104.138\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"team_name\":\"测试队伍2\",\n" +
                "        \"user_name\":\"user2\",\n" +
                "        \"type\":\"attack\",\n" +
                "        \"create_time\":"+ DateUtil.current() +",\n" +
                "        \"step_name\":\"测试测试测试\",\n" +
                "        \"operate_ip\":\"192.168.10.13\",\n" +
                "        \"dest_ip\":\"192.168.104.138\"\n" +
                "    }\n" +
                "]";

        return ImmutableMap.of("msg", Arrays.asList(JSONUtil.parseArray(data)), "success",true);
    }

    @GetMapping("/test2")
    public Map test2(){
        String data = "[]";
        return ImmutableMap.of("msg", JSONUtil.parseArray(data), "success",true);
    }

    @GetMapping("/city")
    public R getCity(){
        JSONObject jsonObject = JSONUtil.readJSONObject(new File("D://data/city.json"), StandardCharsets.UTF_8);
        Map<String, List<City>> cityMap = new HashMap<>();
        JSONUtil.toList(jsonObject.getJSONArray("RECORDS"), City.class)
        .stream().collect(Collectors.groupingBy(city -> city.getCnty() == null ? "null":city.getCnty(),Collectors.toList())).forEach(cityMap::put);
        FileUtil.writeString(JSONUtil.toJsonPrettyStr(cityMap), new File("D://data/city2.json"), StandardCharsets.UTF_8);
        return R.ok(1);
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
