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
    @PutMapping("/index")
    public R createIndex(@RequestBody String createJson, @RequestParam String indexName) throws Exception {
        return R.ok(esUtil.createIndex(createJson, indexName, 3, 2));
    }

    @ApiOperation("删除索引")
    @DeleteMapping("/index")
    public R deleteIndex(@RequestParam String indexName) {
        esUtil.deleteIndex(indexName);
        return R.ok("删除成功");
    }

    @ApiOperation("新增或修改一条记录")
    @PostMapping("/doc")
    public R insertDoc(@RequestParam String indexName, @RequestParam String id, @RequestBody String data) {
        return R.ok(esUtil.insertOrUpdateOne(indexName, id, data));
    }

}
