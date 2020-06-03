package com.example.demo.es.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.es.pojo.Person;
import com.example.demo.es.pojo.SearchParam;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class EsController {

    @Qualifier("myClient")
    @Autowired
    private RestClient client;

    @RequestMapping("/add")
    public R add(@RequestBody SearchParam param) {
        Person person = new Person();
        person.setName("test1111");
        person.setAddr("test2222");
        person.setBirthday("1328-10-29");
        person.setId("1234567890");
        //以bean的id为该文档的id,以便查询
        String body = param.getType() + "/" + person.getId();
        String responseBody = "";
        try {
            Request request = new Request("POST", body);
            //设置请求体并指定ContentType和编码格式
            NStringEntity entity = new NStringEntity(JSONUtil.toJsonStr(person), "GBK");
            entity.setContentType("application/json");
            request.setEntity(entity);
            Response response = client.performRequest(request);
            //获取响应体
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok(responseBody);

    }

    @RequestMapping("/get")
    public R get(@RequestBody SearchParam param) {
        //拼接查询内容
        String body = param.getIndex() + "/" + param.getType() + "/" + param.getId();
        Request request = new Request("GET", body);
        Object source = null;
        try {
            Response response = client.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity(), "GBK");
            JSONObject json = JSONUtil.parseObj(responseBody);
            //获取我们需要的内容
            source = json.get("_source");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok(source.toString());

    }

}
