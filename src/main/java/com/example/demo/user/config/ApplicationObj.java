package com.example.demo.user.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件对象
 * @author xuchunpeng 2020/10/10
 */
@Component
public class ApplicationObj {

    @Component
    @PropertySource(value = {"classpath:properties/detection-task.properties", "file:properties/detection-task.properties"}, ignoreResourceNotFound = true)
    public static class DetectionTask implements InitializingBean {
        @Value("${DetectionTask.applyTokenIn.protocal}")
        public String protocal;
        @Value("${DetectionTask.applyTokenIn.port}")
        public Integer port;
        @Value("${DetectionTask.applyTokenIn.outPort}")
        public Integer outPort;
        @Value("${DetectionTask.applyTokenIn.teamId}")
        public String teamId;
        @Value("${DetectionTask.applyTokenIn.expireTime}")
        public Integer expireTime;
        @Value("${DetectionTask.applicationNode.server}")
        public String server;
        @Value("${DetectionTask.applicationNode.callbackIp}")
        public String callbackIp;

        public static String PROTOCAL;
        public static Integer PORT;
        public static Integer OUTPORT;
        public static String TEAMID;
        public static Integer EXPIRETIME;
        public static String SERVER;
        public static String CALLBACKIP;

        @Override
        public void afterPropertiesSet() throws Exception {
            SERVER = this.server;
            PROTOCAL = this.protocal;
            PORT = this.port;
            OUTPORT = this.outPort;
            TEAMID = this.teamId;
            EXPIRETIME = this.expireTime;
            CALLBACKIP = this.callbackIp;
        }
    }


}
