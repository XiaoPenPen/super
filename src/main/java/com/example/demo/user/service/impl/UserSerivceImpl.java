package com.example.demo.user.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.user.entity.User;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserSerivceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public void test(@Valid User user) {
        Assert.notBlank(user.getName(), "用户据阿萨大大");
        System.out.println(1111);
    }


}
