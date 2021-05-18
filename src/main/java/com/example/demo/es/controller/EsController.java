package com.example.demo.es.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUnit;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @ApiOperation("分页查询")
    @GetMapping("/search/page/user")
    public R searchPageByUser(@RequestParam String indexName, String match, String val,
                              @RequestParam Integer startPage,
                              @RequestParam Integer pageSize) {
        List<Map<String, Object>> list = new ArrayList<>();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String[] fields = {"json", "project", "indexName", "_id", "@timestamp", "dataType", "userCompany", "userKey", "userRole", "userType", "methodValue"};
        //需要返回和不返回的字段，可以是数组也可以是字符串
        sourceBuilder.fetchSource(fields, null);
        //设置根据哪个字段进行排序查询
        sourceBuilder.sort(new FieldSortBuilder("@timestamp").order(SortOrder.DESC));
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //添加查询条件
        builder.must(QueryBuilders.matchQuery(match, val));
        list = ESUtil.SearchDataPage(indexName, startPage, pageSize, sourceBuilder, builder);
        return R.ok(list);
    }

    public static Boolean isBefore(String startTime, String endTime, DateUnit dateUnit){
        return  cn.hutool.core.date.DateUtil.between(cn.hutool.core.date.DateUtil.parse(startTime), cn.hutool.core.date.DateUtil.parse(endTime), dateUnit, false) >= 0;
    }

    public static void main(String[] args) throws ParseException {
        String str="2020-01-02 07:59:59";
        String str2="2020-01-09 07:59:59";
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        calendar.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        long timeInMillis = calendar.getTimeInMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        cn.hutool.core.date.DateUtil.rangeToList(DateUtil.parse(str2, "yyyy-MM-dd"), DateUtil.parse(str, "yyyy-MM-dd"), DateField.DAY_OF_MONTH).forEach(System.out::println);

        System.out.println(isBefore(str,str2,DateUnit.DAY));
    }
}
