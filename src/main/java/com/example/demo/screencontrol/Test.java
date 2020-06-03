package com.example.demo.screencontrol;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author xuchunpeng 2020/6/1
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
            Object data = "{\n" +
                    "        \"recv\":[\n" +
                    "            {\n" +
                    "                \"instance-00013a4b\":1\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"instance-00013a4c\":0\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"send\":[\n" +
                    "            {\n" +
                    "                \"instance-00013a42b\":0\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"instance-00013a4c\":1\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }";

            Object data2 = "{\n" +
                    "            \"reason\": \"attack reason\",\n" +
                    "            \"time\": \"4/16 12:22:33.1243123\",\n" +
                    "            \"alert_info\": {\n" +
                    "                \"source\": {\n" +
                    "                    \"ip_addr\": \"192.168.12.56\",\n" +
                    "                    \"mac\": \"11:22:33:44:55:66\",\n" +
                    "                    \"instance_name\": \"instance-00013a4b\",\n" +
                    "                    \"protocal\": 4,\n" +
                    "                    \"project_id\": \"uuid_string\"\n" +
                    "                },\n" +
                    "                \"destination\": {\n" +
                    "                    \"ip\": \"192.168.12.34\",\n" +
                    "                    \"mac\": \"aa:bb:cc:dd:ee:ff\",\n" +
                    "                    \"protocal\": 4,\n" +
                    "                    \"instance_name\": \"instance-abcdef\",\n" +
                    "                    \"project_id\": \"uuid_string\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }";
        System.out.println(addVMData("", JSONUtil.parseObj(data2),9));
    }

    private static Object addVMData(String projectId, Object data, Integer id){
        Result result = new Result();
        result.setCode(1);
        result.setMessage("123");
        Map da = new HashMap();
        da.put("total", 1);

        Map da2 = new HashMap();
        da2.put("name", "Client666");
        da2.put("id", "tttttttttttttttttttt");
        da2.put("instanceName", "instance-00013a4b");

        da.put("items", Arrays.asList(da2));
        result.setData(da);

        if (result.getCode() != ErrorCode.SUCCESS.getCode()){
            log.error("获取虚拟机列表失败：{}", JSONUtil.parse(result));
            return data;
        }
        JSONArray vms = JSONUtil.parseObj(result.getData()).getJSONArray("items");
        if (vms == null || vms.isEmpty()){
            log.info("获取虚拟机列表为[]：{}", JSONUtil.parse(result));
            return data;
        }

        switch (id){
            case 8: // 虚拟机连接信息
                Map<String,Object> dataMap = (Map<String, Object>) data;
                vms.forEach(vm -> {
                    JSONObject jsonObject = JSONUtil.parseObj(vm);
                    dataMap.entrySet().forEach(entry -> {
                        List<Map> list = (List<Map>) entry.getValue();
                        list.forEach(map -> {
                            Iterator iterator = map.entrySet().iterator();
                            while (iterator.hasNext()) {
                                Map.Entry qwe = (Map.Entry) iterator.next();
                                if (jsonObject.getStr("instanceName").equals(qwe.getKey())){
                                    map.put("id",jsonObject.get("id"));
                                    map.put("name",jsonObject.get("name"));
                                }
                            }

                        });
                    });

                });
                break;

            case 9: // 网络安全事件
                cnvertSnort(data);
                /*Map<String,Object> info = (Map<String, Object>) JSONUtil.parseObj(data).get("alert_info");
                vms.forEach(vm -> {
                    JSONObject jsonObject = JSONUtil.parseObj(vm);
                    info.entrySet().forEach(infoEntry -> {
                        Map<String,Object> map = (Map<String,Object>) infoEntry.getValue();
                        if (jsonObject.getStr("instanceName").equals(map.get("instance_name"))){
                            map.put("id",jsonObject.get("id"));
                            map.put("name",jsonObject.get("name"));
                        }
                    });
                });*/
                break;

            default:
                break;
        }
        return data;
    }

    private static void cnvertSnort(Object data){
        Map<String,Object> info = (Map<String, Object>) JSONUtil.parseObj(data).get("alert_info");
        info.entrySet().forEach(infoEntry -> {
            Map<String,Object> map = (Map<String,Object>) infoEntry.getValue();
            Result result = new Result();
            result.setCode(1);
            result.setMessage("123");
            Map da = new HashMap();
            da.put("total", 1);

            Map da2 = new HashMap();
            da2.put("name", "Client666");
            da2.put("id", "tttttttttttttttttttt");
            da2.put("instanceName", "instance-00013a4b");

            da.put("items", Arrays.asList(da2));
            result.setData(da);
            if (result.getCode() != ErrorCode.SUCCESS.getCode()){
                log.error("cnvertSnort获取虚拟机列表失败, projectId：{}, 接口返回值: {}", map.get("project_id"), JSONUtil.parse(result));
                return;
            }
            JSONArray vms = JSONUtil.parseObj(result.getData()).getJSONArray("items");
            if (vms == null || vms.isEmpty()){
                log.error("cnvertSnort获取虚拟机列表为空, projectId：{}, 接口返回值: {}", map.get("project_id"), JSONUtil.parse(result));
                return;
            }
            vms.forEach(vm -> {
                JSONObject jsonObject = JSONUtil.parseObj(vm);
                if (jsonObject.getStr("instanceName").equals(map.get("instance_name"))){
                    map.put("id",jsonObject.get("id"));
                    map.put("name",jsonObject.get("name"));
                    map.put("host",jsonObject.get("host"));
                    map.put("networkId",jsonObject.get("networkId"));
                    map.put("networkName",jsonObject.get("networkName"));
                }
            });
        });
    }
}
