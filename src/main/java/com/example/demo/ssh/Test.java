package com.example.demo.ssh;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchunpeng 2021/3/31
 */
public class Test {
    private long lastTimeFileSize = 0;  //上次文件大小
    /**
     * 实时输出日志信息
     * @param logFile 日志文件
     * @throws IOException
     */
    public void realtimeShowLog(File logFile) throws IOException {
        //指定文件可读可写
        final RandomAccessFile randomFile = new RandomAccessFile(logFile,"rw");
        //启动一个线程每1秒钟读取新增的日志信息
        ScheduledExecutorService exec =
                Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Runnable(){
            @Override
            public void run() {
                try {
                    //获得变化部分的
                    randomFile.seek(lastTimeFileSize);
                    String tmp ="";
                    tmp = randomFile.readLine();
                    while(tmp !=null){
                        System.out.println(new String(tmp.getBytes("iso-8859-1")));
                        tmp = randomFile.readLine();
                        System.out.println("*******");
                    }
                    lastTimeFileSize = randomFile.length();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws Exception {
        Test view = new Test();
        final File tmpLogFile = new File("e://data/1.txt");
        view.realtimeShowLog(tmpLogFile);
    }
}
