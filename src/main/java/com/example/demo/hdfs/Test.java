package com.example.demo.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * @author xuchunpeng 2020/6/22
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.18.1.51:8020");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

        FileSystem fs = null;
        fs = FileSystem.get(URI.create("/"), conf, "hdfs");
        fs.deleteOnExit(new Path("/flow-collection/collection4fd49e000d9f4cb4bb1bc7e78a1baf4c"));
        fs.close();
    }

    public static void mkdir(String path) throws IOException {
        //读取配置文件
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://172.18.1.51:9000");
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");
        //获取文件系统
        FileSystem fs = FileSystem.get(URI.create("hdfs://172.18.1.51:9000"),conf);

        Path srcPath =  new Path(path);
        //调用mkdir（）创建目录，（可以一次性创建，以及不存在的父目录）
        boolean flag = fs.mkdirs(srcPath);
        if(flag) {
            System.out.println("create dir ok!");
        }else {
            System.out.println("create dir failure");
        }

        //关闭文件系统
        fs.close();
    }

}
