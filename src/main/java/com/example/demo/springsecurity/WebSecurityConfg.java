/*
package com.example.demo.springsecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

*/
/**
 * @author xuchunpeng 2020/7/27
 *//*

//@Configuration
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/500").permitAll()
                .antMatchers("/403").permitAll()
                .antMatchers("/404").permitAll()
                .antMatchers("/admin/index").hasRole("ADMIN")//指定权限为ADMIN才能访问
                .antMatchers("/person").hasAnyRole("ADMIN","USER")//指定多个权限都能访问
                .anyRequest() //任何其它请求
                .authenticated() //都需要身份认证
                .and()
                .formLogin() //使用表单认证方式
                .loginProcessingUrl("/login")//配置默认登录入口
                .and()
                .csrf().disable();
    }
}
*/
