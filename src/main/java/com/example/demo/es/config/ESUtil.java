package com.example.demo.es.config;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuchunpeng 2020/9/14
 */
@Slf4j
@Component
public class ESUtil {

    private static RestHighLevelClient client = null;

    @Value("${elasticsearch.user}")
    private String userName;
    @Value("${elasticsearch.password}")
    private String password;
    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private Integer port;

    @PostConstruct
    public void init() {
        if (client == null){
            //基本的连接
            RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(host, port));
            //配置身份验证
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            clientBuilder.setHttpClientConfigCallback(
                    httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
            //设置连接超时和套接字超时
            clientBuilder.setRequestConfigCallback(builder -> builder.setConnectTimeout(10000).setSocketTimeout(60000));
            //设置节点选择器
            clientBuilder.setNodeSelector(NodeSelector.SKIP_DEDICATED_MASTERS);
            //配置HTTP异步请求ES的线程数
            clientBuilder.setHttpClientConfigCallback(
                    httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultIOReactorConfig(
                            IOReactorConfig.custom().setIoThreadCount(1).build()));
            //设置监听器，每次节点失败都可以监听到，可以作额外处理
            clientBuilder.setFailureListener(new RestClient.FailureListener() {
                @Override
                public void onFailure(Node node) {
                    super.onFailure(node);
                    log.error(node.getHost() + "--->该节点失败了");
                }
            });

            client = new RestHighLevelClient(clientBuilder);
        }
    }


    /**
     * Description: 判断某个index是否存在
     *
     * @param index index名
     */
    public Boolean indexExist(String index) {
        try {
            GetIndexRequest request = new GetIndexRequest(index);
            request.local(false);
            request.humanReadable(true);
            request.includeDefaults(false);
            return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("判断某个index是否存在报错:" + e.getMessage(), e);
        }
    }

    /**
     * 创建index
     * @param indexName
     * @param number_of_shards
     * @param number_of_replicas
     * @return
     */
    public String createIndex(String createJson, String indexName, Integer number_of_shards, Integer number_of_replicas) {
        try {
            if (indexExist(indexName)){
                return indexName + "已存在";
            }
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            request.settings(Settings.builder().put("index.number_of_shards", number_of_shards).put("index.number_of_replicas", number_of_replicas));
            request.mapping(createJson, XContentType.JSON);
            CreateIndexResponse res = client.indices().create(request, RequestOptions.DEFAULT);
            return res.index();
        } catch (IOException e) {
            throw new RuntimeException("判断某个index是否存在报错:" + e.getMessage(), e);
        }
    }

    /**
     * Description: 删除index
     */
    public void deleteIndex(String index) {
        try {
            client.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 插入/更新一条记录
     */
    public String insertOrUpdateOne(String index, String id, String data) {
        IndexRequest request = new IndexRequest(index);
        request.id(id);
        request.source(data, XContentType.JSON);
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 批量插入数据
     */
    public void insertBatch(String index, List<JSONObject> dataList) {
        BulkRequest request = new BulkRequest();
        dataList.forEach(item -> request.add(new IndexRequest(index).id(item.getStr("id"))
                .source(item.getStr("data"), XContentType.JSON)));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 搜索
     */
    public <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
