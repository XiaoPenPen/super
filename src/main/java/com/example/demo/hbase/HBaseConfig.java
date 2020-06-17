package com.example.demo.hbase;

import ch.qos.logback.classic.db.names.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author dushixiang
 */
@Configuration
public class HBaseConfig {

    @Value("${hbase.zookeeper.property.clientPort}")
    private Integer hbaseZookeeperPropertyClientPort;
    @Value("${hbase.zookeeper.quorum:2181}")
    private String hbaseZookeeperQuorum;

    @Bean
    public Connection connection() throws IOException {
        org.apache.hadoop.conf.Configuration hConf = org.apache.hadoop.hbase.HBaseConfiguration.create();
        hConf.set("hbase.zookeeper.property.clientPort", String.valueOf(hbaseZookeeperPropertyClientPort));
        hConf.set("hbase.zookeeper.quorum", hbaseZookeeperQuorum);
        return ConnectionFactory.createConnection(hConf);
    }

    @Bean
    public TableName oobTableName() {
        return TableName.valueOf("oob_status");
    }

    @Bean
    public TableName ovsTableName() {
        return TableName.valueOf("ovs_status");
    }
}
