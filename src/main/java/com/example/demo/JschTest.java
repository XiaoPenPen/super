package com.example.demo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ssh.ChannelType;
import cn.hutool.extra.ssh.JschRuntimeException;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.extra.ssh.JschUtil.close;
import static cn.hutool.extra.ssh.JschUtil.createChannel;

/**
 * @author xuchunpeng 2020/6/25
 */
public class JschTest {

    private static final String path = "/etc/nginx/conf.d";
    private static final String DNS_RESTART_SHELL = "systemctl restart dnsmasq";

    public static void main(String[] args) throws Exception {
        Session session = JschUtil.createSession("47.244.107.125",22,"root", "Xcp123456");
        ChannelSftp channelSftp = JschUtil.createSftp(session).getClient();
        Sftp sftp = JschUtil.createSftp(session);
        //sftp.delDir(path);
        //sftp.mkDirs(path);
        //sftp.put("E:\\data\\1.txt",path);
        String str = "/root/qwe-1/";
        //String dns = exec(session, "sh /home/app/flow-collection/startup.sh", Charset.forName("UTF-8"), System.err);
        //System.out.println(dns+"123");
        channelSftp.rmdir(str);
//        channelSftp.mkdir(str);
//        channelSftp.put( FileUtils.openInputStream(new File("E:\\data\\1.txt")),str + "2.txt");
        sftp.close();
        session.disconnect();
    }

    public static String exec(Session session, String cmd, Charset charset, OutputStream errStream) throws Exception {
        if (null == charset) {
            charset = CharsetUtil.CHARSET_UTF_8;
        }

        ChannelExec channel = (ChannelExec)createChannel(session);
        channel.setCommand(StrUtil.bytes(cmd, charset));
        channel.setInputStream((InputStream)null);
        channel.setErrStream(errStream);
        InputStream in = null;
        InputStream in2 = null;

        String var6;
        String var7;
        try {
            channel.connect();
            in = channel.getInputStream();
            in2 = channel.getExtInputStream();
            var6 = IoUtil.read(in, CharsetUtil.CHARSET_UTF_8);
            var7 = IoUtil.read(in2, CharsetUtil.CHARSET_UTF_8);
        } catch (Exception var11) {
            throw new Exception(var11);
        } finally {
            IoUtil.close(in);
            close((Channel)channel);
        }

        return var6 + var7;
    }

    public static Channel createChannel(Session session) {
        try {
            if (!session.isConnected()) {
                session.connect();
            }

            Channel channel = (ChannelExec) JschUtil.createChannel(session, ChannelType.EXEC);
            return channel;
        } catch (JSchException var4) {
            throw new JschRuntimeException(var4);
        }
    }

}
