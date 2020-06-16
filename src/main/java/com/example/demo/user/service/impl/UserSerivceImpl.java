package com.example.demo.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.user.entity.User;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserSerivceImpl extends ServiceImpl<UserMapper,User> implements IUserService {
}
