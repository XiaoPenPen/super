package com.example.demo;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuchunpeng 2021/4/14
 */
public class TEst2 {
    public static void main(String[] args) {
        String[] elemlist = new String[3];
        elemlist[0] = "小明";
        elemlist[1] = "小红";
        elemlist[2] = "小刚";
        Map map = new HashMap();
        map.put("names", elemlist);
        map.put("title", "人员列表");
        System.out.println(JSONUtil.toJsonPrettyStr(map));
    }
}
