package com.example.demo.kafka;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuchunpeng 2020/12/9
 */
@AllArgsConstructor
@Data
public class LogEntity2 implements Serializable {
    private String methodName; // 方法名
    private Object data; // 内容
    private String userKey; // 用户ID
    private String username; // 用户账号
    private String userCompany; // 用户单位
    private String companyName; // 单位名称
    private String userType; // 用户类型
    private String userRole; // 用户权限
    private String dataTime; // 数据时间

    public static void main(String[] args) {
        System.out.println(DateUtil.current());
        System.out.println(DateUtil.date(DateUtil.current()));
        System.out.println(DateUtil.date(1615889440 * 1000L));
        System.out.println(DateUtil.currentSeconds());;
    }
}
 