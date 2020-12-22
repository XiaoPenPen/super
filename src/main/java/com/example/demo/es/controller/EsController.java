package com.example.demo.es.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.es.config.ESUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Api(tags = "ES 7.2.0")
@RestController
@RequestMapping("/es")
public class EsController {

    @Autowired
    private ESUtil esUtil;

    @ApiOperation("创建索引")
    @PutMapping("/index")
    public R createIndex(@RequestBody String createJson, @RequestParam String indexName) throws Exception {
        return R.ok(esUtil.createIndex(createJson, indexName, 1, 1));
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

    @ApiOperation("查询全部")
    @GetMapping("/search/all")
    public R searchAll(@RequestParam String indexName) {
        return R.ok(esUtil.search(indexName, new SearchSourceBuilder(), Object.class));
    }

    @ApiOperation("查询条件")
    @GetMapping("/search/page")
    public R searchPage(@RequestParam String indexName, String match, String val) {
        List<Map<String, Object>> list = new ArrayList<>();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String[] fields = {"messagejson", "project", "indexName", "_id", "@timestamp"};
        //需要返回和不返回的字段，可以是数组也可以是字符串
        sourceBuilder.fetchSource(fields, null);
        //设置根据哪个字段进行排序查询
        sourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.DESC));
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //添加查询条件
        builder.must(QueryBuilders.matchQuery(match, val));
        list = ESUtil.SearchDataPage(indexName, 1, 10, sourceBuilder, builder);
        return R.ok(list);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.parseUTC("2020-12-17T10:56:59.319Z").getTime());
    }


}
