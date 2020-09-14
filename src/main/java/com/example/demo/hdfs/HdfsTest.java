package com.example.demo.hdfs;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

/**
 * @author xuchunpeng 2020/7/31
 */
public class HdfsTest {

    public static void main(String[] args) throws Exception {
        // HdfsService.renameFile( "/flow-collection/collection/d9d3834c00f34eeeb4f4b6d862fac243", "/flow-collection/collection/35ab17072d5f4d9b92e19cc44f418f59");
       // getListNode();
        System.out.println(HdfsService.deleteFile("/flow-collection/netTestResult/updateIp/9e31d6773542437eabfd7062385b1d84"));

    }

    public static void getListNode() throws Exception {

        FileSystem fs = HdfsService.getFileSystem();



        DistributedFileSystem hdfs = (DistributedFileSystem)fs;

        DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();



        for(int i=0;i<dataNodeStats.length;i++){

            System.out.println("DataNode_"+i+"_Name:"+dataNodeStats[i].getHostName());

        }


    }}
