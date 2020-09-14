package com.example.demo.es.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.es.config.ESUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "ES 7.2.0")
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private ESUtil esUtil;

    @ApiOperation("创建索引")
    @PutMapping("/create/index")
    public R createIndex(@RequestBody String createJson, @RequestParam String indexName) throws Exception {
        return R.ok(esUtil.createIndex(createJson, indexName, 3, 2));
    }
}
