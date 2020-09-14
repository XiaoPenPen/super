package com.example.demo.linux;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author xuchunpeng 2020/5/22
 */
public class Test {
    public static void main(String[] args) throws SftpException {

       /* try {
            Session session = JschUtil.openSession("hanyukun.cn", 10222, "hanyukun", "123456");
            System.out.println(session.getTimeout());
            System.out.println("连接成功");
            Sftp sftp= JschUtil.createSftp(session);
            //上传本地文件
            *//*sftp.put("D:/startup.sh", "/home/hanyukun/xuchunpeng");
            sftp.put("D:/shutdown.sh", "/home/hanyukun/xuchunpeng");
            sftp.put("D:\\project\\bc\\backend\\nsrep-flow-collection\\target\\nsrep-flow-collection-0.0.1-SNAPSHOT.jar", "/home/hanyukun/xuchunpeng");*//*
            //下载远程文件
            String reslut = JschUtil.exec(session, "sh /home/hanyukun/xuchunpeng/startup.sh", Charset.defaultCharset());
            System.out.println(reslut);
            session.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //关闭连接

        Session session = JschUtil.createSession("172.18.0.21", 22, "root", "asdf1234!@#$");
        ChannelSftp channelSftp = JschUtil.openSftp(session);
        Sftp sftp = JschUtil.createSftp(session);
        boolean boo = sftp.delDir("/root/flow-collection/pcapMerge/e845518977364d11819665ab383bcfc5/");
        System.out.println(boo);
    }
}
