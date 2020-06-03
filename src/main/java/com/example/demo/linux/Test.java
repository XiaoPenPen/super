package com.example.demo.linux;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;

/**
 * @author xuchunpeng 2020/5/22
 */
public class Test {
    public static void main(String[] args) {
        Sftp sftp= JschUtil.createSftp("172.18.100.1", 22, "root", "asdf1234!@#$");
        //进入远程目录
        //sftp.cd("");
        //上传本地文件
  /*      sftp.put("e:/test.jpg", "/opt/upload");*/
        //下载远程文件
        sftp.get("/home/app/screen-control/consoleMsg.out", "d:/consoleMsg.out");

        //关闭连接
        IoUtil.close(sftp);
    }
}
